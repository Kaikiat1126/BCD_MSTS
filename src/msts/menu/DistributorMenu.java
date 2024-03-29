package msts.menu;

import msts.StatusContainer;
import msts.obj.Distributor;

import java.util.ArrayList;

public class DistributorMenu extends StackholderMenu {

    public static DistributorMenu instance = null;

    public static DistributorMenu getMenu() {
        if (instance == null) {
            instance = new DistributorMenu();
        }
        return instance;
    }

    public void generateMenu() {
        int option;
        do {
            System.out.println("\nDistributor Menu");
            System.out.println("1. Order Medicine");
            System.out.println("2. View Medicine Transaction");
            System.out.println("3. Logout");
            option = MenuTool.getMenuOption(3, "Enter your option: ");
            switch (option) {
                case 1 -> orderMedicine();
                case 2 -> ViewMedicineTransaction();
                case 3 -> logout();
            }
        } while (option != 3);
    }

    public void orderMedicine(){
        Distributor distributor = (Distributor) StatusContainer.currentUser;
        ArrayList<ArrayList<String>> medicines = distributor.getMedicineBatchFromManufacturer();
        if (medicines.isEmpty()) {
            System.out.println("\nNo Medicine Available to Order!");
            return;
        }

        System.out.println("\nOrder Medicine From Manufacturer");
        System.out.println("-------------------------------");
        System.out.printf("%-4s %-15s %-18s %-10s %-10s %-10s %-15s %-15s %-25s\n",
                "No.", "Manufacturer", "Medicine", "Type", "Price", "Quantity", "Production Date", "Expiry Date", "Batch No");
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < medicines.size(); i++) {
            System.out.printf("%-4d %-15s %-18s %-10s %-10s %-10s %-15s %-15s %-25s\n",
                    i + 1, medicines.get(i).get(1), medicines.get(i).get(6), medicines.get(i).get(7), medicines.get(i).get(8),
                    medicines.get(i).get(9), medicines.get(i).get(4), medicines.get(i).get(5), medicines.get(i).get(3));
        }
        int option = MenuTool.getMenuOption(medicines.size(), "\nEnter your choice: ");
        ArrayList<String> selectedBatch = medicines.get(option-1);
        String additionalInfo = MenuTool.getStringInput("Enter additional information: ");
        selectedBatch.add(additionalInfo);

        distributor.createNewTransaction(selectedBatch);
    }

}
