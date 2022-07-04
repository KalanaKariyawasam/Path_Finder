public class Main {
    //main method
    public static void main(String[] args) {
        System.out.println();
        //pass the file
        Maze.filename = "examples_2/puzzle_20.txt";
        //creating an object from PathFinder class
        PathFinder pFind = new PathFinder();
        //call methods
        pFind.printPath();
    }

}
