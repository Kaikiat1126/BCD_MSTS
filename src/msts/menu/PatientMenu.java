package msts.menu;

import msts.DigitalSignature;
import msts.StatusContainer;
import msts.Transaction;
import msts.obj.HealthcareOrganisation;
import msts.obj.Patient;
import msts.obj.User;

import java.util.ArrayList;
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
                case 2 -> purchaseMedicine();
                case 3 -> logout();
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

    public void purchaseMedicine(){
        Patient patient = (Patient) StatusContainer.currentUser;
        ArrayList<ArrayList<String>> medicines = patient.getMedicineBatchFromHealthcareOrganisation();
        if (medicines.isEmpty()) {
            System.out.println("\nNo Medicine Available to Purchase!");
            return;
        }

        System.out.println("\nPurchase Medicine From Healthcare Organisation");
        System.out.println("-------------------------------");
        System.out.printf("%-4s %-25s %-10s %-10s %-10s %-10s %-15s %-15s %-35s %-35s\n",
                "No.", "Healthcare Organisation", "Medicine", "Type", "Price", "Quantity", "Production Date", "Expiry Date", "Batch No", "Sub Batch Number");
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < medicines.size(); i++) {
            System.out.printf("%-4s %-25s %-10s %-10s %-10s %-10s %-15s %-15s %-35s %-35s\n",
                    i + 1, medicines.get(i).get(1), medicines.get(i).get(6), medicines.get(i).get(7), medicines.get(i).get(8),
                    medicines.get(i).get(9), medicines.get(i).get(4), medicines.get(i).get(5), medicines.get(i).get(3), medicines.get(i).get(10));
        }
        int option = MenuTool.getMenuOption(medicines.size(), "\nEnter your choice: ");
        ArrayList<String> selectedBatch = medicines.get(option-1);
        int maxQuantity = Integer.parseInt(medicines.get(option - 1).get(9));
        int quantity = MenuTool.getMenuOption( maxQuantity, "Enter Purchase Quantity: ", "Exceeded Available Quantity!");
        String additionalInfo = MenuTool.getStringInput("Enter additional information: ");
        selectedBatch.add(String.valueOf(quantity));
        selectedBatch.add(additionalInfo);

        patient.createNewTransaction(selectedBatch);
    }
}