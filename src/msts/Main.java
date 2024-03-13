package msts;

import msts.menu.MainMenu;
import java.sql.*;

public class Main {

    public static void main(String[] args) {
//        String pwd = Hasher.md5("kaikiat123");
//        System.out.println(pwd);
        MainMenu mainMenu = MainMenu.getMenu();
        mainMenu.generateMainMenu();
    }
}
