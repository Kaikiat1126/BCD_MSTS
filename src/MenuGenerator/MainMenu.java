package MenuGenerator;

import java.util.Scanner;

public class MainMenu {
    Scanner scanner;
    static MainMenu mainMenu;
    public ManufacturerMenu manufacturerMenu;
    public DistributorMenu  distributorMenu;
    public HeathcareOrganisationMenu heathcareOrganisationMenu;
    public PatientMenu patientMenu;

    private MainMenu() {
        scanner = new Scanner(System.in);
    }

    public static MainMenu getMenu(){
        if(mainMenu == null)
            mainMenu = new MainMenu();
        return mainMenu;
    }
    public void generateMainMenu() {
        int choice;
        do {
            System.out.println("Main menu");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);
    }

    //只是为了方便跳转用的
    private void login() {
        int choice;
        do {
            System.out.println("Login menu");
            System.out.println("1. Manufacturer");
            System.out.println("2. Distributor");
            System.out.println("3. Healthcare Organisation");
            System.out.println("4. Patient");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    this.manufacturerMenu = new ManufacturerMenu();
                    manufacturerMenu.generateMenu();
                    break;
                case 2:
                    this.distributorMenu = new DistributorMenu();
                    distributorMenu.generateMenu();
                    break;
                case 3:
                    this.heathcareOrganisationMenu = new HeathcareOrganisationMenu();
                    heathcareOrganisationMenu.generateMenu();
                    break;
                case 4:
                    this.patientMenu = new PatientMenu();
                    patientMenu.generateMenu();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);
        // Implement login functionality here
    }

    private void register() {
        int choice;
        System.out.println("Register menu");
        do {
            System.out.println("1. Manufacturer");
            System.out.println("2. Distributor");
            System.out.println("3. Healthcare Organisation");
            System.out.println("4. Patient");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    this.manufacturerMenu = new ManufacturerMenu();
                    manufacturerMenu.generateMenu();
                    break;
                case 2:
                    this.distributorMenu = new DistributorMenu();
                    distributorMenu.generateMenu();
                    break;
                case 3:
                    this.heathcareOrganisationMenu = new HeathcareOrganisationMenu();
                    heathcareOrganisationMenu.generateMenu();
                    break;
                case 4:
                    this.patientMenu = new PatientMenu();
                    patientMenu.generateMenu();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);
        // Implement registration functionality here
    }
}
