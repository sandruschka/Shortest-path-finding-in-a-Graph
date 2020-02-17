import java.util.*;
import java.io.*;

public class PathFinder
{
    private static Figures figures;
    public static void main(String args[])
    {
   	        
	PathFinder planner = new PathFinder();
         figures = new Figures();
         Vertex start = new Vertex(17, 21);
         Vertex finish = new Vertex(0,3);
         nextConfigs(start);
	//LinkedList<Vertex> route = planner.iterativeDeepening(start, finish);	
	//System.out.println("route = " + route);
    }
   
    public static List<Vertex> nextConfigs(Vertex state) {  
        
        for (Figure figure : figures.getFiguresList()) {
            System.err.println("figure");
            int i = 1;
            for (Vertex vertex : figure.verticies) {
                //System.err.println(vertex.getX() + " " + vertex.getY());
                System.err.println("system intersect");
                System.err.println(vertex.linesIntersect(state, new Vertex(0, 1), vertex, figure.verticies.get(i)));
                System.err.println("___________________");
                i++;
                if (i >= 4)
                    i = 1;
            }
         }   
        //the state is the solution we want to draw
         return null;
    }
    
    public LinkedList<Vertex> iterativeDeepening(Vertex start, Vertex finish) {
        for (int depth = 1; true; depth++)
        {
             LinkedList<Vertex> route = depthFirst(start, finish, depth);
             if (route != null) return route;
        }
    }

    private LinkedList<Vertex> depthFirst(Vertex start, Vertex finish, int depth)
    {   
	if (depth == 0)
	{
	    return null;
	}
	else if (start.equals(finish)) 
	{    
	    LinkedList<Vertex> route = new LinkedList<>();
	    route.add(finish);
	    return route;
        }
	else
        {   
	    List<Vertex> nexts = nextConfigs(finish);
	    for (Vertex next : nexts)
	    {   
	        LinkedList<Vertex> route = depthFirst(next, finish, depth - 1);
		if (route != null)
		{
		    route.addFirst(start);
                      System.err.println(route);
		    return route;
		}
            }
	    return null;
	}
    }
}