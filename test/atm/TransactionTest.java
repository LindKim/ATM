/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author sktoo
 */
public class TransactionTest {
    
    public TransactionTest() {
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
    public void testDepositTransaction(){
        Transaction deposit = new Transaction(500, "deposit");
        assertEquals("deposit", deposit.getType());
        assertEquals(500, deposit.getAmount(), 0.001);
    }
    
    @Test
    public void testWithdrawTransaction(){
        Transaction deposit = new Transaction(500, "withdraw");
        assertEquals("withdraw", deposit.getType());
        assertEquals(500, deposit.getAmount(), 0.001);
    }
}
