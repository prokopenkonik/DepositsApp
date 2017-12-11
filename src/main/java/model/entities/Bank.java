package model.entities;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;

public class Bank implements Externalizable {
    private String name;
    private List<Deposit> deposits;
    private int depositsCount = 0;

    public Bank() {
        deposits = new ArrayList<Deposit>();
    }

    public Bank(String name) {
        this.name = name;
        deposits = new ArrayList<Deposit>();
    }

    public Bank(String name, List<Deposit> deposits) {
        this.name = name;
        this.deposits = new ArrayList<Deposit>(deposits);
        depositsCount = this.deposits.size();
    }

    public void addDeposit(Deposit deposit) {
        deposits.add(deposit);
        depositsCount++;
    }

    public void addDeposits(List<Deposit> deposits) {
        if (this.deposits == null) {
            this.deposits = new ArrayList<Deposit>(deposits);
        }
        else {
            this.deposits.addAll(deposits);
        }
        depositsCount = this.deposits.size();
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

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(name);
        out.writeInt(depositsCount);
        System.out.println(depositsCount);
        for (Deposit deposit : deposits) {
            out.writeObject(deposit);
        }
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = in.readUTF();
        int count = in.readInt();
        Deposit deposit;
        for (int i = 0; i < count; i++) {
            deposit = (Deposit) in.readObject();
            deposit.setBank(this);
            addDeposit(deposit);
        }
    }

    public int getDepositsCount() {
        return depositsCount;
    }
}
