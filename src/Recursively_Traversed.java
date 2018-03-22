import java.util.ArrayList;

public class Recursively_Traversed {
    private String[][] labyrinth;
    private int rows;
    private ArrayList<String> directions;

    public Recursively_Traversed() {
        this.labyrinth = new String[][]{
                {" ", " ", " ", " ", " "},
                {"*", " ", "*", "*", " "},
                {" ", " ", " ", " ", "e"},
        };
        this.rows = labyrinth.length;
        directions = new ArrayList<>();
    }

    public static void main(String[] args) {
        Recursively_Traversed labyrinth = new Recursively_Traversed();
        labyrinth.findExit(0, 0, "Beginning");
    }

    void findExit(int row, int col, String dir) {
        if (isInvalidPosition(row, col)) {
            return;
        }
        if (labyrinth[row][col].equals("e")) {
            System.out.println("Found a way!");
            directions.add("Exit");
            printDirections(directions);
        }
        if (!labyrinth[row][col].equals(" ")) {
            return;
        }
        directions.add(dir);
        labyrinth[row][col] = "S";
        findExit(row + 1, col, "Down");
        findExit(row, col - 1, "Left");
        findExit(row - 1, col, "Up");
        findExit(row, col + 1, "Right");
        directions.remove(dir);
        labyrinth[row][col] = " ";
    }

    private boolean isInvalidPosition(int row, int col) {
        return row < 0 || col < 0 || row >= rows || col >= labyrinth[row].length;
    }

    private void printDirections(ArrayList<String> directions) {
        for (String dir : directions) {
            System.out.print(dir + " ");
        }
        System.out.println();
        directions.clear();
        directions.add("Beginning");
    }

    public String[][] getLabyrinth() {
        return labyrinth;
    }

    public int getRows() {
        return rows;
    }
}
