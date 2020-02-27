
import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Figures {

    private List<Figure> figuresList;

    public Figures() {

        figuresList = new ArrayList<>();
        List<String> input = txtParser.getInstance().readTxtFile("Figures.txt");

        for (String line : input) {
            Figure figure = new Figure();
            figure.transformToVertex(line, figure);
            figuresList.add(figure);
        }
    }

    public List<Figure> getFiguresList() {
        return figuresList;
    }
    
    /**
     * This method checks if the line the state and potentialNeighbour vertex make in the graph doesn't intersect a Rhombus
     * @param state
     * @param potentialNeighbour
     * @return true of false
     */
    private boolean doesLineIntersectRhombus(Vertex state, Vertex potentialNeighbour) {
        for (Figure rhombus : figuresList) {

            int i = 1;

            //Going through all the verticies in the rhombus to see which one the state could potentially connect to
            //check each side of the rhombus to make sure the state isn't going through
            for (Vertex rhombusVertex : rhombus.verticies) {

                boolean linesIntersect = rhombusVertex.linesIntersect(state, potentialNeighbour, rhombusVertex, rhombus.verticies.get(i));
               // System.err.println(linesIntersect);
                if (linesIntersect == true) {
                    System.err.println("Line intersects - Do not add neighbour");
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

}
