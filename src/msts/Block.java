package msts;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Block implements Serializable {
    private Header header;
    private ArrayList<Transaction> transactions;
    @Serial
    private static final long serialVersionUID = 1L;

    public Block() {
        header = new Header();
        header.setTimestamp(new Timestamp(System.currentTimeMillis()).getTime());
        transactions = new ArrayList<>();
    }

    //for genesis block only
    public Block(String previousHash) {
        header = new Header();
        header.setTimestamp(new Timestamp(System.currentTimeMillis()).getTime());
        header.setPreviousHash(previousHash);
        calculateCurrentHash();
    }

    public void calculateCurrentHash() {
        String blockHash = Hasher.sha256(
                header.index + header.previousHash + header.timestamp + transactions + header.getMerkleRoot()
        );
        header.setCurrentHash(blockHash);
    }

    public void calculateMerkleRoot() {
        this.header.calculateMerkleRoot(transactions);
    }

    public ArrayList<Transaction> getTransactions() { return transactions; }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public boolean isFull() {
        return transactions == null || transactions.size() == 10;
    }

    public Header getHeader() {
        return header;
    }
}
