package DataStructures.StackDS;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
    
        Stack<String> stack = new Stack<String>();
        stack.push("Ghost Of Tsushima");
        stack.push("Red Dead Redemption 2");
        stack.push("Valorant");
        stack.push("CSGO - 2");

        //Printing the stack
        //System.out.println(stack);

        //Using isEmpty() method to check the Stack
        //System.out.println(stack.isEmpty()); 

        //Using the pop() method to remove the top most element in the stack and then printing it
        //stack.pop();
        //System.out.println(stack);

        //Assigning the popped element to a variable and then printing the variable
        //String myFavGame  = stack.pop();
        //System.out.println(myFavGame);

        //Using peek() method to see the upper most element in the stack
        //System.out.println(stack.peek());

        //Using search() method to get the position of the stack (PS : it doesnt starts with 0)
        //If the element is not in the stack then it will return (-1)
        System.out.println(stack.search("Valorant"));
        System.out.println(stack.search("GTA"));





    }
}
