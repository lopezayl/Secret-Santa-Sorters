/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secret.santa;

import java.util.Random;
import java.util.Scanner;
import secret.santa.Hidden;
/**
 *
 * @author Darkkat
 */
public class SecretSanta {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        
        //variables and arrays
        String[]Santas;
        String[]Secret;
        String name = "";
        String done = "done";
        int NumSantas;
        int CurrentSanta=0;
        String ready = "";
        int TooManyLoops = 0;
        Hidden hidden = new Hidden();
        int stagger;
        String santa = "";
        int SecretSanta;
        String SecretName = "none";
        int i = 0;
        
        
        //Welcome to...
        System.out.printf("***  Welcome to Secret Santa Sorters!    ***\nHow many Santas are there going to be?\n");
        //set array size
        NumSantas = input.nextInt();
        Santas = new String[NumSantas];

        //random stagger
        Random generator;
        generator = new Random();
        stagger = generator.nextInt(NumSantas)+1;
        while(stagger == NumSantas)
        {
            stagger = generator.nextInt(NumSantas)+1;
        }
        
        
        //enter Santa names
        while(CurrentSanta != NumSantas)
        {
            System.out.println("Please enter your name:");
            name = input.nextLine();
            while (name.equals("\n") || name.equals(" ") || name.equals(""))
            {
                name = input.nextLine();
            }
            Santas[CurrentSanta] = name;
            CurrentSanta++;
        }
        
        CurrentSanta = 0;
        
        //make the secret array so names can be randomly assigned
        Secret = new String[NumSantas];
        while(i<NumSantas)
        {
            Secret[i] = "none";
            i++;
        }
        
        //mix up the order of the santas
        generator = new Random();
        while(CurrentSanta != NumSantas)
        {
            name = Santas[CurrentSanta];
            i = generator.nextInt(NumSantas);
            if(Secret[i].equals("none"))
            {
                Secret[i] = name;
                CurrentSanta++;
            }
        }
        
        
        CurrentSanta = 0;
        
        /*
        //display Santa names
        while(CurrentSanta != NumSantas)
        {
            name = Santas[CurrentSanta];
            System.out.printf("Santa Number %d is %s.\n", CurrentSanta+1, name);
            CurrentSanta++;
        }
        
        CurrentSanta = 0;
        */
        
        while(CurrentSanta < NumSantas)
        {
            //assign secret santas
            name = Santas[CurrentSanta];
            SecretSanta = CurrentSanta+stagger;
            if(SecretSanta>NumSantas-1)
            {
                SecretSanta=SecretSanta-NumSantas;
            }
            santa = Santas[SecretSanta];
            
            //Santa, are you ready?
            System.out.printf("\nSanta %s, are you ready? Yes or No: \n", name);
            ready = input.nextLine();
            if (ready.equals("\n") || ready.equals(" ") || ready.equals(""))
            {
                ready = input.nextLine();
            }
            
            //Don't take too long!
            while(ready.equals("no") || ready.equals("No"))
            {
                System.out.println("How about now?\n");
                ready = input.nextLine();
                TooManyLoops++;
                if(TooManyLoops == 6)
                {
                    System.out.printf("Too bad! Here goes...\n");
                    ready = "Yes";
                }
            }
            
            //Santa reveal
            System.out.printf("You are %s's Secret Santa.\n", santa);
            System.out.println("Type Done when you are done: ");
            ready = input.nextLine();
            while(ready.equals("no") || ready.equals("No"))
            {
                System.out.println("How about now?");
                ready = input.nextLine();
                TooManyLoops++;
                if(TooManyLoops == 11)
                {
                    System.out.printf("Too bad! Here goes...\n");
                    ready = "Yes";
                }
            }
            hidden.ClearScreen();
            CurrentSanta++;
        }
        System.out.println("Thank you for playing!\nMerry Christmas, everyone!");
                
    }
    
}
