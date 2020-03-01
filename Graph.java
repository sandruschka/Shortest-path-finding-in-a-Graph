import java.util.*;

class Graph {
    private Map<Vertex, List<Vertex>> neighbours;
     private static Rhombuses rhombuses;
     
    
    public Graph() {
         neighbours = new HashMap<>();
         rhombuses = new Rhombuses();
        
         for (Rhombus rhombus : rhombuses.getRhombusList()) {
            for (Vertex state : rhombus.verticies) {
                createNeighbourGraph(state);
            }
         }
    }

    public Map<Vertex, List<Vertex>> getNeighbours() {
        return neighbours;
    }

    
    private void createNeighbourGraph(Vertex state) {
        if (neighbours.containsKey(state))
            return;

        List<Vertex> stateNeighbours = new ArrayList<>();
        
        for (Rhombus rhombus : rhombuses.getRhombusList()) {
            for (Vertex potentialNeighbour : rhombus.verticies) {
                if (rhombuses.doesLineIntersectRhombus(state, potentialNeighbour) == false && !state.equals(potentialNeighbour)) {
                        stateNeighbours.add(potentialNeighbour);
                }
            }
        }
        neighbours.put(state, stateNeighbours);
    }
}