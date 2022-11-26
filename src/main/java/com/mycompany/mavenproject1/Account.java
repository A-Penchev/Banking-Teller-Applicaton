
package com.mycompany.mavenproject1;

public class Account {
    private String name;
    private String address;
    

    private double balance; 
    private double holds; 
    private int accountNum;
    private int cardPIN;
    
    public Account(int accountNum,int cardPIN, String name, double balance,double holds){
        this.accountNum=accountNum;
        this.cardPIN=cardPIN;
        this.name=name;
        this.balance=balance;
        this.holds=holds;
    }
    
    public String getName(){
        return name;
    }
    public String getAddress(){
        return address;
    }
    
    public double getBalance(){
        return balance-holds;
    }
    public void addBalance(double amount){
        this.balance+=amount;
    }
    public void subtractBalance(double amount){
        this.balance-=amount;
    }
    public void addHolds(double amount){
        this.holds+=amount;
    }
    public double getHolds(){
        return this.holds;
    }
    
    public int getAccountNum(){
        return accountNum;
    }
    public int getCardPIN(){
        return cardPIN;
    }
}
