package msts.menu;

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
                // Implement create new medicine batch functionality here
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
}
