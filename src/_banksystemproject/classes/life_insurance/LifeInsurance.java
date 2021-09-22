package _banksystemproject.classes.life_insurance;

import _banksystemproject.classes.ownersofaccounts.Customer;
import _banksystemproject.interfaces.ITaxable;

public class LifeInsurance implements ITaxable {
    private double value;
    private Customer owner;
    private int policyNumber;

    public LifeInsurance(double value, Customer owner, int policyNumber) {
        this.value = value;
        this.owner = owner;
        this.policyNumber = policyNumber;
    }

    public double getValue(){return this.value;}

    public Customer getOwner(){return this.owner;}

    public int getPolicyNumber(){return this.policyNumber;}

    public void setValue(double newValue) {this.value = newValue;}

    public void setOwner(Customer newOwner) {this.owner = newOwner;}

    public void setPolicyNumber(int policyNumber) {this.policyNumber = policyNumber;}

    @Override
    public double getTaxValue(){
        return 42 + this.value * 0.02;
    }
}
