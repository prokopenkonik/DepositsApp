package model;

import model.entities.Bank;
import model.entities.Deposit;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DepositsManagerTest {

    private DepositsManager depositsManager;
    private List<Deposit> allDeposits;

    @Before
    public void setUp() throws Exception {
        depositsManager = DepositsManager.INSTANCE;
        depositsManager.setBankRepository(BankRepository.INSTANCE);
        allDeposits = new ArrayList<Deposit>();

        Bank bank = new Bank("Privat");
        List<Deposit> deposits = new ArrayList<Deposit>();
        Deposit deposit = new Deposit("Dep1", bank, 100, 12,
                15.0, true, false);
        deposits.add(deposit);
        allDeposits.add(deposit);
        deposit = new Deposit("Dep2", bank, 200, 6,
                15.9, true, true);
        deposits.add(deposit);
        allDeposits.add(deposit);
        bank.addDeposits(deposits);
        depositsManager.getBankRepository().add(bank);

        bank = new Bank("Alpha");
        deposits.clear();
        deposit = new Deposit("Dep1", bank, 500, 6,
                10.5, false, true);
        deposits.add(deposit);
        allDeposits.add(deposit);
        deposit = new Deposit("Dep2", bank, 50, 4,
                20.2, true, false);
        deposits.add(deposit);
        allDeposits.add(deposit);
        bank.addDeposits(deposits);
        depositsManager.getBankRepository().add(bank);
    }

    @Test
    public void getAllDeposits() throws Exception {
        System.out.println(allDeposits);
        System.out.println(depositsManager.getAllDeposits());
        assertEquals(allDeposits, depositsManager.getAllDeposits());
    }

    @Test
    public void getDepositsByParameters() throws Exception {
        List<Deposit> res = new ArrayList<Deposit>();
        res.add(allDeposits.get(1));
        res.add(allDeposits.get(2));
        assertEquals(res, depositsManager.getDepositsByParameters(600, 6));
        res = new ArrayList<Deposit>();
        res.add(allDeposits.get(0));
        assertEquals(res, depositsManager.getDepositsByParameters(1000, 12,
                "privat", true, false));
    }
}