package ua.com.javarush.lifesimulator.items.board;

public class ItemPosition {
    private int x;
    private int y;

    public ItemPosition(GameBoard gameBoard) {
        this.x = (int) (Math.random() * (gameBoard.getWidth()));
        this.y = (int) (Math.random() * (gameBoard.getHeight()));
    }

    public ItemPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
