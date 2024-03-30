package msts.menu;

import msts.StatusContainer;
import msts.obj.Distributor;
import msts.obj.HealthcareOrganisation;

import java.util.ArrayList;

public class HealthcareOrganisationMenu extends StackholderMenu {

    static HealthcareOrganisationMenu instance = null;

    public static HealthcareOrganisationMenu getMenu() {
        if (instance == null) {
            instance = new HealthcareOrganisationMenu();
        }
        return instance;
    }

    public void generateMenu() {
        int option;
        do {
            System.out.println("\nHealthcare Organisation Menu");
            System.out.println("1. View Medicine Transaction");
            System.out.println("2. Procure Medicine");
            System.out.println("3. Logout");
            option = MenuTool.getMenuOption(3, "Enter your option: ");
            switch (option) {
                case 1 -> ViewMedicineTransaction();
                case 2 -> procureMedicine();
                case 3 -> logout();
            }
        } while (option != 3);
    }

    public void procureMedicine(){
        HealthcareOrganisation healthcareOrganisation = (HealthcareOrganisation) StatusContainer.currentUser;
        ArrayList<ArrayList<String>> medicines = healthcareOrganisation.getMedicineBatchFromDistributor();
        if (medicines.isEmpty()) {
            System.out.println("\nNo Medicine Available to Procure!");
            return;
        }

        System.out.println("\nProcure Medicine From Distributor");
        System.out.println("-------------------------------");
        System.out.printf("%-4s %-15s %-18s %-10s %-10s %-10s %-15s %-15s %-25s\n",
                "No.", "Distributor", "Medicine", "Type", "Price", "Quantity", "Production Date", "Expiry Date", "Batch No");
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < medicines.size(); i++) {
            System.out.printf("%-4d %-15s %-18s %-10s %-10s %-10s %-15s %-15s %-25s\n",
                    i + 1, medicines.get(i).get(1), medicines.get(i).get(6), medicines.get(i).get(7), medicines.get(i).get(8),
                    medicines.get(i).get(9), medicines.get(i).get(4), medicines.get(i).get(5), medicines.get(i).get(3));
        }
        int option = MenuTool.getMenuOption(medicines.size(), "\nEnter your choice: ");
        ArrayList<String> selectedBatch = medicines.get(option-1);
        int maxQuantity = Integer.parseInt(medicines.get(option - 1).get(9));
        int quantity = MenuTool.getMenuOption( maxQuantity, "Enter Procurement Quantity: ", "Exceeded Available Quantity!");
        String additionalInfo = MenuTool.getStringInput("Enter additional information: ");
        selectedBatch.add(String.valueOf(quantity));
        selectedBatch.add(additionalInfo);

        healthcareOrganisation.createNewTransaction(selectedBatch);
    }
}
