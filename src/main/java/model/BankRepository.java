package model;

import model.entities.Bank;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public enum BankRepository {
    INSTANCE;

    private List<Bank> banks = new ArrayList<Bank>();

    BankRepository() {
        load();
    }

    private void load() {
        try (FileInputStream file = new FileInputStream("deposits.ser")) {
            ObjectInputStream ois = new ObjectInputStream(file);
            while (file.available() > 0) {
                Bank bank = (Bank)ois.readObject();
                add(bank);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean add(Bank bank) {
        return banks.add(bank);
    }

    public List<Bank> getBanks() {
        return banks;
    }
}
