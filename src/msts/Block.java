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
    private Transaction[] transactions;
    private String merkleRoot = "0";
    @Serial
    private static final long serialVersionUID = 1L;

    public Block() {
        header = new Header();
        header.setTimestamp(new Timestamp(System.currentTimeMillis()).getTime());
        transactions = new Transaction[10];
    }

    //for genesis block only
    public Block(String previousHash) {
        header = new Header();
        header.setTimestamp(new Timestamp(System.currentTimeMillis()).getTime());
        transactions = new Transaction[0];
        header.setPreviousHash(previousHash);
        calculateCurrentHash();
    }

    public void calculateCurrentHash() {
        String blockHash = Hasher.sha256(
                header.index + header.previousHash + header.timestamp + Arrays.toString(transactions) + merkleRoot
        );
        header.setCurrentHash(blockHash);
    }

    public void calculateMerkleRoot() {
        List<Transaction> transactionList = new ArrayList<>(Arrays.asList(transactions));
        transactionList.removeIf(Objects::isNull);
        MerkleTree merkleTree = MerkleTree.getInstance(transactionList);
        merkleTree.build();
        this.merkleRoot = merkleTree.getRoot();
    }

    public Transaction[] getTransactions() { return transactions; }

    public void addTransaction(Transaction transaction) {
        for (int i = 0; i < transactions.length; i++) {
            if (transactions[i] == null) {
                transactions[i] = transaction;
                break;
            }
        }
    }

    public boolean isFull() {
        for (Transaction transaction : transactions) {
            if (transaction == null) {
                return false;
            }
        }
        return true;
    }

    public Header getHeader() {
        return header;
    }
}
