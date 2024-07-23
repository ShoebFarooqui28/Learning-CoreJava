package DataStructures.LinkedListDS;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {

        LinkedList<String> list = new LinkedList<String>();
        
        //(PS : In Linked List the push() method pushes the element in front/head position of previous element.)
        //list.push("A");
        //list.push("B");
        //list.push("C");
        //list.push("D");
        //list.push("E");

        //Using the pop() method to remove the first most element in the list and then printing it
        //list.pop();
        //System.out.println(list);

        //Using peek() method to see the first most element in the list
        //System.out.println(list.peek());
        //System.out.println(list.peekFirst());
        //System.out.println(list.peekLast());

        //(PS : In Linked List the offer() method pushes the element in back/tail position of the previous element.)
        list.offer("A");
        list.offer("B");
        list.offer("C");
        list.offer("D");
        list.offer("E");
        
        //Using addFirst() method to add a new element in the first position of the list
        list.addFirst("test");
        System.out.println(list);

        //Using addFirst() method to add a new element in the first position of the list
        list.addLast("test2");
        System.out.println(list);

    }
}
