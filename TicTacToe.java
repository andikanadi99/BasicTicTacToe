/**
 * The TicTacToe program implements the classical game in a simple
 * environment where players can play with an AI.
 *
 *  @author: Andika Yudhatrisna
 *  @since 2022-05-25
 * 
 */
import java.util.*;

public class TicTacToe
{
    /**
    This methods prints the game board to be played in.
    @param gameBoard    The game board to be printed.
    */
    public static void printBoard(char gameBoard[][]){
        //Spacer.
        System.out.println();
        //Print the game board.
        for(int i = 0; i < gameBoard.length; i++){
            for(int j = 0; j < gameBoard[i].length; j++){
                System.out.print(gameBoard[i][j]);
            }
            System.out.println();
        }
        //Spacer.
        System.out.println();
    }

    /**
    This method returns the space where the user wants to put a symbol in.
    @param input        Scanner for reading user inputs.
    @param gameBoard    The game board to be printed.
    @return Integer position.
    */
    public static int positionCalculation(Scanner input, char[][] gameBoard){
        int position = 0;
        //Keeps asking user until correct position is entered.
        while(position < 1 || position > 9){
            System.out.println();
            System.out.print("Enter a position(1-9) to mark a symbol: ");
            //Try and catch to ensure that user enters an integer.
            try {
                position = Integer.parseInt(input.nextLine());
                if(position < 1 || position > 9){
                    System.out.println("Sorry, number you entered is out of range for the game board. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Sorry, you did not entered an integer. Please try again.");
            }
        }
        return position;
    }

    /**
    This methods inputs symbols made by player and CPU onto the board. If symbol is already taken, returns a -1.
    @param gameBoard    The game board to be edited.
    @param position     Position in board to be edited.
    @param symbol       Symbol to be put in
    */
    public static int inputChar(char gameBoard[][], int position, char symbol){
            //Checker for AI:
            if(position < 1 || position > 9){
                return -1;
            }
            //Switch case to input symbol based on position entered and type of player.
            switch(position){
                case 1:
                    if(gameBoard[0][0] == 'X' || gameBoard[0][0] == 'O'){
                        return -1;
                    }
                    gameBoard[0][0] = symbol;
                    break;
                case 2:
                    if(gameBoard[0][2] == 'X' || gameBoard[0][2] == 'O'){
                        return -1;
                    }
                    gameBoard[0][2] = symbol;
                    break;
                case 3:
                    if(gameBoard[0][4] == 'X' || gameBoard[0][4] == 'O'){
                        return -1;
                    }
                    gameBoard[0][4] = symbol;
                    break;
                case 4:
                    if(gameBoard[2][0] == 'X' || gameBoard[2][0] == 'O'){
                        return -1;
                    }
                    gameBoard[2][0] = symbol;
                    break;
                case 5:
                    if(gameBoard[2][2] == 'X' || gameBoard[2][2] == 'O'){
                        break;
                    }
                    gameBoard[2][2] = symbol;
                    break;
                case 6:
                    if(gameBoard[2][4] == 'X' || gameBoard[2][4] == 'O'){
                        return -1;
                    }
                    gameBoard[2][4] = symbol;
                    break;
                case 7:
                    if(gameBoard[4][0] == 'X' || gameBoard[4][0] == 'O'){
                        return -1;
                    }
                    gameBoard[4][0] = symbol;
                    break;
                case 8:
                    if(gameBoard[4][2] == 'X' || gameBoard[4][2] == 'O'){
                        return -1;
                    }
                    gameBoard[4][2] = symbol;
                    break;
                case 9:
                    if(gameBoard[4][4] == 'X' || gameBoard[4][4] == 'O'){
                        return -1;
                    }
                    gameBoard[4][4] = symbol;
                    break;
            }
            return 0;
    }

    /**
    This methods checks if the player of cpu has won
    @param gameBoard    The game board to be printed.
    @param symbol       Symbol to check.
    @return     If winner or not.
    */
    public static boolean checkWinner(char gameBoard[][], char symbol){
        //For loop to check for wins horizontally or vertically.
        for(int i = 0; i < 6; i += 2){
            if(gameBoard[i][0] == symbol && gameBoard[i][2] == symbol && gameBoard[i][4] == symbol){
                return true;
            }
            if(gameBoard[0][i] == symbol && gameBoard[2][i] == symbol && gameBoard[4][i] == symbol){
                return true;
            }
        }
        //If statements to check for winner in diagonal method.
        if(gameBoard[0][0] == symbol && gameBoard[2][2] == symbol && gameBoard[4][4] == symbol){
            return true;
        }
        if(gameBoard[0][4] == symbol && gameBoard[2][2] == symbol && gameBoard[4][0] == symbol){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        /* Intro to Game */
        String play = "y";
        System.out.println("Welcome to Tic Tac Toe!");

        /* Continually ask user if they want to play or not */
        while(play.equals("y")){
            //Spacer
            System.out.println();
            //Checks if user wants to play or not
            System.out.print("To play, enter y or Y. Enter anything else to exit: ");
            play = input.nextLine().toLowerCase();
            if(play.equals("y")){
                /* initialize, then print initial gameboard. */
                char gameBoard [][] = { 
                {' ', '|', ' ', '|', ' '}, {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}, {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};
                printBoard(gameBoard);
                
                // Variables to deal with game.
                int turns = 0;
                boolean winner = false;
                int position = 0;
                int validPosition = -1;

                //While loop to last until duration of game is finshed. Either through a winner or draw.
                while(!winner && turns < 9){   
                    //Player's turn: Ask player for position to play
                    if(turns % 2 == 0){
                        validPosition = -1;
                        //While loop to run until a valid position is picked by player.
                        while(validPosition == -1){
                            //Gets position and inputs appropriate symbols IF valid position was picked.
                            position = positionCalculation(input,gameBoard);
                            validPosition = inputChar(gameBoard, position, 'X');
                            if(validPosition == -1){
                                System.out.println("Sorry, position you entered already has a symbol in it. Try again.");
                            }else{
                                //Checks if player won.
                                winner = checkWinner(gameBoard, 'X');
                                if(winner){
                                    System.out.println("Congratulations, You won the game! You are amazing!");
                                }
                            }
                        }
                    }
                    //CPU's turn: Randomly Assign a position for cpu to play
                    else{
                        System.out.println("AI's turn: ");
                        validPosition = -1;
                        int min = 1;
                        int max = 10;
                        //While loop to assign position that has not been taken yet.
                        while(validPosition == -1){
                            position = (int)(Math.random()*(max-min+1)+min);  
                            validPosition = inputChar(gameBoard, position, 'O');
                        }
                        //Checks if cpu won.
                        winner = checkWinner(gameBoard, 'O');
                        if(winner){
                            System.out.println("Sorry, you lost. Better luck next time.");
                        }
                    }

                    //Print updated board and increment turn.
                    printBoard(gameBoard); 
                    turns++;

                    //Draw message printing.
                    if(turns == 9 && !winner){
                        System.out.println("Game was a draw!");
                    }
                }
            }
        }

        //Exit message.
        System.out.println("Thank you for Playing!");
        System.out.println();
        input.close();
    }
}
