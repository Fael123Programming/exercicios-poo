package _library_simulation.concrete_classes.lending_of_publication.lending_main_class;

import _library_simulation.abstract_classes.publication.Publication;
import _library_simulation.abstract_classes.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;

public class Lending {
    private Publication publication;
    private LocalDateTime lendingDateTime;
    private LocalDate deliveryDate;
    private ArrayList<LocalDateTime> renovations;
    private boolean created;

    public boolean lend(Publication publication, User userToLend, int quantityOfDays) {
        //This method fills up the attributes of each object of this class themselves.
        if (publication.isAvailable()) {
            if (userToLend.canBorrow()) {
                publication.setAvailable(false);
                this.publication = publication;
                this.lendingDateTime = LocalDateTime.now();
                this.deliveryDate = this.lendingDateTime.plus(Period.ofDays(quantityOfDays)).toLocalDate();
                this.renovations = new ArrayList<>();
                this.created = true;
                return userToLend.getLendings().add(this);
            }
            return false;//Problem! User can't borrow any publication...
        }
        return false;//Problem! Publication is not available...
    }

    public boolean renew(int additionalDays) {
        if (!this.created || additionalDays <= 0) return false;
        this.deliveryDate = this.deliveryDate.plus(Period.ofDays(additionalDays));
        this.renovations.add(LocalDateTime.now());
        return true;
    }

    public Publication getPublication() {
        return this.publication;
    }

    public boolean setPublication(Publication newPublication) {
        if (!newPublication.isAvailable()) return false;
        this.publication = newPublication;
        return true;
    }

    public LocalDateTime getLendingDateTime() {
        return this.lendingDateTime;
    }

    public void setLendingDateTime(LocalDateTime lendingDateTime) {
        this.lendingDateTime = lendingDateTime;
    }

    public LocalDate getDeliveryDate() {
        return this.deliveryDate;
    }

    public void setDeliveryDate(LocalDate newDeliveryDate) {
        this.deliveryDate = newDeliveryDate;
    }

    public boolean isCreated() {
        return this.created;
    }

    public ArrayList getRenovations() {
        if (this.renovations.isEmpty()) return null;
        return this.renovations;
    }
}