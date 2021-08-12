package _library_simulation.types_of_controllers;

import _library_simulation.abstract_classes.controller.Controller;

import _library_simulation.abstract_classes.publication.Publication;

import _library_simulation.abstract_classes.user.User;

import _library_simulation.concrete_classes.library.Library;
import _library_simulation.concrete_classes.publication.loan.Loan;

import _library_simulation.exceptions.*;

import java.util.ArrayList;

public class LoanController extends Controller {
    public LoanController(Library library){
        super(library);
    }

    public boolean lendPublication(Publication publicationToLend, User lendingOwner, int quantityOfDays)
            throws UserCannotBorrowException, UnavailablePublicationException {
        Loan newLoan = new Loan();
        if(newLoan.lend(publicationToLend, lendingOwner, quantityOfDays)){
            this.getLoans().add(newLoan);
            return true;
        }
        return false;
    }

    public boolean renew(Loan loanToRenew) throws RenovationsExceededException, ExpiredLendingException, InvalidRenovationDate {
        if(loanToRenew == null) return false;
        for(Loan loan:this.getLoans()){
            if(loan.getPublication().getTitle().equals(loanToRenew.getPublication().getTitle())){
                return loan.renew();
            }
        }
        return false;
    }

    public double finishLoan(Loan loanToEnd){
        if(this.getLoans().isEmpty() || loanToEnd == null) return -1;
        for(Loan loan:super.getLibrary().getLoans()){
            if(loan.getPublication().getTitle().equals(loanToEnd.getPublication().getTitle())){
                double fine = loan.finish();
                this.getLoans().remove(loan);
                return fine;
            }
        }
        return -1;
    }

    public ArrayList<Loan> getLoans(){ return super.getLibrary().getLoans();}

    public Loan getLoan(int position){ return this.getLoans().get(position);}
}