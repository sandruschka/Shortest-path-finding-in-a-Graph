
import java.util.ArrayList;
import java.util.List;

class Puzzles {
    private int currentPuzzle;
    private List<Puzzle> puzzleList;

    public Puzzles() {
        currentPuzzle = 0;
        puzzleList = new ArrayList<>();
        List<String> input = txtParser.getInstance().readTxtFile("puzzle.txt");

        
        for (String line : input) {
            Puzzle puzzle = new Puzzle();
            puzzle.transformToVertex(line, puzzle);
            puzzleList.add(puzzle);
        }
        
//        for (Puzzle puzzle : puzzleList) {
//            System.err.println(puzzle.getStart().getX() + " " + puzzle.getStart().getY());
//        }
    }
    
    public int getCurrentPuzzle() {
        return currentPuzzle;
    }
    
    public Vertex getCurrentPuzzleStart() {
        return puzzleList.get(currentPuzzle).getStart();
    }
    
    public Vertex getCurrentPuzzleFinish() {
        return puzzleList.get(currentPuzzle).getFinish();
    }
    
    public void setCurrentPuzzle(int currentPuzzle) {
        this.currentPuzzle = currentPuzzle;
    }
    
}