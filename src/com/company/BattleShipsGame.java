
package com.company;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class BattleShipsGame {

    public static void main(String[] args) {
        char hit = 'X';
        char miss = 'O';
        char water = '~';
        char ship = 's';

        char battleShip = 'B';
        char destroyer1 = 'D';
        char destroyer2 = 'd';
        int [] test  = {'b', 'b', 'b', 'b'};

        int shipNumber = 200;
        int gameBoardLenght = 10;

       // int battleShipLenght = 5; leter
      //  int destroyer1Lenght = 4;
      //  int destroyer2Lenght = 4;


        char [][] gameBoard = createGameBoard(gameBoardLenght, hit, miss, water, battleShip, destroyer1, destroyer2, shipNumber, ship);
        printGameBoard(gameBoard, water, ship);
        int undetectedShips = shipNumber;
        while(undetectedShips > 0){
            int[] guessLocation = getUserGuess(gameBoardLenght);
            char locationVievUpdate = evaluateGuessAndGetTarget(guessLocation, gameBoard, water, ship, hit, miss, test);
            if (locationVievUpdate == hit){
                undetectedShips--;
            }
            gameBoard = gameBoardUpdate(gameBoard, guessLocation, locationVievUpdate);
            printGameBoard(gameBoard, water, ship);
        }
        System.out.print("You won");
    }

    private static char[][] gameBoardUpdate(char[][] gameBoard, int[] guessLocation, char locationVievUpdate) {
        int row = guessLocation[0];
        int col = guessLocation[1];
        gameBoard[row][col] = locationVievUpdate;
        return  gameBoard;

    }

    private static char evaluateGuessAndGetTarget(int[] guessLocation, char[][] gameBoard, char water, char ship, char hit, char miss, int[] test) {
        String message;
        int row = guessLocation[0];
        int col = guessLocation[1];
        char target = gameBoard[row][col];

        if (target == ship){
            target = hit;
            message = "Hit!";
        } else if (target == water){
            target = miss;
            message = "Miss!";
        } else {
            message = "Already hit!";
        }

        System.out.println(message);
        return  target;
    }

    private static int[] getUserGuess(int gameBoardLenght) {
        int row;
        int col;

        do {
            System.out.print("Row: ");
            row = new Scanner(System.in).nextInt();
        } while (row < 1 || row > gameBoardLenght + 1);

        do {
            System.out.print("Column: ");
            col = new Scanner(System.in).nextInt();
        } while (col < 1 || col >gameBoardLenght + 1);

        return new int[]{row - 1, col - 1};
    }

    private static void printGameBoard(char[][] gameBoard, char water, char ship) {
        int gameBoardLength = gameBoard.length;
        System.out.print("  ");    //two spaces to align the text

        for (int i = 0; i < gameBoardLength; i++){
            System.out.print(i + 1 + " ");
        }
        System.out.println();

        for (int row = 0; row < gameBoardLength; row++){
            System.out.print(row + 1 + " ");
            for (int col = 0; col < gameBoardLength; col++){     // col = collumn
                char position = gameBoard[row][col];
                if (position == ship){
                    System.out.print(water + " ");
                } else System.out.print(position + " ");
            }
            System.out.println();
        }
    }

    private static char[][] createGameBoard(int gameBoardLength, char hit, char miss, char water, char battleShip, char destroyer1, char destroyer2, int shipNumber, char ship) {
        char [][] gameBoard = new char[gameBoardLength][gameBoardLength];
        for (char [] row : gameBoard){
            Arrays.fill(row, water);
        } return placeShips(gameBoard, water, battleShip, destroyer1, destroyer2, shipNumber, ship);

    }

    private static char[][] placeShips(char[][] gameBoard, char water, char battleShip, char destroyer1, char destroyer2, int shipNumber, char ship) {
        int placedDestroyers1 = 0;
        int gameboardLenght = gameBoard.length;

        while (placedDestroyers1 <4){
            int[] location = generateShipLocation(gameboardLenght);
            char possibilityOfPlacment = gameBoard[location[0]][location[1]];
            if (possibilityOfPlacment == water){
                gameBoard[location[0]][location[1]] = destroyer1;
                placedDestroyers1++;
            }
        } return gameBoard;
    }

    private static int[] generateShipLocation(int gameboardLenght) {
        int [] location = new int [2];

        for (int i = 0; i < location.length; i++){
            location[i] = new Random().nextInt(gameboardLenght);
        } return location;

    }
}
