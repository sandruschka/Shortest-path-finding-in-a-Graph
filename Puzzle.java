
class Puzzle extends VertexHolder {

    private static int START = 0;
    private static int FINISH = 1;
    
    public Puzzle() {
        super();
    }

    public Vertex getStart(){
        return super.verticies.get(START);
    }
    
    public Vertex getFinish(){
        return super.verticies.get(FINISH);
    }
}