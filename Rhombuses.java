
import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Rhombuses {

    private List<Rhombus> rhombusList;

    public Rhombuses() {

        rhombusList = new ArrayList<>();
        List<String> input = txtParser.getInstance().readTxtFile("Rhombuses.txt");

        for (String line : input) {
            Rhombus figure = new Rhombus();
            figure.transformToVertex(line, figure);
            rhombusList.add(figure);
        }
    }

    public List<Rhombus> getRhombusList() {
        return rhombusList;
    }

    /**
     *
     * @param v The vertex we want to check could potentially be inside a rhombus
     * @param rhombusVertiecies the list of the rhombus' verticies
     * @return true of the intersections are odd. The calculation that I use can
     * be found in this tutorial:
     * https://www.geeksforgeeks.org/how-to-check-if-a-given-point-lies-inside-a-polygon/
     */
    
    private boolean isVertexOnARhombusLine(Vertex v, List<Vertex> rhombusVertiecies) {
         Vertex rhombusVertex, nextRhombusVertex;
            
          for (int i = 0; i < rhombusVertiecies.size(); i++) {
            rhombusVertex = rhombusVertiecies.get(i);
            nextRhombusVertex = (((i + 1) == rhombusVertiecies.size()) ? rhombusVertiecies.get(0) : rhombusVertiecies.get(i + 1));
            if (v.vertexIntersect(v, rhombusVertex, nextRhombusVertex) == true) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isVertexInsideRhombus(Vertex v, List<Vertex> rhombusVertiecies) {
        int MAX_X = 23;
        int ODD = 1;
        int countIntersections = 0;
        Vertex drawHorizintalLine = new Vertex(MAX_X, v.getY());

        if (v.linesIntersect(
                v,
                drawHorizintalLine,
                rhombusVertiecies.get(RhombusSides.TOP_RIGHT.ordinal()),
                rhombusVertiecies.get(RhombusSides.BOTTOM_RIGHT.ordinal())) == true) { //ordinal is converting the enum value to an integer
            countIntersections += 1;
        }

        if (v.linesIntersect(
                v,
                drawHorizintalLine,
                rhombusVertiecies.get(RhombusSides.TOP_LEFT.ordinal()),
                rhombusVertiecies.get(RhombusSides.BOTTOM_LEFT.ordinal())) == true) {
            countIntersections += 1;
        }
        
        /* Special case where a vertex is on a rhombus line, which is considered to be inside a rhombus. 
        ** However for these cases we don't want to discard the vertex.
        */
        if (isVertexOnARhombusLine(v, rhombusVertiecies) == true && countIntersections == ODD)
            return false;

        return countIntersections == ODD;
    }
    
    
    private boolean doesVertexLineTravelWitinItself(Vertex state, Vertex potentialNeighbour, List<Vertex> rhombusVertiecies) {
          //TODO check of line travers inside a rhombus
            boolean linesIntersect = state.linesIntersect(
                    state,
                    potentialNeighbour,
                    rhombusVertiecies.get(RhombusSides.BOTTOM_RIGHT.ordinal()),
                    rhombusVertiecies.get(RhombusSides.TOP_LEFT.ordinal()));
            if (linesIntersect == true) {
                return true;
            }
            linesIntersect = state.linesIntersect(
                    state,
                    potentialNeighbour,
                    rhombusVertiecies.get(RhombusSides.BOTTOM_LEFT.ordinal()),
                    rhombusVertiecies.get(RhombusSides.TOP_RIGHT.ordinal()));
            if (linesIntersect == true) {
                return true;
            }
         return false;
         
    }

    /**
     * This method checks if the line the state and potentialNeighbour vertex
     * make in the graph doesn't intersect or is within a Rhombus
     *
     * @param state
     * @param potentialNeighbour
     * @return true of false
     */
    public boolean doesLineIntersectRhombus(Vertex state, Vertex potentialNeighbour) {
        for (Rhombus rhombus : rhombusList) {

            Vertex rhombusVertex, nextRhombusVertex;

            if (doesVertexLineTravelWitinItself(state, potentialNeighbour, rhombus.verticies)) {
                return true;
            }

            if (isVertexInsideRhombus(state, rhombus.verticies) == true) {
               // System.err.println("The state is within a rhombus");
                return true;
            }
            if (isVertexInsideRhombus(potentialNeighbour, rhombus.verticies) == true) {
                //System.err.println("The potential Neighbour is within a rhombus");
                return true;
            }

            //Going through all the verticies in the rhombus to see which one the state could potentially connect to
            //check each side of the rhombus to make sure the state isn't going through
            for (int i = 0; i < rhombus.verticies.size(); i++) {
                rhombusVertex = rhombus.verticies.get(i);
                nextRhombusVertex = (((i + 1) == rhombus.verticies.size()) ? rhombus.verticies.get(0) : rhombus.verticies.get(i + 1));
//            for (Vertex rhombusVertex : rhombus.verticies) {

                boolean linesIntersect = rhombusVertex.linesIntersect(state, potentialNeighbour, rhombusVertex, nextRhombusVertex);
                if (linesIntersect == true) {
                    return true;
                }
            }
        }
        return false;
    }

}
