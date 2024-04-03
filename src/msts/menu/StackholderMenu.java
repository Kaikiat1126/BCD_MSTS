package msts.menu;

import msts.MerkleTree;
import msts.StatusContainer;
import msts.Transaction;
import msts.obj.User;

import java.util.List;

public abstract class StackholderMenu {

    public static StackholderMenu instance = null;

    public static StackholderMenu getMenu() {
        if (instance == null) {
            instance = new StackholderMenu() {
                @Override
                public void generateMenu() {
                    throw new UnsupportedOperationException("Not supported yet.");
                }

                @Override
                public void viewOrigin(int transactionIndex) {
                    throw new UnsupportedOperationException("Not supported yet.");
                }

            };
        }
        return instance;
    }

    public StackholderMenu(){}
    public abstract void generateMenu();

    public abstract void viewOrigin(int transactionIndex);

    protected void logout() {
        System.out.println("Logging out...");
        StatusContainer.currentUser = null;
        MainMenu.getMenu().generateMainMenu();
    }

    public void ViewMedicineTransaction(){
        User user = StatusContainer.currentUser;
        List<Transaction> transactions = user.getTransactions();

        if(transactions.isEmpty()){
            System.out.println("\nNo Medicine Transaction Available!");
            return;
        }
        System.out.println("\nView Medicine Transaction");
        System.out.println("--------------------------");
        System.out.printf("%-4s %-50s %-12s %-8s %-20s %-10s\n", "No.", "TxId", "Medicine ID", "Quantity", "Transaction Date", "Verify Status");
        System.out.println("--------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < transactions.size(); i++) {
            try {
                Transaction transaction = transactions.get(i);
                boolean isTransactionValid = transaction.verifyTransaction();
                System.out.printf("%-4d %-50s %-12s %-8s %-20s %-10s\n",
                        i + 1, transaction.getTransactionId(),
                        transaction.getMedicineId(),
                        transaction.getQuantity(),
                        transaction.getTransactionDate(),
                        isTransactionValid ? "Valid" : "Invalid");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("--------------------------------------------------------------------------------------------------------");
        int transactionIndex = MenuTool.getMenuOption(transactions.size()+1, "Select transaction to view medicine details: ");
        viewTransactionDetails(transactionIndex);
        viewOrigin(transactionIndex);
    }

    protected void viewTransactionDetails(int transactionIndex){
        Transaction transaction = StatusContainer.currentUser.getTransactions().get(transactionIndex - 1);
        System.out.println("\nTransaction Details");
        System.out.println("--------------------------");
        System.out.println("Medicine ID     : " + transaction.getMedicineId());
        System.out.println("Quantity        : " + transaction.getQuantity());
        System.out.println("Transaction Date: " + transaction.getTransactionDate());
        System.out.println("Batch Number    : " + transaction.getBatchNumber());
        System.out.println("Sub Batch Number: " + transaction.getSubBatchNumber());
        System.out.println("Production Date : " + transaction.getProductionDate());
        System.out.println("Expiry Date     : " + transaction.getExpiryDate());
        System.out.println("Additional Info : " + transaction.getAdditionalInfo());
    }

    protected void viewOriginDetails(Transaction transaction, int role, String roleName) {
        User origin = StatusContainer.currentUser.getOrigin(transaction, role);
        if (origin != null) {
            System.out.println(roleName + ":");
            System.out.println("User ID : " + origin.getUserId());
            System.out.println("Username: " + origin.getUserName());
            System.out.println("Email   : " + origin.getEmail());
            System.out.println("Phone   : " + origin.getContactNumber());
            System.out.println();
        } else {
            System.out.println(roleName + " not found.");
        }
    }
}
