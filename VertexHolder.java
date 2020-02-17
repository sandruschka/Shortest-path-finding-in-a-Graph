import java.util.*;

public abstract class VertexHolder {
    protected List<Vertex> verticies;

    public VertexHolder() {
        verticies = new ArrayList<>();
    }
    
    protected void addVertex(int x, int y) {
        verticies.add(new Vertex(x, y));
    }
    
    protected void transformToVertex(String line, VertexHolder obj) {
       
        String[] verticies_ = line.split("\\)");
        // check to make sure you have valid data
        for (int i = 0; i < verticies_.length; i++) {
            verticies_[i] = verticies_[i].replaceAll("[()]", "");
            verticies_[i] = verticies_[i].replaceAll(",", "");

            String[] vertex = verticies_[i].split(" ");
            if (vertex != null && vertex.length != 2) {
                continue;
            }
            obj.addVertex(Integer.valueOf(vertex[0]), Integer.valueOf(vertex[1]));
        }
       
    }
}