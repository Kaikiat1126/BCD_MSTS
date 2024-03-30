package msts;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Block implements Serializable {
    private Header header;
    private List<Transaction> transactionCollection;
    private String merkleRoot = "0";
    @Serial
    private static final long serialVersionUID = 1L;

    public Block() {
        header = new Header();
        header.setTimestamp(new Timestamp(System.currentTimeMillis()).getTime());
    }

    //for genesis block only
    public Block(String previousHash) {
        header = new Header();
        transactionCollection = new ArrayList<>();
        header.setTimestamp(new Timestamp(System.currentTimeMillis()).getTime());
        header.setPreviousHash(previousHash);
        calculateCurrentHash();
    }

    public void calculateCurrentHash() {
        String blockHash = Hasher.sha256(
                header.index + header.previousHash + header.timestamp + transactionCollection + merkleRoot
        );
        header.setCurrentHash(blockHash);
    }

    public void calculateMerkleRoot() {
        MerkleTree merkleTree = MerkleTree.getInstance(transactionCollection);
        merkleTree.build();
        this.merkleRoot = merkleTree.getRoot();
    }

    public List<Transaction> getTransactionCollection() { return transactionCollection; }

    public void setTransactionCollection(List<Transaction> transactionCollection) {
        this.transactionCollection = transactionCollection;
    }

    public void add(Transaction transaction){ transactionCollection.add(transaction); }

    public Header getHeader() {
        return header;
    }
}
