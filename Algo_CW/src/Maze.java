//import libraries
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Maze {
    //Create Variables
    private int startingRow;
    private int startingColumn;
    private int endingRow;
    private int endingColumn;
    protected static String filename;
    private char[][] maze = new char[countRows(filename)][];

    //Getters
    public int getStartingRow() {
        return startingRow;
    }

    public int getStartingColumn() {
        return startingColumn;
    }

    public String getFilename() {
        return filename;
    }

    public char[][] getMaze() {
        return maze;
    }

    //read file method for read the file and store to the maze array
    public void readFile() {
        String line;
        int row = 0;
        int column;
        try{
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);

            while ((line = br.readLine()) != null) {
                maze[row] = line.toCharArray();
                for (column = 0; column < maze[row].length; column++) {
                    if((maze[row][column]) == 'S') {
                        this.startingRow = row;
                        this.startingColumn = column;
                    }

                    if((maze[row][column]) == 'F') {
                        this.endingRow= row;
                        this.endingColumn = column;
                    }
                }
                row++;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        //print maze, starting node and ending node
        printMaze(maze);
        System.out.println("\nStarting Node: (" + startingRow + ", " + startingColumn + ")");
        System.out.println("Ending Node: (" + endingRow + ", " + endingColumn + ")");
        System.out.println();
    }

    //rows count method for counting rows and define the maze array size
    public int countRows(String filename) {
        int rows = 0;
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);

            while ((br.readLine()) != null) {
                rows++;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return rows;
    }

    //print maze method for print maze
    public void printMaze(char[][] maze) {
       for (int i = 0; i < maze.length; i++) {
           for (int j = 0; j < maze[i].length; j++) {
               System.out.print(maze[i][j] + " ");
           }
           System.out.println();
       }
    }
}
