package _library_simulation.concrete_classes.publication.loan;

import _library_simulation.abstract_classes.publication.Publication;
import _library_simulation.abstract_classes.user.User;

import _library_simulation.exceptions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;

public class Loan {
    private Publication publication;
    private User loanOwner;
    private LocalDateTime loanDateTime, realDeliveryDateTime;
    private LocalDate deliveryDate;
    private ArrayList<LocalDateTime> renovations;
    private boolean active;

    public boolean lend(Publication publication, User lendingOwner, int quantityOfDays) {
        if(this.active || publication == null || lendingOwner == null || quantityOfDays == 0) return false;
        if (publication.isAvailable()) {
            if (lendingOwner.canBorrow()) {
                publication.setAvailable(false);
                lendingOwner.setTotalOfLoans(lendingOwner.getTotalOfLoans() + 1);
                this.publication = publication;
                this.loanOwner = lendingOwner;
                this.loanDateTime = LocalDateTime.now();
                this.deliveryDate = this.loanDateTime.plusDays(quantityOfDays).toLocalDate();//7 days after lending was made
                this.renovations = new ArrayList<>();
                this.active = true;
                return true;
            }
            throw new UserCannotBorrowException();
        }
        throw new UnavailablePublicationException();
    }

    public boolean renew(){
        if(!this.isActive()) return false;
        if(this.renovations.size() == this.loanOwner.getLimitOfRenovations()) throw new RenovationsExceededException();
        if(this.isExpired()) throw new ExpiredLendingException();
        if(LocalDate.now().plusDays(7).isBefore(this.deliveryDate)) throw new InvalidRenovationDate();//It is too early to renew
        this.deliveryDate = LocalDate.now().plusDays(7);
        this.renovations.add(LocalDateTime.now()); //Timestamping of this renovation
        return true;
    }

    public double finish(){
        if(!this.isActive()) return -1;
        double fineValue = 0;
        if(this.isExpired()){
            Period prd = Period.between(this.deliveryDate, LocalDate.now());
            fineValue = prd.getDays() * this.getPublication().getFineValue();
        }
        this.publication.setAvailable(true);
        this.loanOwner.setTotalOfLoans(this.loanOwner.getTotalOfLoans() - 1);
        this.realDeliveryDateTime = LocalDateTime.now();
        return fineValue;
    }

    public Publication getPublication() {
        return this.publication;
    }

    public void setPublication(Publication newPublication) {
        if(this.isExpired()) throw new ExpiredLendingException();
        if(newPublication == null) return;
        if (!newPublication.isAvailable()) throw new UnavailablePublicationException();
        this.publication.setAvailable(true);
        this.publication = newPublication;
    }

    public User getUser(){ return this.loanOwner;}

    public LocalDateTime getLoanDateTime() {
        return this.loanDateTime;
    }

    public LocalDateTime getRealDeliveryDateTime(){
        return this.realDeliveryDateTime;
    }

    public LocalDate getDeliveryDate() {
        return this.deliveryDate;
    }

    public ArrayList<LocalDateTime> getRenovations() {
        if (this.renovations.isEmpty()) return null;
        return this.renovations;
    }

    public boolean isActive() {
        return this.active;
    }

    public boolean isExpired(){
        Period prd = Period.between(LocalDate.now(), this.deliveryDate); //If deliveryDate is before now, lending expired already
        return prd.isNegative();
    }
}