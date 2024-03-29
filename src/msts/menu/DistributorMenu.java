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
        System.out.println("\nOrder Medicine From Manufacturer");
        System.out.println("-------------------------------");
    }

}
