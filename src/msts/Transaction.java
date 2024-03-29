package msts;

import java.io.Serial;
import java.io.Serializable;
import java.security.PrivateKey;
import java.time.LocalDate;
import java.util.Arrays;

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

    //create new batch medicine
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

    public byte[] getDigitalSignatureBytes() {
        return digitalSignature.getBytes();
    }

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
        return Hasher.sha256Salt(
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
        digitalSignature = Arrays.toString(sign);
    }
}
