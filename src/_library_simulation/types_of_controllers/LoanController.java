package _library_simulation.types_of_controllers;

import _library_simulation.abstract_classes.controller.Controller;

import _library_simulation.abstract_classes.publication.Publication;

import _library_simulation.abstract_classes.user.User;

import _library_simulation.concrete_classes.library.Library;
import _library_simulation.concrete_classes.publication.loan.Loan;

import _library_simulation.exceptions.ExpiredLendingException;
import _library_simulation.exceptions.RenovationsExceededException;
import _library_simulation.exceptions.UnavailablePublicationException;
import _library_simulation.exceptions.UserCannotBorrowException;

import java.util.ArrayList;

public class LoanController extends Controller {
    public LoanController(Library library){
        super(library);
    }

    public boolean lendPublication(Publication publicationToLend, User lendingOwner, int quantityOfDays) throws UserCannotBorrowException, UnavailablePublicationException {
        Loan newLoan = new Loan();
        if(newLoan.lend(publicationToLend, lendingOwner, quantityOfDays)){
            super.getLibrary().getLoans().add(newLoan);
            return true;
        }
        return false;
    }

    public boolean renew(Loan loanToRenew, int quantityOfDays) throws ExpiredLendingException, RenovationsExceededException {
        if(loanToRenew == null || quantityOfDays == 0) return false;
        for(Loan loan:super.getLibrary().getLoans()){
            if(loan.getPublication().getTitle().equals(loanToRenew.getPublication().getTitle())){
                return loan.renew(quantityOfDays);
            }
        }
        return false;
    }

    public double finishLoan(Loan loanToEnd){
        if(super.getLibrary().getLoans().isEmpty() || loanToEnd == null) return -1;
        for(Loan loan:super.getLibrary().getLoans()){
            if(loan.getPublication().getTitle().equals(loanToEnd.getPublication().getTitle())){
                double fine = loan.finish();
                super.getLibrary().getLoans().remove(loan);
                return fine;
            }
        }
        return 0;
    }

    public ArrayList<Loan> getLoans(){ return super.getLibrary().getLoans();}

    public Loan getLoan(int position){ return this.getLoans().get(position);}
}