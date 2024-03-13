package msts;

import msts.menu.MainMenu;
import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        System.out.println("Hello BCD");
        MainMenu mainMenu = MainMenu.getMenu();
        mainMenu.generateMainMenu();
    }
}
