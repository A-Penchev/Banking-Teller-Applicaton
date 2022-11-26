package com.mycompany.mavenproject1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage window) throws Exception {
        Login log=new Login();
        
        // 1. Create start layout
        BorderPane startPage=new BorderPane();
        startPage.setTop(new Label("Welcome, please hit 'Start' to begin"));
        
        // 1.1 Create start program button
        Button startButton=  new Button("Start");
        
        // 1.2 Organize start page layout
        startPage.setCenter(startButton);
        
        // 2. Create login layout
        BorderPane loginPage=new BorderPane();
        Label loginStatus=new Label("");
        
        // 2.1 Create menu for login layout
        HBox loginMenu=new HBox();
        loginMenu.setPadding(new Insets(20, 20, 20, 20));
        loginMenu.setSpacing(10);
        
        // 2.2 Create button/TextFields for menu
        Button login=  new Button("Login");
        
        TextField accountNum = new TextField();
        TextField pin = new TextField();
        
        // 2.3. Add button/TextFields to menu
        loginMenu.getChildren().addAll(login,accountNum,pin);
        
        //2.4 Organize login layout
        loginPage.setCenter(loginMenu);
        loginPage.setBottom(loginStatus);
               
        // 3. Create welcome layout
        BorderPane layoutPage=new BorderPane();
        HBox layoutButtons=new HBox();
        
        

        // Buttons for layout
        Button depOrWid=new Button("Deposit/Withdraw");
        Button holdOrStop=new Button("Holds/Stops");
        Button accountInfo=new Button("Account Information");
        
        layoutButtons.getChildren().addAll(depOrWid,holdOrStop,accountInfo);
        layoutPage.setBottom(layoutButtons);
        
        //4.1 Account Info Section
        Label welcomeName=new Label();
        
        //4.2 dep/withdraw section
        BorderPane depWidLayout=new BorderPane();
        Label currentBalance=new Label("Current Balance: ");
        
        GridPane amounts=new GridPane();
        Button deposit=new Button("Deposit");
        TextField depositAmount=new TextField();
        Button withdraw=new Button("Withdraw");
        TextField withdrawAmount=new TextField();
        amounts.add(deposit, 0, 0);
        amounts.add(depositAmount, 1, 0);
        amounts.add(withdraw, 0, 1);
        amounts.add(withdrawAmount, 1, 1);
        
        depWidLayout.setTop(currentBalance);
        depWidLayout.setCenter(amounts);
        
        //4.3 Hold/Stops section
        BorderPane holdStopLayout=new BorderPane();
        VBox holdsLayout=new VBox();
        Button holdsButton=new Button("Hold");
        Button stopButton=new Button("Stop");
        
        //Holds layout
        HBox holdAmountLayout=new HBox();
        Label holdAmountLabel=new Label("Amount");
        TextField holdsAmount=new TextField();
        holdAmountLayout.getChildren().addAll(holdAmountLabel,holdsAmount);
        
        HBox holdTimeLayout=new HBox();
        Label holdTimeLabel=new Label("Hold period");
        TextField holdsTime=new TextField();
        holdTimeLayout.getChildren().addAll(holdTimeLabel,holdsTime);
        
        HBox holdReasonLayout=new HBox();
        Label holdReasonLabel=new Label("Reason");
        TextField holdsReason=new TextField();
        holdReasonLayout.getChildren().addAll(holdReasonLabel,holdsReason);
        
        holdsLayout.getChildren().addAll(holdsButton,holdAmountLayout,holdTimeLayout,holdReasonLayout);
        holdStopLayout.setLeft(holdsLayout);
        
        //Stops layout
        VBox stopLayout=new VBox();
        
        HBox stopAmountLayout=new HBox();
        Label stopAmountLabel=new Label("Amount");
        TextField stopAmount=new TextField();
        stopAmountLayout.getChildren().addAll(stopAmountLabel,stopAmount);
        
        HBox stopTimeLayout=new HBox();
        Label stopTimeLabel=new Label("Stop period");
        TextField stopTime=new TextField();
        stopTimeLayout.getChildren().addAll(stopTimeLabel,stopTime);
        
        HBox stopReasonLayout=new HBox();
        Label stopReasonLabel=new Label("Reason");
        TextField stopReason=new TextField();
        stopReasonLayout.getChildren().addAll(stopReasonLabel,stopReason);
        
        stopLayout.getChildren().addAll(stopButton,stopAmountLayout,stopTimeLayout,stopReasonLayout);
        holdStopLayout.setRight(stopLayout);
  
      
        
        //Creates scenes
        Scene startScene = new Scene(startPage);
        Scene loginScene = new Scene(loginPage);
        Scene welcomeScene = new Scene(layoutPage);
        
        
        // 10. Adding actions on button press
        startButton.setOnAction((event) -> {log.load();window.setScene(loginScene);}); //this one loads the text file and changes the scene to the login page
        
        //10.1 login button-> change scene to welcome page if successful
        login.setOnAction((event) -> {
            if(log.loginSuccess(Integer.parseInt(accountNum.getText()),Integer.parseInt(pin.getText()))){
                window.setScene(welcomeScene);//change to welcome screen if loginsuccessful
                welcomeName.setText("Welcome "+log.getName(Integer.parseInt(accountNum.getText()))+". Your available balance is $"+currentBal(log,accountNum.getText())+". Your balance is $"+currentBalWithHolds(log,accountNum.getText()));//change welcome screen text
                layoutPage.setTop(welcomeName);
            }
            loginStatus.setText("Wrong Account Number/PIN");//invalid pin entered
        });
        
        //10.2 Layout button action
        depOrWid.setOnAction((event)->layoutPage.setTop(depWidLayout));
        accountInfo.setOnAction((event)-> {layoutPage.setTop(welcomeName);
                welcomeName.setText("Welcome "+log.getName(Integer.parseInt(accountNum.getText()))+". Your available balance is $"+currentBal(log,accountNum.getText())+". Your balance is $"+currentBalWithHolds(log,accountNum.getText()));
                });
        holdOrStop.setOnAction((event)->layoutPage.setTop(holdStopLayout));
        
        
        //10.3 Dep/Wid button action
        deposit.setOnAction((event)->{log.depositBal(Integer.parseInt(accountNum.getText()), Double.parseDouble(depositAmount.getText()));
                depositAmount.setText("");
                });
        withdraw.setOnAction((event)->{log.withdrawBal(Integer.parseInt(accountNum.getText()), Double.parseDouble(withdrawAmount.getText()));
                withdrawAmount.setText("");
                });
        holdsButton.setOnAction((event)->{log.addHold(Integer.parseInt(accountNum.getText()), Double.parseDouble(holdsAmount.getText()));
                });
        
       
        
        //Show the main scene when program launch
        window.setScene(startScene);
        window.show();
        
    }

    public static void main(String[] args) {
        launch();
    }
    
    public double currentBal(Login log, String accountNum){
        return log.getBalance(Integer.parseInt(accountNum))-currentHolds(log,accountNum);
    }
    public double currentHolds(Login log, String accountNum){
        return log.getHolds(Integer.parseInt(accountNum));
    }
    public double currentBalWithHolds(Login log, String accountNum){
        return log.getBalance(Integer.parseInt(accountNum));
    }

}