package MenuGenerator;

import java.util.Scanner;

public class ManufacturerMenu extends StackholderMenu {
//    public ManufacturerMenu(){
//        super();
//    }
    public void generateMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Manufacturer Menu");
            System.out.println("1. Create New Medicine Batch");
            System.out.println("2. Add New Medicine");
            System.out.println("3. View Medicine Transaction");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
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
                    System.out.println("Returning to Main Menu...");
                    this.mainMenu.generateMainMenu();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }

}
