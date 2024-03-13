package msts.menu;

public class HealthcareOrganisationMenu extends StackholderMenu {

    static HealthcareOrganisationMenu instance = null;

    public static HealthcareOrganisationMenu getMenu() {
        if (instance == null) {
            instance = new HealthcareOrganisationMenu();
        }
        return instance;
    }

    public void generateMenu() {
        System.out.println("\nHealthcare Organisation Menu");
        System.out.println("1. View Medicine Transaction");
        System.out.println("2. Procure Medicine");
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
}
