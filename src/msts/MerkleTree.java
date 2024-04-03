package msts;

import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;

public class MerkleTree {
    private List<Transaction> transactionList;
    private String root = "0";

    public String getRoot() {
        return root;
    }

    private MerkleTree(List<Transaction> transactionList) {
        super();
        this.transactionList = transactionList;
    }

    private static MerkleTree instance;
    public static MerkleTree getInstance( List<Transaction> transactionList ) {
        if( instance == null ) {
            return new MerkleTree(transactionList);
        }
        return instance;
    }

    public void build() {
        List<String> tempLst = new ArrayList<>();

        for (Transaction transaction : this.transactionList) {
            tempLst.add(transaction.toString());
        }

        List<String> hashes = genTransactionHashLst(tempLst);
        while(  hashes.size() != 1 ) {
            hashes = genTransactionHashLst(hashes);
        }
        this.root = hashes.get(0);
    }

    private List<String> genTransactionHashLst(List<String> transactionList) {
        List<String> hashLst = new ArrayList<>();
        int i = 0;
        while( i < transactionList.size() ) {
            String left = transactionList.get(i);
            i++;

            String right = "";
            if( i != transactionList.size() ) right = transactionList.get(i);

            String hash = Hasher.sha256(left.concat(right));
            hashLst.add(hash);
            i++;
        }
        return hashLst;
    }

    public Boolean generateProof(String merkleRoot) {
        List<String> tempLst = new ArrayList<>();
        for (Transaction transaction : this.transactionList) {
            tempLst.add(transaction.toString());
        }

        List<String> hashes = genTransactionHashLst(tempLst);
        while(  hashes.size() != 1 ) {
            hashes = genTransactionHashLst(hashes);
        }
        return hashes.get(0).equals(merkleRoot);
    }
}