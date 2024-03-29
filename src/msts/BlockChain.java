package msts;

import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.LinkedList;

public class BlockChain implements Serializable {
    private static LinkedList<Block> chain;
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
        chain = getBlocks();
        if (chain == null) chain = new LinkedList<>();
    }

    public void genesis() {
        Block genesis = new Block("0");
        chain.add(genesis);
        persist();
    }

    public void addNewBlock(Transaction transaction) {
        Block newBlock = new Block();
        newBlock.getHeader().setIndex(chain.size());
        newBlock.getHeader().previousHash = chain.getLast().getHeader().currentHash;
        newBlock.setTransactionCollection(chain.getLast().getTransactionCollection());
        newBlock.add(transaction);
        newBlock.calculateMerkleRoot();
        newBlock.calculateCurrentHash();
        addNewBlock(newBlock);
    }

    public void addNewBlock(Block newBlock){
        chain = getBlocks();
        chain.add(newBlock);
        persist();
    }

    public LinkedList<Block> getBlocks(){
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
            oos.writeObject(chain);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void distribute(){
        String blockchain = new GsonBuilder().setPrettyPrinting().create().toJson(chain);
        System.out.println(blockchain);
    }
}
