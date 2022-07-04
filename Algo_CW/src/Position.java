public class Position {
    //Create Variables
    private int row;
    private int column;
    private String direction;
    private Position previousPosition;

    //Constructors
    public Position(int row, int column, Position previousPosition, String direction) {
        this.row = row;
        this.column = column;
        this.previousPosition = previousPosition;
        this.direction = direction;
    }

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    //Getters
    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public String getDirection() {
        return direction;
    }

    public Position getPreviousPosition() {
        return previousPosition;
    }
}
