package msts;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Header implements Serializable {
    public Integer index = Integer.valueOf(0);
    public String currentHash;
    public String previousHash;
    public long timestamp;
    public String merkleRoot = "0";
    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "Header{" + "\t" +
                "index=" + index + ",\n\t" +
                " currentHash='" + currentHash + '\'' + ",\n\t" +
                " previousHash='" + previousHash + '\'' + ",\n\t" +
                " timestamp=" + timestamp + ",\n\t" +
                '}';
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getCurrentHash() {
        return currentHash;
    }

    public void setCurrentHash(String currentHash) {
        this.currentHash = currentHash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getMerkleRoot() {
        return merkleRoot;
    }

    public void calculateMerkleRoot(ArrayList<Transaction> transactions) {
        MerkleTree merkleTree = MerkleTree.getInstance(transactions);
        merkleTree.build();
        this.merkleRoot = merkleTree.getRoot();
    }
}
