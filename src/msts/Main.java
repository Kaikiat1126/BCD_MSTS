package msts;

import msts.menu.MainMenu;

import java.io.File;

public class Main {

    public static void initializeBlockChain() {
        BlockChain blockChain = BlockChain.getInstance("master/blockchain.bin");
        if (!new File("master").exists()) {
            new File("master").mkdir();
            blockChain.genesis();
        }
//        blockChain.distribute();
        StatusContainer.blockChain = blockChain;
    }

    public static void main(String[] args) throws Exception {
        initializeBlockChain();
        MainMenu mainMenu = MainMenu.getMenu();
        mainMenu.generateMainMenu();
    }
}
