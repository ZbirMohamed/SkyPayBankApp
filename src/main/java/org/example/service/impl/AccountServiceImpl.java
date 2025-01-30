package org.example.service.impl;

import org.example.service.AccountService;
import org.example.Exceptions.NegativeAmountException;
import org.example.Exceptions.SurpasingBalanceAmountException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class AccountServiceImpl implements AccountService {

    /* this is not perfect for multiple users as it stores the data
    of only one user if we consider adding an indexation methode
    to accurately get each user data respectively
     a Map<Integer,Integer> (user_id,balance) would be a good approach  */

    private int balance = 0;

    // the idea is to reduce the usage of multiple data structures
    // such as making a whole hashmap for withdraws and one for deposit
    // negative value represent withdraws and postive one's are deposits
    //ex : { '11/04/2025':[1500,-1000]},{'11/05/2025':[-1000]}
    private final Map<String,List<Integer>> transactions = new HashMap<>();
    private List<Integer> balanceHistory = new ArrayList<>();


    public int getBalance(){
        return balance;
    }

    public void setBalance(int amount){
        this.balance += amount;
    }

    @Override
    public void deposit(int amount)  {
        try {
            if (amount < 0) throw new NegativeAmountException("The amount inserted is negative");

            String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            transactions.computeIfAbsent(date, k -> new ArrayList<>()).add(amount);
            setBalance(amount);
            balanceHistory.add(getBalance());

        }catch (NegativeAmountException e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }catch (Exception e){
            /* for now we use Exception to handle any unkown type of exception */
            System.out.println("Catching Bizzar or New Error");
        }
    }

    /* the withdraw method check the balance and acts accordingly */
    @Override
    public void withdraw(int amount) {
        try{
            if(amount>balance) throw new SurpasingBalanceAmountException("The Amount surpass your current balance");

            String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            transactions.computeIfAbsent(date, k -> new ArrayList<>()).add(-amount);
            setBalance(-amount);
            balanceHistory.add(getBalance());

        }catch (SurpasingBalanceAmountException e){
            throw new RuntimeException(e);
        }catch (Exception e){
            System.out.println("Catching Bizzar or New Error");
        }
    }

    @Override
    public void printStatment() {
        try{
        AtomicInteger index = new AtomicInteger();
        System.out.println("Date       || Amount || Balance");
        transactions.forEach((date,amounts)->{
            for(Integer amount: amounts){
                System.out.printf("%10s || %5d  || %3d \n",date,amount,balanceHistory.get(index.getAndIncrement()));
            }
        });
        }catch (ArrayIndexOutOfBoundsException e){
            /* maybe we can add some logs to check if the transactions are safely made */
            System.out.println("The balance history is out of bounds with the commited transactions check your logs");
        }catch (Exception e){
            System.out.println("Catching Bizzar or New Error");
        }
    }
}
