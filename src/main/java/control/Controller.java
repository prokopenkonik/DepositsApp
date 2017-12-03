package control;

import control.constants.MainCommand;
import control.constants.SortingCommand;
import model.DepositsManager;
import model.entities.Deposit;
import model.util.DepositSort;
import services.InputUtility;
import view.Viewer;
import java.util.List;

import static view.constants.IMenuConstants.*;

public class Controller {
    private Viewer viewer;
    private DepositsManager depositsManager;

    public Controller(Viewer viewer, DepositsManager depositsManager) {
        this.viewer = viewer;
        this.depositsManager = depositsManager;
    }

    public void run() {
        while (true) {
            viewer.showMenu();
            MainCommand mainCommand = intToCommand(
                    InputUtility.inputCommandNumber(
                            viewer, 0, 2));

            switch (mainCommand) {
                case GET_ALL_DEPOSITS:
                    viewer.showResult(depositsManager.getAllDeposits());
                    break;
                case GET_DEPOSITS_BY_PARAMETERS:
                    List<Deposit> deposits = getDepositsByParameters();
                    viewer.showResult(deposits);
                    if (deposits.size() != 0) {
                        offerSorting(deposits);
                    }
                    break;
                case EXIT:
                    System.exit(0);
                    break;
            }
        }
    }

    private void offerSorting(List<Deposit> deposits) {
        viewer.showMenuForSorting();
        SortingCommand sortingCommand = intToSortingCommand(
                InputUtility.inputCommandNumber(
                        viewer, 0, 4));
        boolean isSorted = true;
        switch (sortingCommand) {
            case SORT_BY_BANK:
                DepositSort.sortByBankName(deposits);
                break;
            case SORT_BY_PERCENT:
                DepositSort.sortByPercent(deposits);
                break;
            case SORT_BY_NAME:
                DepositSort.sortByDepositName(deposits);
                break;
            case BACK_TO_MAIN_MENU:
                isSorted = false;
                break;
            case EXIT:
                System.exit(0);
                break;
        }
        if (isSorted) {
            viewer.showResult(deposits);
        }
    }

    private List<Deposit> getDepositsByParameters() {
        int sum = InputUtility.inputInt(viewer, INPUT_SUM);
        int term = InputUtility.inputInt(viewer, INPUT_TERM);

        viewer.showMessage(INPUT_CHOICE);
        int choiceCommand = InputUtility.inputCommandNumber(
                viewer, 1, 2);

        if (choiceCommand == 1) {
            String bankName = InputUtility.inputString(viewer, INPUT_BANK_NAME);
            boolean isPretermWithdraw = getBoolean(INPUT_PRETERM_WITHDRAW);
            boolean isReplenish = getBoolean(INPUT_REPLENISH);

            return depositsManager.getDepositsByParameters(sum, term,
                            bankName, isPretermWithdraw, isReplenish);
        }
        return depositsManager.getDepositsByParameters(sum, term);
    }

    private boolean getBoolean(String message) {
        viewer.showMessage(message);
        int commandForBoolean = InputUtility.inputCommandNumber(viewer,
                1, 2);

        return intToBoolean(commandForBoolean);
    }

    private SortingCommand intToSortingCommand(int sortingCommand) {
        switch (sortingCommand) {
            case 1:
                return SortingCommand.SORT_BY_BANK;
            case 2:
                return SortingCommand.SORT_BY_PERCENT;
            case 3:
                return SortingCommand.SORT_BY_NAME;
            case 4:
                return SortingCommand.BACK_TO_MAIN_MENU;
            case 0:
                return SortingCommand.EXIT;
            default:
                throw new IllegalArgumentException();
        }
    }

    private MainCommand intToCommand(int commandNumber) {
        switch (commandNumber) {
            case 1:
                return MainCommand.GET_ALL_DEPOSITS;
            case 2:
                return MainCommand.GET_DEPOSITS_BY_PARAMETERS;
            case 0:
                return MainCommand.EXIT;
            default:
                throw new IllegalArgumentException();
        }
    }

    private boolean intToBoolean(int commandForBoolean) {
        switch (commandForBoolean) {
            case 1:
                return true;
            case 2:
                return false;
            default:
                throw new IllegalArgumentException();
        }
    }
}
