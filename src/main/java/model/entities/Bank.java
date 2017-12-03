package model.entities;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private String name;
    private List<Deposit> deposits;

    public Bank() {
    }

    public Bank(String name) {
        this.name = name;
    }

    public Bank(String name, List<Deposit> deposits) {
        this.name = name;
        this.deposits = new ArrayList<Deposit>(deposits);
    }

    public boolean addDeposit(Deposit deposit) {
        return deposits.add(deposit);
    }

    public void addDeposits(List<Deposit> deposits) {
        if (this.deposits == null) {
            this.deposits = new ArrayList<Deposit>(deposits);
        }
        else {
            this.deposits.addAll(deposits);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Deposit> getDeposits() {
        return deposits;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name + ": \n");
        for (Deposit deposit : deposits) {
            sb.append("\t").append(deposit).append("\n");
        }
        return sb.toString();
    }
}
