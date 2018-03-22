import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BFS_Traversal {
    private String[][] maze;
    private Queue<Point> queue;

    public BFS_Traversal() {
        this.maze = new String[][]{
                {" ", " ", " ", " ", " ", " "},
                {" ", "W", " ", " ", " ", " "},
                {" ", " ", "W", "W", " ", "W"},
                {" ", " ", " ", " ", " "},
                {" ", " ", "W", " ", " ", "X"},
        };
        this.queue = new LinkedList<>();
    }

    public Queue<Point> getQueue() {
        return queue;
    }

    public String[][] getMaze() {
        return maze;
    }

    public static void main(String[] args) {
        BFS_Traversal labyrinth = new BFS_Traversal();
        Stack<Point> directions = findAWayOut(0, 0, labyrinth);
        printTheWayOut(directions);
    }

    private static void printTheWayOut(Stack<Point> directions) {
        if (directions.isEmpty()) {
            System.out.println("There is no way out...");
        }
        Point oldPosition = new Point(0,0,null);
        while (!directions.isEmpty()) {
            Point newPosition = directions.pop();
            if (newPosition.getX() > oldPosition.getX()) {
                System.out.print("Right ");
            } else if (newPosition.getX() < oldPosition.getX()) {
                System.out.println("Left ");
            } else if (newPosition.getY() > oldPosition.getY()) {
                System.out.print("Down ");
            } else if (newPosition.getY() < oldPosition.getY()) {
                System.out.print("Up ");
            }
            oldPosition = newPosition;
        }
    }

    private static Stack<Point> findAWayOut(int x, int y, BFS_Traversal labyrinth) {
        Point firstPosition = new Point(x, y, null);
        Stack<Point> parents = new Stack<>();
        labyrinth.queue.add(firstPosition);
        while (!labyrinth.queue.isEmpty()) {
            Point currentPosition = labyrinth.queue.poll();
            if (labyrinth.getMaze()[currentPosition.y][currentPosition.x].equals("X")) {
                System.out.println("Found a way out!");
                while (currentPosition.parent != null) {
                    parents.push(currentPosition);
                    currentPosition = currentPosition.parent;
                }
                return parents;
            }
            if (isValidPosition(currentPosition.x + 1, currentPosition.y, labyrinth)) {
                labyrinth.queue.add(new Point(currentPosition.x + 1, currentPosition.y, currentPosition));
            }
            if (isValidPosition(currentPosition.x - 1, currentPosition.y, labyrinth)) {
                labyrinth.queue.add(new Point(currentPosition.x - 1, currentPosition.y, currentPosition));
            }
            if (isValidPosition(currentPosition.x, currentPosition.y + 1, labyrinth)) {
                labyrinth.queue.add(new Point(currentPosition.x, currentPosition.y + 1, currentPosition));
            }
            if (isValidPosition(currentPosition.x, currentPosition.y - 1, labyrinth)) {
                labyrinth.queue.add(new Point(currentPosition.x, currentPosition.y - 1, currentPosition));
            }
            labyrinth.getMaze()[y][x] = "*";
        }
        return null;
    }

    private static boolean isValidPosition(int x, int y, BFS_Traversal labyrinth) {
        return (y >= 0 && y < labyrinth.getMaze().length &&
                x >= 0 && x < labyrinth.getMaze()[y].length &&
                !labyrinth.getMaze()[y][x].equals("W") && !labyrinth.getMaze()[y][x].equals("*"));
    }

    private static class Point {
        private int x;
        private int y;
        private Point parent;

        public Point(int x, int y, Point parent) {
            this.x = x;
            this.y = y;
            this.parent = parent;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Point getParent() {
            return parent;
        }
    }
}
