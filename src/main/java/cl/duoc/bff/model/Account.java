package cl.duoc.bff.model;

import java.math.BigDecimal;

public class Account {
    private String accountNumber;
    private String ownerName;
    private String type;
    private BigDecimal balance;
    private Integer age;

    public Account() {}
    public Account(String accountNumber, String ownerName, String type, BigDecimal balance, Integer age) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.type = type;
        this.balance = balance;
        this.age = age;
    }

    // Getters and setters
    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    // Legacy compatibility methods
    public String getId() { return accountNumber; }
    public void setId(String id) { this.accountNumber = id; }

    public String getOwner() { return ownerName; }
    public void setOwner(String owner) { this.ownerName = owner; }

    public double getBalanceAsDouble() { return balance != null ? balance.doubleValue() : 0.0; }
    public String getCurrency() { return "CLP"; } // Default currency
}
