package msts;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

public class Block implements Serializable {
    private Header header;
    private TransactionCollection transactions;
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
        transactions = new TransactionCollection();
        header.setTimestamp(new Timestamp(System.currentTimeMillis()).getTime());
        header.setPreviousHash(previousHash);
        calculateCurrentHash();
    }

    public void calculateCurrentHash() {
        String blockHash = Hasher.sha256Salt(
                header.index + header.previousHash + header.timestamp + transactions + merkleRoot
        );
        header.setCurrentHash(blockHash);
    }

    public void calculateMerkleRoot() {
        this.merkleRoot = transactions.calculateMerkleRoot();
    }

    public TransactionCollection getTransactions() {
        return transactions;
    }

    public void setTransactions(TransactionCollection transactions) {
        this.transactions = transactions;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }
}
