package TicTacToe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        char[][] board= new char[3][3];
        for (int row = 0; row < board.length; row++){
            for(int col = 0; col < board[row].length; col++){
                board[row][col] = ' ';
            }
        }

        char player = 'X';
        boolean gameOver = false;

        Scanner scanner = new Scanner(System.in);

        while(!gameOver){
            
            printBoard(board);
            System.out.println("Player "+player+" enter : ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (board[row][col] == ' ') {
                board[row][col] = player;
                gameOver = hasWon(board, player);
                if(gameOver){
                    System.out.println("Player "+player+" has won!");
                    printBoard(board);
                    scanner.close();
                }
                else{
                    if (player == 'X') {
                        player = 'O';
                    }
                    else{
                        player = 'X';   
                    }
                }
            }
            else{
                System.out.println("Invalid move!");
            }
        }
    }

    static void printBoard(char[][] board){
        for (int row = 0; row < board.length; row++){
            for(int col = 0; col < board[row].length; col++){
                System.out.print(board[row][col]+" | ");
            }
            System.out.println();
        }


    }

    static boolean hasWon(char[][] board, char player){
        //checking the rows
        for (int row = 0; row < board.length; row++){
            if (board[row][0] == player && board[row][1] == player && board[row][2] == player){
                return true;
            }
        }

        //checking the columns
        for (int col = 0; col < board.length; col++){
            if (board[0][col] == player && board[1][col] == player && board[2][col] == player){
                return true;
            }
        }

        //checking the diagonals (left to right)
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }

        //checking the diagonals (right to left)
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }

        return false;
    }
}
