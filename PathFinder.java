
import java.util.*;
import java.io.*;

public class PathFinder {

    private static Figures figures;
    private static Puzzles puzzles;
    private static Map<Vertex, List<Vertex>> neighbours;

    public static void main(String args[]) {
        neighbours = new HashMap<>();
        PathFinder planner = new PathFinder();
        figures = new Figures();
        puzzles = new Puzzles();
        //Vertex start = new Vertex(17, 21);
        //Vertex finish = new Vertex(0, 3);
        nextConfigs(puzzles.getCurrentPuzzleStart());
        //LinkedList<Vertex> route = planner.iterativeDeepening(start, finish);	
        //System.out.println("route = " + route);
    }

    private static boolean doesLineIntersectRhombus(Vertex state, Vertex potentialNeighbour) {
        for (Figure rhombus : figures.getFiguresList()) {

            int i = 1;

            //go through all the verticies in the figures to see which one the state could potentially connect to
            //check each side of the rhombus to make sure the state isn't going through
            for (Vertex vertex : rhombus.verticies) {

                boolean linesIntersect = vertex.linesIntersect(state, potentialNeighbour, vertex, rhombus.verticies.get(i));
               // System.err.println(linesIntersect);
                if (linesIntersect == true) {
                    System.out.println("Don't add neighbour");
                    //addNeighbour = false;
                    return true;
                }
                i++;
                if (i >= (rhombus.verticies.size() - 1)) {
                    i = 0;
                }
            }
        }
        return false;
    }

    public static List<Vertex> nextConfigs(Vertex state) {

        System.err.println(state.getX() + " " + state.getY());
        //check the graph of neighbours if it already exists.
        if (neighbours.containsKey(state)) {
            return neighbours.get(state);
        }

        //TODO add the state vertex for the neigbours 
        for (Figure figure : figures.getFiguresList()) {

            boolean addNeighbour = true;
            for (Vertex potentialNeighbour : figure.verticies) {

                if (potentialNeighbour.compareTo(state) == 0) {
                    System.err.println("they are the same");
                }
                if (figures.doesLineIntersectRhombus(state, potentialNeighbour) == true) {
                    addNeighbour = false;
                }

                if (addNeighbour == true) {
                    System.err.println("___________________");
                    System.out.println("add Neighbour");
                    System.err.println("STATE " + state.getX() + " " + state.getY());
                    System.err.println("NEIGHBOUR " + potentialNeighbour.getX() + " " + potentialNeighbour.getY());
                    System.err.println("___________________");

                    if (neighbours.containsKey(potentialNeighbour)) {
                        if (!neighbours.get(potentialNeighbour).contains(state)) {
                            neighbours.get(potentialNeighbour).add(state);
                        }
                    } else {
                        List<Vertex> vl = new ArrayList<>();
                        vl.add(state);
                        neighbours.put(potentialNeighbour, vl);
                    }
                }
            }
        }
        //the state is the solution we want to draw
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
            List<Vertex> nexts = nextConfigs(finish);
            for (Vertex next : nexts) {
                LinkedList<Vertex> route = depthFirst(next, finish, depth - 1);
                if (route != null) {
                    route.addFirst(start);
                    System.err.println(route);
                    return route;
                }
            }
            return null;
        }
    }
}
