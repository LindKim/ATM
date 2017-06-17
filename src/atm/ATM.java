/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

import java.util.Scanner;

/**
 *
 * @author sktoo
 */
public class ATM {
    
    public Scanner input;
    public BankAccount account;
    /**
     * @param args the command line arguments
     */
    public ATM(){
        input = new Scanner(System.in);
        account = new BankAccount();
    }
    public void displayMenu(){
        System.out.println("*************************************************");
        System.out.println("1. Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdrawal");
        System.out.println("4. Quit");
        System.out.println("*************************************************");
    }
    public int getMenu(){
        String select;
        this.displayMenu();
        System.out.println("Enter Menu Option\n>");
        select = this.input.next();
        this.input.nextLine();
        switch (select.toLowerCase().trim()) {
            case "balance":
                return 1;
            case "deposit":
                return 2;
            case "withdrawal":
                return 3;
            case "quit":
                return 4;
            default:
                break;
        }
        return 0;
    }
    public void run(){
        boolean run = true;
        int option;
        while(run){
           option = this.getMenu();
           if(option == 4){
               run = !this.quit();
           }
           else if(option == 0){
               System.err.println("Invalid option. ");
           }else if(option == 1){
               this.checkBalance();
           }else if(option == 2){
               this.makeDeposit();
           }else if(option == 3){
               this.makeWithdrawal();
           }
       
        }
    }
    public boolean quit(){
        System.out.println("Are you sure you want to quit? (yes/no)");
        String option = this.input.next();
        this.input.nextLine();
        return option.toLowerCase().trim().equals("yes") || option.toLowerCase().trim().equals("y");
    }
    
    public void checkBalance(){
        System.out.println(this.account.Balance());
        System.out.println("Type \"menu\" and press enter to go back to main menu.");
        String inp;
        boolean run = true;
        while(run){
            inp = this.input.nextLine();
            if(inp.toLowerCase().trim().equals("menu")){
                run=false;
            }else{
                System.out.println("Invalid option");
            }
        }
        this.run(); 
    }
    
    public void makeDeposit(){
       this.showBalance();
       System.out.println("Enter amount and press enter(or type menu and press enter to go back to main menu)");
       String inp;
       double amount;
       boolean intake = true;
       while(intake){
            inp = this.input.nextLine();
            try {
                 amount = Double.parseDouble(inp.trim());
                 this.account.deposit(amount);
                 System.out.println("You have successfully deposited " + amount);
                 intake = false;
                 
                 
             } catch (NumberFormatException ignore) {
                 if(inp.toLowerCase().trim().equals("menu")){
                     intake = false;
                 }
                 else{
                     System.err.println("Invalid Input");
                 }
             }catch(Exception ex){
                 System.err.println(ex.getMessage());
             }
       }
       this.run();
    }
    
    public void makeWithdrawal(){
       this.showBalance();
       System.out.println("Enter amount and press enter(or type menu and press enter to go back to main menu)");
       String inp;
       double amount;
       boolean intake = true;
       while(intake){
            inp = this.input.nextLine();
            try {
                 amount = Double.parseDouble(inp.trim());
                 this.account.withdraw(amount);
                 System.out.println("You have successfully withdrawn " + amount);
                 intake = false;
                 
             } catch (NumberFormatException ignore) {
                 if(inp.toLowerCase().trim().equals("menu")){
                     intake = false;
                 }
                 else{
                     System.err.println("Invalid Input");
                 }
             }catch(Exception ex){
                 System.err.println(ex.getMessage());
             }
       }
       this.run();
                 
    }
    public void showBalance(){
        System.out.println("Current Balance: "+ this.account.Balance());
    }
    public static void main(String[] args) {
        // TODO code application logic here
        ATM atm = new ATM();
        atm.run();
    }
    
    
}
