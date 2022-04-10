
package com.company;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class BattleShipsGame {
    public static void main(String[] args) {
        char hit = 'X';
        char miss = 'O';
        char water = '~';
        char ship = 's';    //test
        char destroyer2 = 'd';
        char destroyer1 = 'D';
        System.out.println( );
        int shipNumber = 8;
        int gameBoardLenght = 10;

        char [][] gameBoard = createGameBoard(gameBoardLenght, water, destroyer1, destroyer2);
        printGameBoard(gameBoard, water, destroyer1, destroyer2, ship);
        int undetectedShips = shipNumber;
        while(undetectedShips > 0){
            int[] guessLocation = getUserGuess(gameBoardLenght);
            char locationViewUpdate = evaluateGuessAndGetTarget(guessLocation, gameBoard, water, ship, hit, miss, destroyer1, destroyer2);
            if (locationViewUpdate == hit){ // && locationViewUpdate != alreadyHit
                undetectedShips--;
            }
            gameBoardUpdate(gameBoard, guessLocation, locationViewUpdate);
            printGameBoard(gameBoard, water, destroyer1, destroyer2, ship);
        }System.out.print("You won");
    }

    private static char[][] gameBoardUpdate(char[][] gameBoard, int[] guessLocation, char locationViewUpdate) {
        int row = guessLocation[0];
        int col = guessLocation[1];
        gameBoard[row][col] = locationViewUpdate;
        return  gameBoard;
    }

    private static char evaluateGuessAndGetTarget(int[] guessLocation, char[][] gameBoard, char water, char ship, char hit, char miss, char destroyer1, char destroyer2) {
        String message;
        int row = guessLocation[0];
        int col = guessLocation[1];
        char target = gameBoard[row][col];

        if (target == destroyer1){
            target = hit;
            message = "Hit!";
        } else if (target == destroyer2){
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
        do {
            System.out.print("Row: ");
            row = new Scanner(System.in).nextInt();
        } while (row < 1 || row > gameBoardLenght + 1);
        int col;
        do {
            System.out.print("Column: ");
            col = new Scanner(System.in).nextInt();
        } while (col < 1 || col >gameBoardLenght + 1);
        return new int[]{row - 1, col - 1};
    }

    private static void printGameBoard(char[][] gameBoard, char water, char destroyer1, char destroyer2, char ship) {
        int gameBoardLength = gameBoard.length;
        System.out.print("  ");    //two spaces to align the text
        for (int i = 0; i < gameBoardLength; i++){
            System.out.print(i + 1 + " ");
        }
        System.out.println();

        for (int row = 0; row < gameBoardLength; row++){
            System.out.print(row + 1 + " ");
            for (int col = 0; col < gameBoardLength; col++){     // col = column
                char position = gameBoard[row][col];
                if (position == ship){
                    System.out.print(water + " ");
                } else if (position == ship){
                    System.out.print(water + " ");
                }
                else System.out.print(position + " ");
            }
            System.out.println();
        }
    }

    private static char[][] createGameBoard(int gameBoardLength,char water, char destroyer1, char destroyer2) {
        char [][] gameBoard = new char[gameBoardLength][gameBoardLength];
        for (char [] row : gameBoard){
            Arrays.fill(row, water);
        } return placingDestroyer1(gameBoard, water, destroyer1, destroyer2);
    }

    private static char[][] placingDestroyer1(char[][] gameBoard, char water,char destroyer1, char destroyer2) {
        int gameBoardLenght = gameBoard.length;
        int [] location1 = generateDestroyer1Location(gameBoardLenght -3);
        int [] location2 = generateDestroyer2Location(gameBoardLenght -3);

        int placedDestroyers1 = 0;
        int placedDestroyers2 = 0;
        int x = 0;
        int y = 1;

        while (placedDestroyers1 < 4){
            char possibilityOfPlacement1 = gameBoard[location1[x]][location1[y]];
            if (possibilityOfPlacement1 == water && possibilityOfPlacement1 != destroyer1 && possibilityOfPlacement1 != destroyer2 ){
                    gameBoard[location1[x]][location1[y]] = destroyer1;
            }
            placedDestroyers1++;
            location1[y]++;
        }

        while (placedDestroyers2 < 4){
            char possibilityOfPlacement2 = gameBoard[location2[x]][location2[y]];
            if (possibilityOfPlacement2 == water && possibilityOfPlacement2 != destroyer1 && possibilityOfPlacement2 != destroyer2 ){
                    gameBoard[location2[x]][location2[y]] = destroyer2;
            }
            placedDestroyers2++;
            location2[x]++;
        }return gameBoard;

    }

    private static int[] generateDestroyer1Location(int gameBoardLenght) {
        int [] locationOfDestroyer1 = new int [4];
        for (int i = 0; i < locationOfDestroyer1.length; i++){
            locationOfDestroyer1[i] = new Random().nextInt(gameBoardLenght);
        } return locationOfDestroyer1;
    }

    private static int[] generateDestroyer2Location(int gameBoardLenght) {
        int [] locationOfDestroyer2 = new int [4];
        for (int i = 0; i < locationOfDestroyer2.length; i++){
            locationOfDestroyer2[i] = new Random().nextInt(gameBoardLenght);
        } return locationOfDestroyer2;
    }
}
