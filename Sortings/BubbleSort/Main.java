package Sortings.BubbleSort;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //User Input : Length of the Array
        System.out.println("Enter the length of an Array : ");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];

        //User Input : Elements of the Array
        for(int i = 0; i<n; i++){
            System.out.println("Enter the number at "+i+"th index : ");
            arr[i] = scanner.nextInt();
        }

        bubbleSort(arr);

        //Printing the sorted Array
        System.out.println("Your Sorted Array is : ");
        for(int i : arr){
            System.out.print(i+" ");
        }
        scanner.close();
    }
    
    //Bubble Sort Algorithm
    public static void bubbleSort(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length-1; i++) {    
            for(int j = 0; j < arr.length-i-1; j++){
                if (arr[j] > arr[j+1]) {
                    temp = arr[j]; 
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }            
    }
}
