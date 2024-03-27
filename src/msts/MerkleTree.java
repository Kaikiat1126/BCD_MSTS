package msts;

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

    //generate merkle proof
    public List<String> generateProof(String transaction) {
        List<String> proof = new ArrayList<>();
        List<String> tempLst = new ArrayList<>();
        for (Transaction t : this.transactionList) {
            tempLst.add(t.toString());
        }

        List<String> hashes = genTransactionHashLst(tempLst);
        while( hashes.size() != 1 ) {
            hashes = genTransactionHashLst(hashes);
            if( hashes.size() == 1 ) {
                break;
            }
            if( hashes.size() % 2 != 0 ) {
                hashes.add(hashes.get(hashes.size()-1));
            }
            List<String> temp = new ArrayList<>();
            for( int i = 0; i < hashes.size(); i+=2 ) {
                if( hashes.get(i).equals(transaction) ) {
                    proof.add(hashes.get(i+1));
                } else if( hashes.get(i+1).equals(transaction) ) {
                    proof.add(hashes.get(i));
                }
                temp.add(Hasher.sha256(hashes.get(i).concat(hashes.get(i+1))));
            }
            hashes = temp;
        }
        return proof;
    }
}