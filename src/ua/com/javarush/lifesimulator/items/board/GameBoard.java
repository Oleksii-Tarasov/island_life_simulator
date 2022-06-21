package ua.com.javarush.lifesimulator.items.board;

public class GameBoard {
    private final Cell[][] gameBoard;
    int width;
    int height;

    public GameBoard(Cell[][] gameBoard, int width, int height) {
        this.gameBoard = gameBoard;
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Cell getCell(int y, int x) {
        return gameBoard[y][x];
    }

    public void setCell(Cell cell, int y, int x) {
        gameBoard[y][x] = cell;
    }
}
