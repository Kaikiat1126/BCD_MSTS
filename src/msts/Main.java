package msts;

import msts.menu.MainMenu;

import java.io.File;

public class Main {

    public static void initializeBlockChain() {
        BlockChain blockChain;
        String chainFile = "master/blockchain.bin";
        boolean chainExists = new File(chainFile).exists();
        if (!chainExists) new File("master").mkdir();
        blockChain = BlockChain.getInstance(chainFile);
        if (!chainExists) blockChain.genesis();
        StatusContainer.blockChain = blockChain;
//        blockChain.distribute();
    }

    public static void main(String[] args) throws Exception {
        initializeBlockChain();
        MainMenu mainMenu = MainMenu.getMenu();
        mainMenu.generateMainMenu();
    }
}
