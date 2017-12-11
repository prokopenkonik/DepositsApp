package execute;

import control.Controller;
import model.BankRepository;
import model.DepositsManager;
import view.Viewer;

public class Main {
    public static void main(String[] args) {
        Viewer viewer = new Viewer();
        DepositsManager depositsManager = DepositsManager.INSTANCE;
        BankRepository banks = BankRepository.INSTANCE;
        depositsManager.setBankRepository(banks);
        Controller controller = new Controller(viewer, depositsManager);
        controller.run();
    }
}
