
import java.util.ArrayList;
import java.util.List;

class Puzzles {
    public List<Puzzle> puzzleList;

    public Puzzles() {
        puzzleList = new ArrayList<>();
        List<String> input = txtParser.getInstance().readTxtFile("puzzle.txt");

        
        for (String line : input) {
            Puzzle puzzle = new Puzzle();
            puzzle.transformToVertex(line, puzzle);
            puzzleList.add(puzzle);
        }
        
//        for (Puzzle puzzle : puzzleList) {
//            System.err.println("PUZZLES\n"+ puzzle.getStart().getX() + " " + puzzle.getStart().getY());
//        }
    }
    
    public Vertex getCurrentPuzzleStart(int index) {
        return puzzleList.get(index).getStart();
    }
    
    public Vertex getCurrentPuzzleFinish(int index) {
        return puzzleList.get(index).getFinish();
    }
  
    
}