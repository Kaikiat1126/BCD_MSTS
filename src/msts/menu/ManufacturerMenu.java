package msts.menu;

import msts.StatusContainer;
import msts.obj.Manufacturer;
import msts.obj.Medicine;


import java.util.ArrayList;

public class ManufacturerMenu extends StackholderMenu {

    static ManufacturerMenu instance = null;

    public static ManufacturerMenu getMenu() {
        if (instance == null) {
            instance = new ManufacturerMenu();
        }
        return instance;
    }

    public void generateMenu() {
        System.out.println("\nManufacturer Menu");
        System.out.println("1. Create New Medicine Batch");
        System.out.println("2. Add New Medicine");
        System.out.println("3. View Medicine Transaction");
        System.out.println("4. Logout");
        int option = MenuTool.getMenuOption(4, "Enter your option: ");
        switch (option) {
            case 1:
                createNewMedicineBatch();
                break;
            case 2:
                // Implement add new medicine functionality here
                break;
            case 3:
                ViewMedicineTransaction();
                break;
            case 4:
                logout();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void createNewMedicineBatch() {
        System.out.println("\nCreate New Medicine Batch");
        System.out.println("--------------------------");
        System.out.println("Select medicine to create batch for:");
        Manufacturer manufacturer = (Manufacturer) StatusContainer.currentUser;
        ArrayList<Medicine> medicines = manufacturer.getAllMedicine();
        for (int i = 0; i < medicines.size(); i++) {
            System.out.println((i + 1) + ". " + medicines.get(i).getName());
        }

        int option = MenuTool.getMenuOption(medicines.size(), "Enter your option: ");
        int medicineID = medicines.get(option - 1).getMedicineID();
        int quantity = MenuTool.getIntegerInput("Enter quantity: ");
        String additionalInfo = MenuTool.getStringInput("Enter additional information: ");

        manufacturer.createNewBatch(medicineID, quantity, additionalInfo);
    }
}
