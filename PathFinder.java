
import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PathFinder {

   
    private static Puzzles puzzles;
    private static Map<Vertex, List<Vertex>> neighbours;
    private static Solution solution;

    public static void main(String args[]) {
        PathFinder planner = new PathFinder();
        solution = new Solution();
        puzzles = new Puzzles();
        neighbours = new Graph().getNeighbours();
        
       for (int puzzleNumber = 0; puzzleNumber < puzzles.puzzleList.size(); puzzleNumber++) {
            LinkedList<Vertex> route = planner.iterativeDeepening(
                    puzzles.getCurrentPuzzleStart(puzzleNumber),
                    puzzles.getCurrentPuzzleFinish(puzzleNumber));
            solution.writeSolutionToFile(route, puzzleNumber + 1);
       }
    }
    
    public static List<Vertex> nextConfigs(Vertex state) {
        for (Vertex key : neighbours.keySet())
            if (state.getX() == key.getX() && state.getY() == key.getY())
                return neighbours.get(key);
        return null;
    }

    public LinkedList<Vertex> iterativeDeepening(Vertex start, Vertex finish) {
        for (int depth = 1; true; depth++) {
            LinkedList<Vertex> route = depthFirst(start, finish, depth);
            if (route != null) {
                return route;
            }
        }
    }

    private LinkedList<Vertex> depthFirst(Vertex start, Vertex finish, int depth) {
        if (depth == 0) {
            return null;
        } else if (start.equals(finish)) {
            LinkedList<Vertex> route = new LinkedList<>();
            route.add(finish);
            return route;
        } else {
            List<Vertex> nexts = nextConfigs(start);
            for (Vertex next : nexts) {
                LinkedList<Vertex> route = depthFirst(next, finish, depth - 1);
                if (route != null) {
                    route.addFirst(start);
                    return route;
                }
            }
            return null;
        }
    }
}
