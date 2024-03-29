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

    //generate merkle proof, check if the transaction is in the block
    public Pair<Boolean, List<String>> generateProof(String txid) {
        List<String> tempLst = new ArrayList<>();
        for (Transaction transaction : this.transactionList) {
            tempLst.add(transaction.toString());
        }

        List<String> hashes = genTransactionHashLst(tempLst);
        List<String> proof = new ArrayList<>();

        while (hashes.size() != 1) {
            if (hashes.contains(txid)) {
                proof.add(txid);
            }

            // Generate new level of hashes
            hashes = genTransactionHashLst(hashes);
        }

        // Final check for the last hash
        if (hashes.size() == 1 && txid.equals(hashes.get(0))) {
            proof.add(hashes.get(0));
            return new Pair<>(true, proof);
        }

        return new Pair<>(false, proof);
    }
}