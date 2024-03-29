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
        int option;
        do {
            System.out.println("\nManufacturer Menu");
            System.out.println("1. Create New Medicine Batch");
            System.out.println("2. Add New Medicine");
            System.out.println("3. View Medicine Transaction");
            System.out.println("4. Logout");
            option = MenuTool.getMenuOption(4, "Enter your option: ");
            switch (option) {
                case 1 -> createNewMedicineBatch();
                case 2 -> addNewMedicine();
                case 3 -> ViewMedicineTransaction();
                case 4 -> logout();
            }
        } while (option != 4);
    }

    private void createNewMedicineBatch() {
        Manufacturer manufacturer = (Manufacturer) StatusContainer.currentUser;
        ArrayList<Medicine> medicines = manufacturer.getAllMedicine();
        if (medicines.isEmpty()) {
            System.out.println("\nNo Medicine Available to Create Batch For!");
            return;
        }

        System.out.println("\nCreate New Medicine Batch");
        System.out.println("--------------------------");
        System.out.println("Select medicine to create batch for:");
        for (int i = 0; i < medicines.size(); i++) {
            System.out.println((i + 1) + ". " + medicines.get(i).getName());
        }

        int option = MenuTool.getMenuOption(medicines.size(), "Enter your option: ");
        int medicineID = medicines.get(option - 1).getMedicineID();
        int quantity = MenuTool.getIntegerInput("Enter quantity: ");
        String additionalInfo = MenuTool.getStringInput("Enter additional information: ");

        manufacturer.createNewBatch(medicineID, quantity, additionalInfo);
    }

    private void addNewMedicine() {
        System.out.println("\nAdd New Medicine");
        System.out.println("--------------------------");
        Manufacturer manufacturer = (Manufacturer) StatusContainer.currentUser;

        String name = MenuTool.getStringInput("Enter Medicine Name: ");
        String type = MenuTool.getStringInput("Enter Medicine Type: ");
        int price = MenuTool.getIntegerInput("Enter Medicine Price: ");

        manufacturer.addNewMedicine(name, type, price);

        System.out.println("\nNew Medicine Added Successful!");
    }
}
