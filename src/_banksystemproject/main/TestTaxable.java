package _banksystemproject.main;

import _banksystemproject.classes.accounts.CurrentAccount;
import _banksystemproject.classes.life_insurance.LifeInsurance;
import _banksystemproject.classes.ownersofaccounts.PhysicalPerson;
import _banksystemproject.interfaces.ITaxable;

public class TestTaxable {
    public static void main(String[] args) {/*
        PhysicalPerson me = new PhysicalPerson("Rafael Fonseca","064.854.071-54","6/11/2002");
        LifeInsurance lf = new LifeInsurance(5000,me,1);
        CurrentAccount cc = new CurrentAccount(me,12345,"Caldas Novas");
        cc.deposit(5000);
        System.out.println("Through life insurance reference: " + lf.getTaxValue());
        System.out.println("Through current account reference: " + cc.getTaxValue());
        ITaxable itax = lf;
        System.out.println("Through interface reference: " + itax.getTaxValue());
        itax = cc;
        System.out.println("Through interface reference: " + itax.getTaxValue());*/
    }
}
