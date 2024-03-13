package msts.menu;

public class DistributorMenu extends StackholderMenu {

    public static DistributorMenu instance = null;

    public static DistributorMenu getMenu() {
        if (instance == null) {
            instance = new DistributorMenu();
        }
        return instance;
    }

    public void generateMenu() {
        System.out.println("\nDistributor Menu");
        System.out.println("1. Order Medicine");
        System.out.println("2. View Medicine Transaction");
        System.out.println("3. Logout");
        int option = MenuTool.getMenuOption(3, "Enter your option: ");
        switch (option) {
            case 1:
                // Implement order medicine functionality here
                break;
            case 2:
                ViewMedicineTransaction();
                break;
            case 3:
                logout();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public void buyMedicine(){

    }

}
