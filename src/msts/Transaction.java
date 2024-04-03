package msts;

import org.javatuples.Pair;

import java.io.Serial;
import java.io.Serializable;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

public class Transaction implements Serializable {

    private String transactionId;
    private transient LocalDate transactionDate;
    private String sender;
    private String receiver;
    private int medicineId;
    private int quantity;
    private String batchNumber;
    private String subBatchNumber;
    private transient LocalDate productionDate;
    private transient LocalDate expiryDate;
    private String additionalInfo;
    private String digitalSignature;

    @Serial
    private static final long serialVersionUID = 1L;

    public Transaction(){};

    //create new batch medicine, new transaction request
    public Transaction(
            LocalDate transactionDate, String sender, String receiver,
            int medicineId, int quantity, String batchNumber, String subBatchNumber,
            LocalDate productionDate, LocalDate expiryDate, String additionalInfo
    ) {
        this.transactionDate = transactionDate;
        this.sender = sender;
        this.receiver = receiver;
        this.medicineId = medicineId;
        this.quantity = quantity;
        this.batchNumber = batchNumber;
        this.subBatchNumber = subBatchNumber;
        this.productionDate = productionDate;
        this.expiryDate = expiryDate;
        this.additionalInfo = additionalInfo;
        this.transactionId = calculateHash();
    }

    public Transaction(
            LocalDate transactionDate, String sender, String receiver,
            int medicineId, int quantity, String batchNumber, String subBatchNumber,
            LocalDate productionDate, LocalDate expiryDate, String additionalInfo,
            String digitalSignature
    ) {
        this.transactionDate = transactionDate;
        this.sender = sender;
        this.receiver = receiver;
        this.medicineId = medicineId;
        this.quantity = quantity;
        this.batchNumber = batchNumber;
        this.subBatchNumber = subBatchNumber;
        this.productionDate = productionDate;
        this.expiryDate = expiryDate;
        this.additionalInfo = additionalInfo;
        this.digitalSignature = digitalSignature;
        this.transactionId = calculateHash();
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public String getSubBatchNumber() {
        return subBatchNumber;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public String getDigitalSignature() {
        return digitalSignature;
    }

    public byte[] getDigitalSignatureBytes() { return Base64.getDecoder().decode(digitalSignature);}

    public String getTransactionId() { return transactionId; }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionDate=" + transactionDate +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", medicineId=" + medicineId +
                ", quantity=" + quantity +
                ", batchNumber='" + batchNumber + '\'' +
                ", subBatchNumber='" + subBatchNumber + '\'' +
                ", productionDate=" + productionDate +
                ", expiryDate=" + expiryDate +
                ", additionalInfo='" + additionalInfo + '\'' +
                ", digitalSignature='" + digitalSignature + '\'' +
                '}';
    }

    public String calculateHash() {
        return Hasher.sha256(
                transactionDate + sender +
                        receiver + medicineId +
                        quantity + batchNumber +
                        subBatchNumber + productionDate +
                        expiryDate + additionalInfo
        );
    }

    public void signTransaction(PrivateKey privateKey) {
        if (privateKey == null || digitalSignature != null) {
            return;
        }
        byte[] sign = DigitalSignature.getInstance().getSignature(calculateHash(), privateKey);
        digitalSignature = Base64.getEncoder().encodeToString(sign);
    }

    public boolean verifySignature(PublicKey publicKey) {
        return DigitalSignature.getInstance().isTextAndSignatureValid(calculateHash(), digitalSignature, publicKey);
    }

    public boolean verify() {
        try {
            return verifySignature(KeyAccess.getPublicKey(sender));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean verifyTransaction() {
        Block block = StatusContainer.blockChain.getBlock(this);
        if (block == null) {
            return false;
        }
        MerkleTree merkleTree = MerkleTree.getInstance(block.getTransactions());
        Boolean proof = merkleTree.generateProof(block.getMerkleRoot());
        return proof;
    }
}
