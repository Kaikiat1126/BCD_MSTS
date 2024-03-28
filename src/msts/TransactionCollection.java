package msts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TransactionCollection implements Serializable {
    public List<Transaction> transactionList;

    public TransactionCollection(){transactionList = new ArrayList<>();}

    public void add(Transaction transaction){transactionList.add(transaction);}

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public String calculateMerkleRoot() {
        MerkleTree merkleTree = MerkleTree.getInstance(transactionList);
        merkleTree.build();
        return merkleTree.getRoot();
    }
}
