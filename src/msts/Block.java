package msts;

import java.sql.Timestamp;

public class  Block {
    //    private TransactionCollection transactions;
    private Header header;
    private static final long serialVersionUID = 1L;

    public Block(String previousHash) {
        header = new Header();
        header.setTimestamp(new Timestamp(System.currentTimeMillis()).getTime());
        header.setPreviousHash(previousHash);
        String info = String.join("+", Integer.toString(header.getIndex()),
                Long.toString(header.getTimestamp()),
                header.getPreviousHash());
        String blockHash = Hasher.sha256(info);
        header.setCurrentHash(blockHash);
    }

//    public TransactionCollection getTransactions() {
//        return transactions;
//    }
//
//    public void setTransactions(TransactionCollection transactions) {
//        this.transactions = transactions;
//    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

//    @Override
//    public String toString() {
//        return "Block{" +
//                "transactions=" + transactions + ",\n" +
//                " header=" + header + "\n" +
//                '}';
//    }
}
