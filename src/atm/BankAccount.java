/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author sktoo
 */
public class BankAccount {
    public static final double DAILY_MAX_DEPOSIT = 150000;
    public static final double TRANS_MAX_DEPOSIT = 40000;
    public static final int MAX_FREQ_DEPOSIT = 4;
    
    public static final double DAILY_MAX_WITH = 50000;
    public static final double TRANS_MAX_WITH = 20000;
    public static final int MAX_FREQ_WITH = 3;
    
    private double amount;
    private int deposit_frequency;
    private int with_frequency;
    
    private ArrayList<Transaction> deposits;
    private ArrayList<Transaction> withdrawals;
    
    
    public BankAccount(){
        this.amount = 150000.00;
        this.deposit_frequency = 0;
        this.with_frequency = 0;
        
        this.deposits = new ArrayList<>();
        this.withdrawals = new ArrayList<>();
    }
    public BankAccount(double a){
        this.amount = a;
        this.deposit_frequency = 0;
        this.with_frequency = 0;
        
        this.deposits = new ArrayList<>();
        this.withdrawals = new ArrayList<>();
    }
    public double Balance(){
        return this.amount;
    }
    public void deposit(double damount) throws Exception{
        Date today = new Date(); // GEt todays date
        if(damount < 0){
            throw new Exception("Invalid amount! Amount cannot be negative.");
        }
        if(this.deposit_frequency >= MAX_FREQ_DEPOSIT){
            throw new Exception("Sorry, maximum deposits frequency exceeded.");
        }
        if(damount > TRANS_MAX_DEPOSIT){
            throw new Exception("Invalid amount, your cannot deposit more than "+TRANS_MAX_DEPOSIT+" per transaction.");
        }
        if((this.totalDeposits(today) + damount) > DAILY_MAX_DEPOSIT){
            throw new Exception("Sorry, Daily maximum deposits per day is "+DAILY_MAX_DEPOSIT+".");
        }
        if(damount > 0 && damount <= TRANS_MAX_DEPOSIT){
            this.amount += damount;
            this.deposit_frequency++;
            this.addTransaction("deposit", damount);
            
        }else{
            throw new Exception("Invalid amount. Ensure deposit amount is less than 40000");
        }
    }
    public void withdraw(double damount) throws Exception{
        Date today = new Date(); // GEt todays date
        if(damount < 0){
            throw new Exception("Invalid amount! Amount cannot be negative.");
        }
        if(this.with_frequency >= MAX_FREQ_WITH){
            throw new Exception("Sorry, maximum withdrawals exceeded.");
        }
        if(damount > TRANS_MAX_WITH){
            throw new Exception("Invalid amount, you maximum withdrawal per transaction is "+ TRANS_MAX_WITH );
        }
        if((this.totalWithdrawals(today) + damount ) > DAILY_MAX_WITH){
            throw new Exception("Sorry, your maximum withdrawal limit is "+DAILY_MAX_WITH+" per day.");
        }
        if(this.amount >= damount){
            this.amount -=damount;
            this.with_frequency++;
            this.addTransaction("withdraw", damount);
        }else{
            throw new Exception("Insufficient Balance.");
        }
    }
    
    public double totalDeposits(){
        double total = 0.0;
        if(this.deposits!=null){
            total = this.deposits.stream().map((t) -> t.getAmount()).reduce(total, (accumulator, _item) -> accumulator + _item);
        }
        
        return total;
    }
    
    public double totalDeposits(Date today){
        double total = 0.0;
        if(this.deposits != null){
            total = this.deposits.stream().filter((t) -> (t.equalDate(today))).map((t) -> t.getAmount()).reduce(total, (accumulator, _item) -> accumulator + _item);
        }
        
        return total;
    }
    
    public double totalWithdrawals(){
        double total = 0.0;
        total = this.withdrawals.stream().map((t) -> t.getAmount()).reduce(total, (accumulator, _item) -> accumulator + _item);
        return total;
    }
    
    public double totalWithdrawals(Date today){
        double total = 0.0;
        total = this.withdrawals.stream().filter((t) -> (t.equalDate(today))).map((t) -> t.getAmount()).reduce(total, (accumulator, _item) -> accumulator + _item);
        return total;
    }
    
    public boolean addTransaction(String type, double amount){
        Transaction t = new Transaction(amount, type);
        if(type.equals("deposit")){
            this.deposits.add(t);
        } else if(type.equals("withdraw")) {
            this.withdrawals.add(t);
        }
        return false;        
    }
}
