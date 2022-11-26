package com.mycompany.mavenproject1;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Login extends App{
   private Map<Integer,Account> accounts=new HashMap<>();
    
   public static void main(String[] args){
       Login programm = new Login();
        programm.load();
        programm.start();
    }
   public void start() {
    }

   public boolean load(){
        try{
            File myObj = new File("C:\\Users\\penis\\Documents\\NetBeansProjects\\mavenproject1\\src\\main\\java\\com\\mycompany\\mavenproject1\\test.txt");
            Scanner myReader = new Scanner(myObj);
            myReader.nextLine();
            while(myReader.hasNextLine()){
                String line=myReader.nextLine();
                String[] parts=line.split(":");
                int accountNum=Integer.valueOf(parts[0]);
                int pin=Integer.valueOf(parts[1]);
                String name=parts[2];
                double balance=Double.parseDouble(parts[3]);
                double holds=Double.parseDouble(parts[4]);
                accounts.put(accountNum,new Account(accountNum,pin,name,balance,holds));          
                return true;
            }
            
        }catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
        }
        return false;
    }
   public boolean loginSuccess(int accountNum,int pin){
        for(Integer key: accounts.keySet()){
            if(accounts.get(key).getAccountNum()==accountNum&&accounts.get(key).getCardPIN()==pin){
                return true;
            }
       }
       return false;
   }
   public String getName(int accountNum){
       String name="";
       for(Integer key: accounts.keySet()){
           if(accounts.get(key).getAccountNum()==accountNum){
               name=accounts.get(accountNum).getName();
            }
       }
       return name;
   }
   public double getBalance(int accountNum){
       double balance=0.00;
       for(Integer key: accounts.keySet()){
           if(key==accountNum){
               balance=accounts.get(accountNum).getBalance();
            }
       }
       return balance;
   }
   public void depositBal(int accountNum,double amount){
       for(Integer key: accounts.keySet()){
           if(key==accountNum){
               accounts.get(key).addBalance(amount);
            }
       }
   }
   public void withdrawBal(int accountNum,double amount){
       for(Integer key: accounts.keySet()){
           if(key==accountNum){
               accounts.get(key).subtractBalance(amount);
            }
       }
   }
   public void addHold(int accountNum,double amount){
       for(Integer key: accounts.keySet()){
           if(key==accountNum){
               accounts.get(key).addHolds(amount);
            }
       }
   }
   public double getHolds(int accountNum){
       for(Integer key: accounts.keySet()){
           if(key==accountNum){
               return accounts.get(accountNum).getHolds();
            }
       }
       return 0.00;
   }
}
