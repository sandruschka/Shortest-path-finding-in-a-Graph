
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
            //txtParser.getInstance().transformToVertex(line, figure);
            figure.transformToVertex(line, figure);
            figuresList.add(figure);
//            Figure figure = new Figure();
//            String[] verticies = line.split("\\)");
//            // check to make sure you have valid data
//            for (int i = 0; i < verticies.length; i++) {
//                verticies[i] = verticies[i].replaceAll("[()]", "");
//                verticies[i] = verticies[i].replaceAll(",", "");
//
//                String[] vertex = verticies[i].split(" ");
//                if (vertex != null && vertex.length != 2) {
//                    continue;
//                }
//                figure.addVertex(Integer.valueOf(vertex[0]), Integer.valueOf(vertex[1]));
//            }
//            figuresList.add(figure);
        }
    }

    public List<Figure> getFiguresList() {
        return figuresList;
    }

}
