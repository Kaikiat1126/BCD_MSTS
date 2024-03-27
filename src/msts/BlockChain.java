package msts;

import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.LinkedList;

public class BlockChain implements Serializable {
    private static LinkedList<Block> db;
    private static volatile BlockChain _instance;
    public String chainFile;

    public static BlockChain getInstance(String chainFile) {
        if (_instance == null) {
            synchronized (BlockChain.class) {
                if (_instance == null) {
                    _instance = new BlockChain(chainFile);
                }
            }
        }
        return _instance;
    }

    public BlockChain(String chainFile) {
        this.chainFile = chainFile;
        db = new LinkedList<>();
    }

    public void genesis() {
        Block genesis = new Block("0");
        db.add(genesis);
        persist();
    }

    public void nextBlock(Block newBlock){
        db = get();
        db.add(newBlock);
        persist();
    }

    public LinkedList<Block> get(){
        try (FileInputStream fis = new FileInputStream(this.chainFile);
             ObjectInputStream ois  = new ObjectInputStream(fis)){
            return (LinkedList<Block>) ois.readObject();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private void persist(){
        try (FileOutputStream fos = new FileOutputStream(this.chainFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)){
            oos.writeObject(db);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void distribute(){
        String chain = new GsonBuilder().setPrettyPrinting().create().toJson(db);
        System.out.println(chain);
    }
}
