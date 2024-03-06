package MenuGenerator;

import java.util.Scanner;

public class DistributorMenu extends StackholderMenu {

    public void generateMenu() {

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Distributor Menu");
            System.out.println("1. Order Medicine");
            System.out.println("2. View Medicine Transaction");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Implement order medicine functionality here
                    break;
                case 2:
                    ViewMedicineTransaction();
                    break;
                case 3:
                    System.out.println("Returning to Main Menu...");
                    this.mainMenu.generateMainMenu();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);
    }

    public void buyMedicine(){

    }

    public void ViewMedicineTransaction(){

    }
}
