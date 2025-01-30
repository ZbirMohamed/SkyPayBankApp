import org.example.service.AccountService;
import org.example.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class testAccountService {

    /*Ici on va crée nos test mock*/

    private AccountService accountService;

    @BeforeEach
    void setUp() {
        accountService = new AccountServiceImpl();
    }


    @Test
    void testDeposit500(){
        accountService.deposit(1000);
        assertEquals(1000,((AccountServiceImpl) accountService).getBalance());
    }

    @Test
    void testDepositNegativeAmount(){

        RuntimeException exception = assertThrows(RuntimeException.class,()-> {
            accountService.deposit(-500);
        });
        assertEquals(exception.getMessage(),"org.example.Exceptions.NegativeAmountException: The amount inserted is negative");
    }


    @Test
    void withdraw1000(){

        accountService.deposit(1500);
        accountService.withdraw(1000);
        assertEquals(500,((AccountServiceImpl) accountService).getBalance() );
    }

    @Test
    void withdrawMoneyBeyondBalance(){

        RuntimeException exception = assertThrows(RuntimeException.class, ()->{
            accountService.withdraw(1000);
        });
        assertEquals(exception.getMessage(),"org.example.Exceptions.SurpasingBalanceAmountException: The Amount surpass your current balance");
    }

    @Test
    void printStatementForDepositAndWithdraw(){
        /* since this function only prints the only thing we can do is assert that it will not throw an error */

        accountService.deposit(1000);
        accountService.deposit(500);
        accountService.withdraw(1000);
        accountService.withdraw(100);
        accountService.deposit(500);

        assertDoesNotThrow(()->accountService.printStatment());
    }

}
