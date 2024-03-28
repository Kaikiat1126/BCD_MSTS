package msts;

import java.io.Serial;
import java.io.Serializable;
import java.security.PrivateKey;
import java.time.LocalDate;
import java.util.Arrays;

public class Transaction implements Serializable {
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

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public String getSubBatchNumber() {
        return subBatchNumber;
    }

    public void setSubBatchNumber(String subBatchNumber) {
        this.subBatchNumber = subBatchNumber;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getDigitalSignature() {
        return digitalSignature;
    }

    public byte[] getDigitalSignatureBytes() {
        return digitalSignature.getBytes();
    }

    public void setDigitalSignature(String digitalSignature) {
        this.digitalSignature = digitalSignature;
    }

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
