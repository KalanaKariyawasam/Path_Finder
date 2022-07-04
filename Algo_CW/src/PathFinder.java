//import libraries
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class PathFinder {
    //Create an objects and methods
    Maze m = new Maze();
    boolean[][] visited = new boolean[m.countRows(m.getFilename())][m.countRows(m.getFilename())];
    Queue<Position> queue = new LinkedList<>();
    LinkedList<Position> parents = new LinkedList<>();
    Stack<Position> paths = new Stack<>();
    Position[] shortestPath = null;

    //path find method for find path
    public Position[] findPath() {

        m.readFile();
        int sRow = m.getStartingRow();
        int sCol = m.getStartingColumn();
        Position source = new Position(sRow, sCol);
        queue.add(source);

        visited[source.getRow()][source.getColumn()] = true;

        while (!queue.isEmpty()) {
            Position p = queue.remove();
            //add previous node to the parents array
            parents.add(p);
            //check destination
            if(m.getMaze()[p.getRow()][p.getColumn()] == 'F') {
                break;
            }
            //call search neighbours method
            searchNeighbours(p);
        }

        //get previous node and add it to the shortest path array
        Position newPosition = parents.getLast();
        if(newPosition != null) {
            do {
                paths.push(newPosition);
                newPosition = newPosition.getPreviousPosition();
            }
            while (newPosition != null);
            shortestPath = new Position[paths.size()];
            int count = 0;
            while (!paths.isEmpty()) {
                shortestPath[count++] = paths.pop();
            }
        }
        return shortestPath;

    }

    //search neighbours method for search neighbours
    public void searchNeighbours(Position p) {
        // moving up
        if (isValid(p.getRow() - 1, p.getColumn(), m.getMaze(), visited)) {
            queue.add(new Position(p.getRow() - 1, p.getColumn(), p, "Moved Up"));
            visited[p.getRow() - 1][p.getColumn()] = true;
        }

        // moving down
        if (isValid(p.getRow() + 1, p.getColumn(), m.getMaze(), visited)) {
            queue.add(new Position(p.getRow() + 1, p.getColumn(), p, "Moved Down"));
            visited[p.getRow() + 1][p.getColumn()] = true;
        }

        // moving left
        if (isValid(p.getRow(), p.getColumn() - 1, m.getMaze(), visited)) {
            queue.add(new Position(p.getRow(), p.getColumn() - 1,  p, "Moved Left"));
            visited[p.getRow()][p.getColumn() - 1] = true;
        }

        // moving right
        if (isValid(p.getRow(), p.getColumn() + 1, m.getMaze(), visited)) {
            queue.add(new Position(p.getRow(), p.getColumn() + 1, p, "Moved Right"));
            visited[p.getRow()][p.getColumn() + 1] = true;
        }
    }

    //isValid method for check next movement is valid or not
    private boolean isValid(int row, int column, char[][] maze, boolean[][] visited) {
        if((row >= 0) && (column >= 0) && (row < maze.length) && (column < maze[0].length)
            && (maze[row][column] != '0') && (!visited[row][column])) {
            return true;
        }
        return false;
    }

    //printPath method for print the path
    public void printPath() {
        long startTime = System.currentTimeMillis();
        Position[] paths = this.findPath();
        long endTime = System.currentTimeMillis();
        long totalTime = (endTime - startTime);
        if (paths != null) {
            int i = 1;
            System.out.println(i + ". Start at " + "("+ (paths[0].getRow()) + "," + (paths[0].getColumn()) + ")");
            for(int x = 1; x < paths.length; x++) {
                System.out.println((++i) + ". " +  paths[x].getDirection() + " to ("+ (paths[x].getRow()) + "," + (paths[x].getColumn()) + ")");
            }
            System.out.println("\nDone. Path found!");
            System.out.println("\nTime Elapsed : " + totalTime + " milliseconds.");
        }
    }
}
