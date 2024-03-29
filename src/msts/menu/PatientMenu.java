package msts.menu;

import msts.DigitalSignature;
import msts.StatusContainer;
import msts.Transaction;
import msts.obj.Patient;

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

    public void ViewMedicineTransaction(){
        Patient patient = (Patient) StatusContainer.currentUser;
        List<Transaction> transactions = patient.getTransactions();

        if(transactions.isEmpty()){
            System.out.println("\nNo Medicine Transaction Available!");
            return;
        }
        System.out.println("\nView Medicine Transaction");
        System.out.println("--------------------------");
        System.out.printf("%-4s %-15s %-12s %-8s %-15s %-10s\n", "No.", "TxId", "Medicine ID", "Quantity", "Transaction Date", "Signature");
        System.out.println("-------------------------------------------------");
        for (int i = 0; i < transactions.size(); i++) {
            try {
                Transaction transaction = transactions.get(i);
                Boolean isTransactionSignatureValid = DigitalSignature.getInstance().isTextAndSignatureValid(
                        transaction.getTransactionId(),
                        transaction.getDigitalSignature().getBytes(), // Convert signature string to byte array
                        patient.getUserById(transaction.getSender()).getPublicKey()
                );
                System.out.printf("%-4d %-15s %-12s %-8s %-15s %-10s\n",
                        i + 1, transaction.getTransactionId(),
                        transaction.getMedicineId(),
                        transaction.getQuantity(),
                        transaction.getTransactionDate(),
                        isTransactionSignatureValid ? "Valid" : "Invalid");
                System.out.println();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Select transaction to view medicine details:");
        int transactionIndex = MenuTool.getMenuOption(transactions.size()+1, "Enter transaction index: ");
        viewTransactionDetails(transactionIndex);
        viewOrigin(transactionIndex);
    }

    private void viewOrigin(int transactionIndex) {
        System.out.println("\nView Origin");
        System.out.println("--------------------------");
        Transaction selectedTransaction = StatusContainer.currentUser.getTransactions().get(transactionIndex - 1);

        // View origin details based on role
        viewOriginDetails(selectedTransaction, 1, "Manufacturer");
        viewOriginDetails(selectedTransaction, 2, "Distributor");
        viewOriginDetails(selectedTransaction, 3, "Healthcare Organisation");
    }
}