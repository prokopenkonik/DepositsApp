package view;

import model.entities.Deposit;
import static view.constants.IMenuConstants.*;
import java.util.List;

public class Viewer {

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showMenu() {
        System.out.println(GET_ALL_DEPOSITS_LINE + "\n"
                + GET_DEPOSITS_BY_PARAMETERS_LINE + "\n"
                + EXIT_LINE + "\n");
    }

    public void showMenuForSorting() {
        System.out.println(SORT_BY_BANK_LINE + "\n"
                + SORT_BY_PERCENT_LINE + "\n"
                + SORT_BY_NAME_LINE + "\n"
                + BACK_TO_MAIN_MENU_LINE + "\n"
                + EXIT_LINE + "\n");
    }

    public void showResult(List<Deposit> deposits) {
        if (deposits.isEmpty()) {
            showMessage(NOT_FOUND);
        }
        else {
            for (Deposit deposit : deposits) {
                System.out.println(deposit);
            }
            System.out.println();
        }
    }
}
