package msts.menu;

import java.util.Scanner;

public class MenuTool {

    static Scanner scanner = new Scanner(System.in);

    public static int getMenuOption(int max, String prompt) {
        String option;
        while (true) {
            System.out.print(prompt);
            option = scanner.nextLine();
            if(!option.isEmpty()){
                int opt = Integer.parseInt(option);
                if(opt > 0 && opt <= max){
                    return opt;
                }
            }
            System.out.println("Invalid input, please try again\n");
        }
    }

    public static String getStringInput(String prompt){
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if(!input.isEmpty()){
                return input;
            }
            System.out.println("Invalid input, please try again\n");
        }
    }

    public static int getIntegerInput(String prompt){
        while (true) {
            String input = getStringInput(prompt);
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please try again\n");
            }
        }
    }

    public static String getEmailInput(){
        while (true) {
            String input = getStringInput("Enter email: ");
            if(input.contains("@") && input.contains(".")){
                return input;
            }
            System.out.println("Invalid email, please try again\n");
        }
    }

    public static Long getContactNum() {
        while (true) {
            String input = getStringInput("Enter contact number: ");
            try {
                return Long.parseLong(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please try again\n");
            }
        }
    }
}
