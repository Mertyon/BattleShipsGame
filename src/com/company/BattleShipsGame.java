
package com.company;


import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class BattleShipsGame {
    public static void main(String[] args) {
        char hit = 'X';
        char miss = 'O';
        char water = '~';
        char destroyer2 = 'd';
        char destroyer1 = 'D';
        char battleShip = 'B';
        char ship = 's';    //test

        int destroyer1Life = 4;
        int destroyer2Life = 4;
        int battleShipLife = 5;
        int gameBoardLenght = 10;


        int shipsLife = hpLoss(destroyer1Life, destroyer2Life, battleShipLife, hit, destroyer1, destroyer2, battleShip);

        System.out.println( );
        char [][] gameBoard = createGameBoard(gameBoardLenght, water, destroyer1, destroyer2, battleShip);
        printGameBoard(gameBoard, water, destroyer1, destroyer2, battleShip, ship);
        while(shipsLife >= 0){
            int[] guessLocation = getUserGuess(gameBoardLenght);
            shipsLife = hpLoss(destroyer1Life, destroyer2Life, battleShipLife,hit ,destroyer1 , destroyer2, battleShip);
            char locationViewUpdate = evaluateGuessAndGetTarget(guessLocation, gameBoard, water, hit, miss, destroyer1, destroyer2, battleShip,battleShipLife, destroyer1Life, destroyer2Life, ship, shipsLife);
            gameBoardUpdate(gameBoard, guessLocation, locationViewUpdate);
            printGameBoard(gameBoard, water, destroyer1, destroyer2, battleShip, ship);

            System.out.println(shipsLife);
            if (shipsLife == 0 ){
                System.out.print("You won");
            }
        }
    }

    private static int hpLoss(int destroyer1Life, int destroyer2Life, int battleShipLife, char hit, char destroyer1, char destroyer2, char battleShip) {
        if (destroyer1 == hit){
            destroyer1Life--;
        }
        if (destroyer2 == hit){
            destroyer2Life--;
        }
        if (battleShip == hit){
            battleShipLife--;
        }
        return destroyer1Life + destroyer2Life + battleShipLife;
    }

    private static char[][] gameBoardUpdate(char[][] gameBoard, int[] guessLocation, char locationViewUpdate) {
        int row = guessLocation[0];
        int col = guessLocation[1];
        gameBoard[row][col] = locationViewUpdate;
        return  gameBoard;
    }

    private static char evaluateGuessAndGetTarget(int[] guessLocation, char[][] gameBoard, char water, char hit, char miss, char destroyer1, char destroyer2, char battleShip, int battleShipLife, int destroyer1Life, int destroyer2Life, char ship, int shipsLife) {
        String message = "";
        int row = guessLocation[0];
        int col = guessLocation[1];
        char target = gameBoard[row][col];

        if(shipsLife == 0){
            message = "You Won!";
        }

        if(target == hit){
            message = "Already Hit!";
        }

        if (target == destroyer1){
            if (destroyer1Life > 0){
                target = hit;
                message = "Hit!";
            }
        }

        if (target == destroyer2){
            if (destroyer2Life > 0){
                target = hit;
                message = "Hit!";

            }
        }

        if (target == battleShip){
            if (battleShipLife > 0){
                target = hit;
                message = "Hit!";
            }
        }

        if(target == water) {
            target = miss;
            message = "Miss!";
        }

        System.out.println(message);
        return target;
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

    private static void printGameBoard(char[][] gameBoard, char water, char destroyer1, char destroyer2, char battleShip, char ship) {
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
                }else if (position == ship){
                    System.out.print(water + " ");
                } else System.out.print(position + " ");
            }
            System.out.println();
        }
    }

    private static char[][] createGameBoard(int gameBoardLength,char water, char destroyer1, char destroyer2, char battleship) {
        char [][] gameBoard = new char[gameBoardLength][gameBoardLength];
        for (char [] row : gameBoard){
            Arrays.fill(row, water);
        } return placingShips(gameBoard, water, destroyer1, destroyer2, battleship);
    }

    private static char[][] placingShips(char[][] gameBoard, char water,char destroyer1, char destroyer2, char battleShip) {
        int gameBoardLenght = gameBoard.length;
        int[] location1 = generateDestroyer1Location(gameBoardLenght - 3);
        int[] location2 = generateDestroyer2Location(gameBoardLenght - 3);
        int[] locationB = generateBattleShipLocation(gameBoardLenght - 4);
        int placedDestroyer1 = 0;
        int placedDestroyer2 = 0;
        int placedBattleShip = 0;
        int x = 0;
        int y = 1;

        Random r = new Random();
        double randomValue = r.nextDouble();

        while (placedDestroyer1 < 4) {
            char possibilityOfPlacement1 = gameBoard[location1[x]][location1[y]];
            if (possibilityOfPlacement1 == water && possibilityOfPlacement1 != battleShip && possibilityOfPlacement1 != destroyer2) {
                gameBoard[location1[x]][location1[y]] = destroyer1;
            }
            placedDestroyer1++;
            location1[y]++;
        }

        while (placedDestroyer2 < 4) {
            char possibilityOfPlacement2 = gameBoard[location2[x]][location2[y]];
            if (possibilityOfPlacement2 == water && possibilityOfPlacement2 != battleShip && possibilityOfPlacement2 != destroyer2) {
                gameBoard[location2[x]][location2[y]] = destroyer2;
            }
            placedDestroyer2++;
            location2[x]++;
        }

        if (randomValue >= 0.5) {
            while (placedBattleShip < 5) {
                char possibilityOfPlacementB = gameBoard[locationB[x]][locationB[y]];
                if (possibilityOfPlacementB == water && possibilityOfPlacementB != destroyer1 && possibilityOfPlacementB != destroyer2) {
                    gameBoard[locationB[x]][locationB[y]] = battleShip;
                }
                placedBattleShip++;
                locationB[y]++;
            }
        }

        if (randomValue < 0.5) {
            while (placedBattleShip < 5) {
                char possibilityOfPlacementB = gameBoard[locationB[x]][locationB[y]];
                if (possibilityOfPlacementB == water && possibilityOfPlacementB != destroyer1 && possibilityOfPlacementB != destroyer2) {
                    gameBoard[locationB[x]][locationB[y]] = battleShip;
                }
                placedBattleShip++;
                locationB[x]++;
            }
        }return gameBoard;
    }

    private static int[] generateBattleShipLocation(int gameBoardLenght) {
        int [] locationOfBattleShip = new int [4];
        for (int i = 0; i < locationOfBattleShip.length; i++){
            locationOfBattleShip[i] = new Random().nextInt(gameBoardLenght);
        } return locationOfBattleShip;
    }

    private static int[] generateDestroyer1Location(int gameBoardLenght) {
        int [] locationOfDestroyer1 = new int [4];
        for (int i = 0; i < locationOfDestroyer1.length; i++){
            locationOfDestroyer1[i] = new Random().nextInt(gameBoardLenght);
        } return locationOfDestroyer1;
    }

    private static int[] generateDestroyer2Location(int gameBoardLenght) {
        int [] locationOfDestroyer2 = new int [5];
        for (int i = 0; i < locationOfDestroyer2.length; i++){
            locationOfDestroyer2[i] = new Random().nextInt(gameBoardLenght);
        } return locationOfDestroyer2;
    }
}
