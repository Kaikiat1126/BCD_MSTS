package msts.menu;

import msts.Authentication;
import msts.Registration;
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
        loginProcess(credentials);
    }

    private void loginProcess(String[] credentials) {
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
                HealthcareOrganisationMenu.getMenu().generateMenu();
                break;
            case 4:
                PatientMenu.getMenu().generateMenu();
                break;
            default:
                System.out.println("Invalid role. Please try again.");
        }
    }

    private void register() {
        System.out.println("\nRegister menu");
        System.out.println("--------------------------");
        System.out.println("Select your role");
        System.out.println("1. Manufacturer");
        System.out.println("2. Distributor");
        System.out.println("3. Healthcare Organisation");
        System.out.println("4. Patient");
        int role = MenuTool.getMenuOption(4, "Enter your option: ");
        String username = MenuTool.getStringInput("Enter username: ");
        String password = MenuTool.getStringInput("Enter password: ");
        String email = MenuTool.getEmailInput();
        Long contact = MenuTool.getContactNum();
        try {
            int status = Registration.register(username, password, role, email, contact);
            if (status == 1) {
                System.out.println("Registration successful");
                loginProcess(new String[]{username, password});
            } else {
                System.out.println("Registration failed");
                generateMainMenu();
            }
        } catch (Exception e) {
            System.out.println("Registration failed");
            generateMainMenu();
        }
    }

}
