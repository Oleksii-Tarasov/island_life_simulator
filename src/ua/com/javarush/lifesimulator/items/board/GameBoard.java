package ua.com.javarush.lifesimulator.items.board;

public class GameBoard {
    private final Cell[][] gameBoard;

    public GameBoard(Cell[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    public Cell getCell(int y, int x) {
        return gameBoard[y][x];
    }

    public void setCell(Cell cell, int y, int x) {
        gameBoard[y][x] = cell;
    }
}
