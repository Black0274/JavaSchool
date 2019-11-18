package com.sbt.javaschool.losev.lesson5.Terminal;

import com.sbt.javaschool.losev.lesson5.Exceptions.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class Console {

    private static void showHelp(){
        System.out.println("Choose a service: \n" +
                "insert {card number} — insert card into terminal\n" +
                "extract              — extract card from terminal\n" +
                "pin {value}          — type pin code\n" +
                "check                — show amount of money in account\n" +
                "withdraw {value}     — withdraw value from account\n" +
                "put {value}          — put value to account\n" +
                "exit                 — terminate work\n");
    }

    public static void startWork(TerminalImpl terminal){
        showHelp();
        while (true){
            Scanner sc = new Scanner(System.in);
            String[] input = sc.nextLine().split(" ");
            if (input.length > 2){
                System.out.println("Invalid command. Try again.");
                continue;
            }

            switch (input[0]){
                case "insert":
                    try {
                        if (input.length == 1){
                            System.out.println("Empty card number field. Try again");
                            continue;
                        }
                        CreditCard card = new CreditCard(Long.valueOf(input[1]));
                        terminal.insert(card);
                        System.out.println("Card " + input[1] + " successfully inserted.");
                    } catch (CardAlreadyInsertedException e) {
                        System.out.println("Slot for the card is full. Remove card from terminal.");
                    } catch (NumberFormatException e){
                        System.out.println("Invalid card number.");
                    }
                    break;

                case "extract":
                    if (input.length > 1){
                        System.out.println("Invalid command. Try again");
                        continue;
                    }
                    try {
                        CreditCard card = terminal.extract();
                        System.out.println("Card " + card.getNumber() + " successfully extracted.");
                    } catch (CardNotInsertedException e) {
                        System.out.println("The slot for card is empty.");
                    }
                    break;

                case "pin":
                    if (input.length < 2){
                        System.out.println("Empty value field. Try again");
                        continue;
                    }
                    try {
                        int pin = Integer.valueOf(input[1]);
                        int mistakes = terminal.typePin(pin);
                        if (mistakes == 0) {
                            System.out.println("Correct pin. Choose a service.");
                        } else if (mistakes < 3){
                            System.out.println("Wrong password. Try again.");
                        } else if (mistakes == 3){
                            System.out.println("Wrong password. The card is locked for 5 seconds");
                        }
                    } catch (CardNotInsertedException e ){
                        System.out.println("The slot for card is empty.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid card pin.");
                    } catch (CardNotExistsException e){
                        System.out.println("Your card is not served.");
                    } catch (AccountIsLockedException e) {
                        System.out.println("Your card is locked. Wait " + e.waitTime + " seconds.");
                    } catch (IOException e) {
                        System.out.println("Connection error.");
                    }
                    break;

                case "check":
                    if (input.length > 1){
                        System.out.println("Invalid command. Try again");
                        continue;
                    }
                    try {
                        BigDecimal capital = terminal.checkCapital();
                        System.out.println("Your capital: " + capital);
                    } catch (CardNotInsertedException e) {
                        System.out.println("The slot for card is empty.");
                    } catch (CardNotExistsException e) {
                        System.out.println("Your card is not served.");
                    } catch (AccountIsLockedException e) {
                        System.out.println("Your card is locked. Try to type pin code.");
                    } catch (IOException e) {
                        System.out.println("Server is not responding. Try later.");
                    }
                    break;

                case "withdraw":
                    if (input.length < 2){
                        System.out.println("Empty value field. Try again");
                        continue;
                    }
                    try {
                        terminal.withdraw(new BigDecimal(input[1]));
                        System.out.println(input[1] + " successfully withdrawn from your account");
                    } catch (CardNotInsertedException e) {
                        System.out.println("The slot for card is empty.");
                    } catch (CardNotExistsException e) {
                        System.out.println("Your card is not served.");
                    } catch (AccountIsLockedException e) {
                        System.out.println("Your card is locked. Try to type pin code.");
                    } catch (NotEnoughMoneyException e) {
                        System.out.println("Not enough money in your account");
                    } catch (IllegalArgumentException e){
                        System.out.println("Invalid value.");
                    } catch (NotMultipleOf100Exception e) {
                        System.out.println("Value is not a multiple of 100");
                    } catch (IOException e) {
                        System.out.println("Server is not responding. Try later.");
                    }
                    break;

                case "put":
                    if (input.length < 2){
                        System.out.println("Empty value field. Try again");
                        continue;
                    }
                    try {
                        terminal.put(new BigDecimal(input[1]));
                        System.out.println(input[1] + " successfully withdrawn from your account");
                    } catch (CardNotInsertedException e) {
                        System.out.println("The slot for card is empty.");
                    } catch (CardNotExistsException e) {
                        System.out.println("Your card is not served.");
                    } catch (AccountIsLockedException e) {
                        System.out.println("Your card is locked. Try to type pin code.");
                    } catch (NotEnoughMoneyException e) {
                        System.out.println("Not enough money in your account");
                    } catch (IllegalArgumentException e){
                        System.out.println("Invalid value.");
                    } catch (NotMultipleOf100Exception e) {
                        System.out.println("Value is not a multiple of 100");
                    } catch (IOException e) {
                        System.out.println("Server is not responding. Try later.");
                    }
                    break;

                case "exit":
                    return;
                default:
                    System.out.println("Invalid command. Try again.");
            }
        }
    }

    public static void main(String[] args) {
        TerminalServer terminalServer = new TerminalServer();
        CreditCard card1 = new CreditCard(1111);
        CreditCard card2 = new CreditCard(2222);
        CreditCard card3 = new CreditCard(3333);
        //CreditCard card4 = new CreditCard(4444);
        terminalServer.addNewCard(card1, 1111, new BigDecimal(1111));
        terminalServer.addNewCard(card2, 1234, new BigDecimal(10000));
        terminalServer.addNewCard(card3, 8978);
        TerminalImpl impl = new TerminalImpl(terminalServer);
        startWork(impl);
    }
}
