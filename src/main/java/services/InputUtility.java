package services;

import view.Viewer;
import java.util.Scanner;
import static view.constants.IMenuConstants.*;

public class InputUtility {
    private static Scanner sc = new Scanner(System.in);

    public static int inputInt(Viewer viewer, String message) {
        viewer.showMessage(message);
        while (!sc.hasNextInt()) {
            showErrorAndSkip(viewer, message);
        }
        return sc.nextInt();
    }

    public static String inputString(Viewer viewer, String message) {
        viewer.showMessage(message);
        while (!sc.hasNext()) {
            showErrorAndSkip(viewer, message);
        }
        String res = sc.nextLine();
        sc.next();
        return res;
    }

    private static void showErrorAndSkip(Viewer viewer, String message) {
        viewer.showMessage(WRONG_INPUT + NEW_LINE + message);
        sc.next();
    }

    public static int inputCommandNumber(Viewer viewer, int fromCommand,
                                         int toCommand) {
        int result = inputInt(viewer, INPUT_COMMAND);
        while (result < fromCommand || result > toCommand) {
            viewer.showMessage(WRONG_INPUT);
            result = inputInt(viewer, INPUT_COMMAND);
        }
        return result;
    }
}
