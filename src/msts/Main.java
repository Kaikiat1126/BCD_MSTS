package msts;

import msts.menu.MainMenu;
import java.sql.*;

public class Main {

    public static void main(String[] args) {
        MainMenu mainMenu = MainMenu.getMenu();
        mainMenu.generateMainMenu();
    }
}
