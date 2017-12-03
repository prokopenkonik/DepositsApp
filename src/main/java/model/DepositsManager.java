package model;

import model.entities.Bank;
import model.entities.Deposit;
import java.util.ArrayList;
import java.util.List;

public enum DepositsManager {
    INSTANCE;

    private BankRepository bankRepository;
    private List<Deposit> allDeposits;

    public List<Deposit> getAllDeposits() {
        if (allDeposits == null) {
            allDeposits = new ArrayList<Deposit>();
            for (Bank bank: bankRepository.getBanks()) {
                allDeposits.addAll(bank.getDeposits());
            }
        }
        return allDeposits;
    }

    public List<Deposit> getDepositsByParameters(int sum, int term) {
        if (allDeposits == null) {
            getAllDeposits();
        }
        List<Deposit> result = new ArrayList<Deposit>();
        for (Deposit deposit : allDeposits) {
            if (deposit.getMinSum() <= sum
                    && deposit.getTerm() == term) {
                result.add(deposit);
            }
        }
        return result;
    }

    public List<Deposit> getDepositsByParameters(int sum, int term,
                    String bankName, boolean isPretermWithdraw, boolean isReplenish) {
        if (allDeposits == null) {
            getAllDeposits();
        }
        List<Deposit> result = new ArrayList<Deposit>();
        for (Deposit deposit : allDeposits) {
            if (deposit.getMinSum() <= sum
                    && deposit.getTerm() == term
                    && deposit.getBank().getName().equalsIgnoreCase(bankName)
                    && deposit.isPretermWithdraw() == isPretermWithdraw
                    && deposit.isReplenish() == isReplenish) {
                result.add(deposit);
            }
        }
        return result;
    }

    public BankRepository getBankRepository() {
        return bankRepository;
    }

    public void setBankRepository(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }
}
