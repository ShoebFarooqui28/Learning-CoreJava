package Sortings.SelectionSort;

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

        selectionSort(arr);

        //Printing the sorted Array
        System.out.println("Your Sorted Array is : ");
        for(int i : arr){
            System.out.print(i+ " ");
        }
        scanner.close();

    }

    //Selection Sort Algorithm
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
            
        }
    }
}
