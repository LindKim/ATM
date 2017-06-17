/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sktoo
 */
public class BankAccountTest {

    public BankAccountTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testBalance() {
        BankAccount myaccount = new BankAccount();
        assertEquals(150000, myaccount.Balance(), 0.001);
    }

    @Test
    public void testDeposit() {
        BankAccount myaccount = new BankAccount();
        try {
            myaccount.deposit(40000.00);
        } catch (Exception ex) {
            Logger.getLogger(BankAccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(190000, myaccount.Balance(), 0.001);
    }

    @Test(expected = Exception.class)
    public void testMaxFrequencyDeposit() throws Exception {
        BankAccount myaccount = new BankAccount();
        for (int i = 0; i <= 5; i++) {
            myaccount.deposit(40000.00);
        }
    }
    @Test(expected = Exception.class)
    public void testMaxTransactionDeposit() throws Exception {
        BankAccount myaccount = new BankAccount();
        myaccount.deposit(50000.00);
    }
    
    @Test(expected = Exception.class)
    public void testMaxDailyDeposit() throws Exception {
        BankAccount myaccount = new BankAccount();
        myaccount.deposit(40000);
        myaccount.deposit(40000);
        myaccount.deposit(35000);
        myaccount.deposit(40000);
        
    }
    
    @Test
    public void testWithdrawal() {
        BankAccount myaccount = new BankAccount();
        try {
            myaccount.withdraw(20000.00);
        } catch (Exception ex) {
            Logger.getLogger(BankAccountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(130000, myaccount.Balance(), 0.001);
    }

    @Test(expected = Exception.class)
    public void testMaxFrequencyWithdrawal() throws Exception {
        BankAccount myaccount = new BankAccount();
        for (int i = 0; i < 5; i++) {
            myaccount.withdraw(20000.00);
        }
    }
    @Test(expected = Exception.class)
    public void testMaxTransactionWithdraw() throws Exception {
        BankAccount myaccount = new BankAccount();
        myaccount.withdraw(30000.00);
    }
    
    @Test(expected = Exception.class)
    public void testMaxDailyWithdrawal() throws Exception {
        BankAccount myaccount = new BankAccount();
        myaccount.withdraw(20000);
        myaccount.withdraw(20000);
        myaccount.withdraw(20000);
        
    }
}
