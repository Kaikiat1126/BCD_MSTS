package msts.menu;

import msts.DigitalSignature;
import msts.StatusContainer;
import msts.Transaction;
import msts.obj.Patient;
import msts.obj.User;

import java.util.List;

public class PatientMenu extends StackholderMenu {

    static PatientMenu instance = null;

    public static PatientMenu getMenu() {
        if (instance == null) {
            instance = new PatientMenu();
        }
        return instance;
    }

    public void generateMenu() {
        int option;
        do {
            System.out.println("\nPatient Menu");
            System.out.println("1. View Medicine Transaction");
            System.out.println("2. Purchase Medicine");
            System.out.println("3. Logout");
            option = MenuTool.getMenuOption(3, "Enter your option: ");
            //change to this if have all cases
            switch (option) {
                case 1 -> ViewMedicineTransaction();
//                case 2 -> ;
                case 3 -> logout();
            }
            switch (option) {
                case 1:
                    ViewMedicineTransaction();
                    break;
                case 2:
                    // Implement purchase medicine functionality here
                    break;
                case 3:
                    logout();
                    break;
            }
        } while (option != 3);
    }

    public void viewOrigin(int transactionIndex) {
        System.out.println("\nView Origin");
        System.out.println("--------------------------");
        Transaction selectedTransaction = StatusContainer.currentUser.getTransactions().get(transactionIndex - 1);

        // View origin details based on role
        viewOriginDetails(selectedTransaction, 1, "Manufacturer");
        viewOriginDetails(selectedTransaction, 2, "Distributor");
        viewOriginDetails(selectedTransaction, 3, "Healthcare Organisation");
    }
}