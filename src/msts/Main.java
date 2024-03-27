package msts;

import msts.menu.MainMenu;
import java.sql.*;

public class Main {

    public static void main(String[] args) throws Exception {
        MainMenu mainMenu = MainMenu.getMenu();
        mainMenu.generateMainMenu();
    }
}
