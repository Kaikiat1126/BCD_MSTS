package msts.menu;

import msts.Authentication;
import msts.StatusContainer;

import java.util.Scanner;

public class MainMenu {
    Scanner scanner = new Scanner(System.in);
    static MainMenu mainMenu = null;
    public ManufacturerMenu manufacturerMenu;
    public DistributorMenu  distributorMenu;
    public HealthcareOrganisationMenu healthcareOrganisationMenu;
    public PatientMenu patientMenu;

    private MainMenu() {}

    public static MainMenu getMenu(){
        if(mainMenu == null)
            mainMenu = new MainMenu();
        return mainMenu;
    }
    public void generateMainMenu() {
        System.out.println("\nMain menu");
        System.out.println("--------------------------");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.println("--------------------------");
        int option = MenuTool.getMenuOption(3, "Enter your option: ");
        switch (option) {
            case 1:
                login();
                break;
            case 2:
                register();
                break;
            case 3:
                System.out.println("Exiting...");
                System.exit(0);
                break;
        }
    }

    private void login() {
        String[] credentials = new String[2];
        System.out.println("\nLogin");
        System.out.println("--------------------------");
        credentials[0] = MenuTool.getStringInput("Enter username: ");
        credentials[1] = MenuTool.getStringInput("Enter password: ");
        StatusContainer.currentUser = Authentication.assignUserLogin(credentials);
        if (StatusContainer.currentUser != null) {
            System.out.println("\nLogin successful");
            verifyCurrentUser();
        } else {
            System.out.println("\nInvalid credentials! Please try again.");
            login();
        }
    }

    private void verifyCurrentUser() {
        int role = StatusContainer.currentUser.getRole();
        switch (role) {
            case 1:
                ManufacturerMenu.getMenu().generateMenu();
                break;
            case 2:
                DistributorMenu.getMenu().generateMenu();
                break;
            case 3:
                this.healthcareOrganisationMenu = new HealthcareOrganisationMenu();
                healthcareOrganisationMenu.generateMenu();
                break;
            case 4:
                PatientMenu.getMenu().generateMenu();
                break;
            default:
                System.out.println("Invalid role. Please try again.");
        }
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
                    this.healthcareOrganisationMenu = new HealthcareOrganisationMenu();
                    healthcareOrganisationMenu.generateMenu();
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
