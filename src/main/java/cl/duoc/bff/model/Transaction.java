package cl.duoc.bff.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {
    private String txId;
    private String accountNumber;
    private LocalDate txDate;
    private String description;
    private BigDecimal amount;

    public Transaction() {}
    public Transaction(String txId, String accountNumber, LocalDate txDate, String description, BigDecimal amount) {
        this.txId = txId;
        this.accountNumber = accountNumber;
        this.txDate = txDate;
        this.description = description;
        this.amount = amount;
    }

    // Getters and setters
    public String getTxId() { return txId; }
    public void setTxId(String txId) { this.txId = txId; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public LocalDate getTxDate() { return txDate; }
    public void setTxDate(LocalDate txDate) { this.txDate = txDate; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    // Legacy compatibility methods
    public String getId() { return txId; }
    public void setId(String id) { this.txId = id; }

    public String getAccountId() { return accountNumber; }
    public void setAccountId(String accountId) { this.accountNumber = accountId; }

    public String getType() { return "TRANSACTION"; } // Default type
    public void setType(String type) { /* Not used */ }

    public double getAmountAsDouble() { return amount != null ? amount.doubleValue() : 0.0; }
    public String getDesc() { return description; }
    public void setDesc(String desc) { this.description = desc; }
}
