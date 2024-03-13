package msts.menu;

public class PatientMenu extends StackholderMenu {

    static PatientMenu instance = null;

    public static PatientMenu getMenu() {
        if (instance == null) {
            instance = new PatientMenu();
        }
        return instance;
    }

    public void generateMenu() {
        System.out.println("\nPatient Menu");
        System.out.println("1. View Medicine Transaction");
        System.out.println("2. Purchase Medicine");
        System.out.println("3. Logout");
        int option = MenuTool.getMenuOption(3, "Enter your option: ");
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
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public void ViewMedicineTransaction(){

    }
}
