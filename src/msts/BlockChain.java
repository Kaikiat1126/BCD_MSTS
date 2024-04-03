package msts;

import com.google.gson.GsonBuilder;
import jdk.jshell.Snippet;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

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

    private void createNewBlock(Transaction transaction) {
        Block newBlock = new Block();  // create new block
        newBlock.getHeader().setIndex(chain.size());
        newBlock.getHeader().setPreviousHash(chain.getLast().getHeader().currentHash);
        newBlock.addTransaction(transaction);
        newBlock.calculateMerkleRoot();
        addNewBlock(newBlock);
    }

    public void addNewTransaction(Transaction transaction) {
        boolean verified = transaction.verify(); //verify transaction
        if (!verified) {
            System.out.println("Transaction verification failed");
            return;
        }
        boolean isFull = chain.getLast().isFull();
        if (isFull) {
            chain.getLast().calculateCurrentHash();
            persist();
            createNewBlock(transaction);
        } else {
            chain.getLast().addTransaction(transaction);
            chain.getLast().calculateMerkleRoot();
            persist();
        }
    }

    private void addNewBlock(Block newBlock){
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

    public Block getBlock(Transaction transaction) {
        // Find out the block that contains the transaction and return the block
        for (Block block : chain) {
            if (block != null) {
                List<Transaction> transactions = block.getTransactions();
                if (transactions != null) {
                    for (Transaction t : transactions) {
                        if (t != null && t.getTransactionId().equals(transaction.getTransactionId())) {
                            return block;
                        }
                    }
                }
            }
        }
        return null;
    }
}
