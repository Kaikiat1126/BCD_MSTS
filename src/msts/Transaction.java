package msts;

import msts.obj.User;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

public class Transaction {
    private LocalDate transactionDate;
    private String sender;
    private String receiver;
    private int medicineId;
    private int quantity;
    private int batchNumber;
    private LocalDate productionDate;
    private LocalDate expiryDate;
    private String additionalInfo;
    private byte[] digitalSignature;

    public Transaction(){};

    public Transaction(LocalDate transactionDate, String sender, String receiver, int medicineId, int quantity, int batchNumber, LocalDate productionDate, LocalDate expiryDate, String additionalInfo, byte[] digitalSignature) {
        this.transactionDate = transactionDate;
        this.sender = sender;
        this.receiver = receiver;
        this.medicineId = medicineId;
        this.quantity = quantity;
        this.batchNumber = batchNumber;
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

    public int getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(int batchNumber) {
        this.batchNumber = batchNumber;
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

    public byte[] getDigitalSignature() {
        return digitalSignature;
    }

    public void setDigitalSignature(byte[] digitalSignature) {
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
                ", batchNumber=" + batchNumber +
                ", productionDate=" + productionDate +
                ", expiryDate=" + expiryDate +
                ", additionalInfo='" + additionalInfo + '\'' +
                ", digitalSignature=" + Arrays.toString(digitalSignature) +
                '}';
    }
}
