//Daven Giftian Tejalaksana
//Sunday, 23 May 2021
//CSE 143
//Instructor: Stuart Reges
//TA: Andrew Cheng
//Assignment #7
//This program implements a yes/no guessing game using a binary tree of questions and answers.
//It is similar to a big game of 20 questions where each question is a yes/no question.

import java.util.*;
import java.io.*;

public class QuestionTree {
   private Scanner console; //Scanner object to read words/lines in files
   private QuestionNode overallRoot; //QuestionNode reference to binary tree
   
   //post: construct a question tree with one leaf node representing the object "computer".
   public QuestionTree() {
      console = new Scanner(System.in); //initializes Scanner console
      overallRoot = new QuestionNode("computer"); //initialize QuestionNode overallRoot
   }
   
   //post: method called if client wants to replace current tree by reading another tree
      //from a file. (File information is in preorder form)
      //Passed a Scanner linked to the file; Uses file information to create new tree.
      //Assume file is legal and in standard format. 
      //Reads entire lines of input using NextLine calls.
   public void read(Scanner input) {
      overallRoot = read(overallRoot, input); //public private pair
   }
   
   //private full version of the read method; handles recursion and generation of tree
      //using the passed input scanner and root reference to binary tree.
   private QuestionNode read(QuestionNode root, Scanner input) {
      if (input.hasNextLine()) {
         String category = input.nextLine();
         String word = input.nextLine();
         root = new QuestionNode(word);
         if (category.contains("Q:")) {
            root.yes = read(root.yes, input);
            root.no = read(root.no, input);
         }
      }
      return root;
   }
   
   //post: method called if client wants to store current tree to an output file.
      //The given PrintStream will be open for writing. 
      //Binary tree information sent to output file will be in preorder form.
   public void write(PrintStream output) {
      write(overallRoot, output); //public private pair
   }
   
   //private full version of the write method; handles recursion and generation of file
      //Using root reference to binary tree and PrintStream output.
   private void write(QuestionNode root, PrintStream output) {
      if (root.yes != null || root.no != null) {
         output.println("Q:");
         output.println(root.word);
         write(root.yes, output);
         write(root.no, output);
      } else {
         output.println("A:");
         output.println(root.word);
      }
   }
   
   //post: uses current tree to ask user a series of yes/no questions 
      //until you either guess correctly or fail.
   //If fail, expand the tree to include their object and a new question
      //to distinguish their object from the others. (through user input from Scanner console)
   public void askQuestions() {
      overallRoot = askQuestions(overallRoot);
   }
   
   //post: private full version of the askQuestions() method;
      //handles recursion and modification of binary tree (if needed).
      //returns QuestionNode reference to binary tree (overallRoot)
   private QuestionNode askQuestions(QuestionNode root) {
      if (root.yes == null && root.no == null) { //answer node
         if (yesTo("Would your object happen to be " + root.word + "?")) {
            System.out.println("Great, I got it right!");   
         } else {
            root = addNewWord(root);       
         }
      } else { //question node
         if (yesTo(root.word)) {
            root.yes = askQuestions(root.yes);
         } else {
            root.no = askQuestions(root.no);
         }
      }
      return root;
   }
   
   //post: helper method of the askQuestions recursive method that deals
      //with condition if program guesses the word wrong.
      //handles including new object, asking new question, and modify tree.
   private QuestionNode addNewWord(QuestionNode root) {
      System.out.print("What is the name of your object? ");
      QuestionNode newAnswer = new QuestionNode(console.nextLine());
      System.out.println("Please give me a yes/no question that");
      System.out.println("distinguishes between your object");
      System.out.print("and mine--> ");
      String newQuestion = console.nextLine();
      if (yesTo("And what is the answer for your object?")) {
         root = new QuestionNode(newQuestion, newAnswer, root);
      } else {
         root = new QuestionNode(newQuestion, root, newAnswer);
      }
      return root;
   }
     
   // post: asks the user a question, forcing an answer of "y" or "n";
   //       returns true if the answer was yes, returns false otherwise
   public boolean yesTo(String prompt) {
      System.out.print(prompt + " (y/n)? ");
      String response = console.nextLine().trim().toLowerCase();
      while (!response.equals("y") && !response.equals("n")) {
         System.out.println("Please answer y or n.");
         System.out.print(prompt + " (y/n)? ");
         response = console.nextLine().trim().toLowerCase();
      }
      return response.equals("y");
   }
}