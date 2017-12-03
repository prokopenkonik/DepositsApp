package model;

import model.entities.Bank;
import java.util.ArrayList;
import java.util.List;

public enum BankRepository {
    INSTANCE;

    private List<Bank> banks = new ArrayList<Bank>();

    public boolean add(Bank bank) {
        return banks.add(bank);
    }

    public List<Bank> getBanks() {
        return banks;
    }
}
