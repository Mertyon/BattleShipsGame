package com.company;

import java.util.Arrays;
import java.util.Random;

public class BattleShipsGame {

    public static void main(String[] args) {
        char hit = 'X';
        char miss = 'O';
        char water = '~';
        char ship = 's'

        char battleShip = 'B';
        char destroyer1 = 'D';
        char destroyer2 = 'd';
        int shipNumber = 0;

        int gameBoardLenght = 10;
        int battleShipLenght = 5;

        int destroyer1Lenght = 4;
        int destroyer2Lenght = 4;


        char [][] gameBoard = createGameboard(gameBoardLenght, hit, miss, water, battleShip, destroyer1, destroyer2, shipNumber, ship);
        printGameBoard(gameBoard, water, ship);
    }

    private static void printGameBoard(char[][] gameBoard, char water, char ship) {
        int gameBoardLenght = gameBoard.length;
        for (int row = 0; row < gameBoardLenght; row++){
            for (int col = 0; col < gameBoardLenght; col++){ // col = collumn
                
            }
        }
    }

    private static char[][] createGameboard(int gameBoardLenght, char hit, char miss, char water, char battleShip, char destroyer1, char destroyer2, int shipNumber, char ship) {
        char [][] gameBoard = new char[gameBoardLenght][gameBoardLenght];
        for(char [] row : gameBoard){
            Arrays.fill(row, water);
        }return placeShips(gameBoard, water, battleShip, destroyer1, destroyer2, shipNumber, ship);

    }

    private static char[][] placeShips(char[][] gameBoard, char water, char battleShip, char destroyer1, char destroyer2, int shipNumber, char ship) {
        int placedShips = 0;
        int gameboardLenght = gameBoard.length;
        while (placedShips < shipNumber){
            int [] location = generateShipLocation(gameboardLenght);
            char possibilityOfPlacment = gameBoard[location[0]][location[1]];
            if (possibilityOfPlacment == water){
                gameBoard[location[0]][location[1]] = ship;;
                placedShips++;
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
