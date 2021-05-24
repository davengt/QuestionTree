//Daven Giftian Tejalaksana
//Sunday, 23 May 2021
//CSE 143
//Instructor: Stuart Reges
//TA: Andrew Cheng
//Assignment #7
//This program creates a specialized tree node class for QuestionTree.java.

public class QuestionNode {
   public String word; //holds the string for each tree node
   public QuestionNode yes; //yes branch of tree node (similar to left branch)
   public QuestionNode no; //no branch of tree node (similar to right branch)
   
   //post: constructs binary tree node when only a string is passed.
   public QuestionNode(String word) {
      this(word, null, null);
   }
   
   //post: constructs binary tree node when string, left branch, and right branch is passed.
   public QuestionNode(String word, QuestionNode yes, QuestionNode no) {
      this.word = word;
      this.yes = yes;
      this.no = no;
   }
}
