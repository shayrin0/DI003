
package com.sg.vendingmachine.ui;

import java.util.Scanner;

public class UserIOImplementation implements UserIO{
    
    private final Scanner sc ;
    
    public UserIOImplementation(){
        this.sc = new Scanner(System.in);
    }

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public String readString(String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }

    @Override
    public int readInt(String prompt) {
        int number = 0;
        while(true){
            try{
                number = Integer.parseInt(readString(prompt));
                break;
            }
            catch(NumberFormatException ex){
                print("***ERROR*** \nInvalid entry. You must Enter a number.");
            }
        }
        
        return number;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int num = 0;
        do{
            num = readInt(prompt + "[" + min + " - " + max + "]: ");
        }while(num<min || num>max);
        return num;
    }

    @Override
    public double readDouble(String prompt) {
        double number = 0.0;        
        try{
            number = Double.parseDouble(readString(prompt));
        }
        catch(NumberFormatException ex){
            print("***ERROR*** \nInvalid entry. You must Enter a number.");
        }
        return number;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double num = 0.0;        
        do{
            num = readDouble(prompt + "[" + min + " - " + max + "]: ");
        }while(num < min || num > max);        
        return num;
    }

    @Override
    public float readFloat(String prompt) {
        float number = 0.0f;
        try{
            number = Float.parseFloat(readString(prompt));
        }
        catch(NumberFormatException ex){
            print("***ERROR*** \nInvalid entry. You must Enter a number.");
        }
        return number;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float num = 0.0f;
        do{
            num = readFloat(prompt + "[" + min + " - " + max + "]: ");
        }while(num < min || num > max);
        return num;
    }

    @Override
    public long readLong(String prompt) {
        long number = 0l;
        try{
            number = Long.parseLong(readString(prompt));
        }
        catch(NumberFormatException ex){
            print("***ERROR*** \nInvalid entry. You must Enter a number.");
        }
        return number;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long num = 0l;        
        do{
            num = readLong(prompt + "[" + min + " - " + max + "]: ");
        }while(num < min || num > max);        
        return num;
    }
    /*@Override
    public String readDate(String prompt){ 
        String date = readString(prompt);
        return LocalDate.parse(date);
    }*/

}
