package Modul;

import java.util.HashMap;

public class User {
    private String name, phoneNum, email, password;
    private double eMoney;
    private HashMap<String, Transaction> transactions = new HashMap<String, Transaction>();
    private AccountStatus_Enum accStat;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhoneNum() {
        return phoneNum;
    }
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public double geteMoney() {
        return eMoney;
    }
    public void seteMoney(double eMoney) {
        this.eMoney = eMoney;
    }
    public HashMap<String, Transaction> getTransaksi() {
        return transactions;
    }
    public void setTransaction(HashMap<String, Transaction> transaction) {
        this.transactions = transaction;
    }
    public AccountStatus_Enum getAccStat() {
        return accStat;
    }
    public void setAccStat(AccountStatus_Enum accStat) {
        this.accStat = accStat;
    }
}
