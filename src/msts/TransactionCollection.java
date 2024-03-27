package msts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TransactionCollection implements Serializable {
    public String merkleRoot;
    public List<Transaction> transactionList;

    public TransactionCollection(){transactionList = new ArrayList<>();}

    public void add(Transaction transaction){transactionList.add(transaction);}

    public String getMerkleRoot() {
        return merkleRoot;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    @Override
    public String toString() {
        return "TransactionCollection{" +
                "merkleRoot='" + merkleRoot + '\'' +
                ", transactionList=" + transactionList +
                '}';
    }
}
