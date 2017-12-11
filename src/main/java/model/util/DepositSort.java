package model.util;

import model.entities.Deposit;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DepositSort {
    public static void sortByBankName(List<Deposit> deposits) {
        Collections.sort(deposits, new Comparator<Deposit>() {
            public int compare(Deposit o1, Deposit o2) {
                return o1.getBank().getName().compareTo(o2.getBank().getName());
            }
        });
    }

    public static void sortByPercent(List<Deposit> deposits) {
        Collections.sort(deposits, new Comparator<Deposit>() {
            public int compare(Deposit o1, Deposit o2) {
                return Double.compare(o2.getPercent(), o1.getPercent());
            }
        });
    }

    public static void sortByDepositName(List<Deposit> deposits) {
        Collections.sort(deposits, new Comparator<Deposit>() {
            public int compare(Deposit o1, Deposit o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }
}
