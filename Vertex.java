import java.util.*;

public class Vertex implements Comparable<Vertex>
{
    private int x;
    private int y;

    Vertex(int x, int y)
    {
       	this.x = x;
       	this.y = y;
    }

    public int getX()
    {
       	return x;
    }

    public int getY()
    {
       	return y;
    }

    public int compareTo(Vertex v)
    {
        //System.err.println(v.getX() + " " + v.getY() + " " +  getX() + " " + getY());
        if (v.getX() == getX()) {
	    return v.getY() - getY();
	} else {
	    return v.getX() - getX();
	}
    }

    public boolean equals(Object o)
    {
	if (o instanceof Vertex)
	{
	    Vertex v = (Vertex) o;
	    return compareTo(v) == 0;
        }
	else return false;
    }

//    public static boolean crossingFigure(Vertex cur, Vertex go, ArrayList<Figure> figures) {
//        
//    }
    public static boolean vertexIntersect(Vertex u, Vertex v1, Vertex v2)
    {
	/* check whether the vertex u falls within a given line segment where
           the line segment connects vertices v1 and v2 (but does not include them) */

        
        int a = v1.getX() - v2.getX();
        int b = u.getX() - v2.getX();
    					    
        int c = v1.getY() - v2.getY();
        int d = u.getY() - v2.getY();
        

	/* check whether the simultaneous equations a mu = b and c mu = d
           has a solution 0 < mu < 1 
           where a and c are coefficients of the variable mu
           where b and d are constants */

	if (a < 0)
	{
	    a = -a;
	    b = -b;
	}

	if (c < 0)
	{
	    c = -c;
	    d = -d;
	}

	if (a == 0 && b == 0)
	{
	    return 0 < d && d < c;
	}
	else if (c == 0 && d == 0)
	{
	    return 0 < b && b < a;
	}
	else
	{
	    return 0 < b && b < a && 0 < d && d < c && a * d == b * c;
	}
    }

    public static boolean linesIntersect(Vertex u1, Vertex u2, Vertex v1, Vertex v2)
    {
	/* check whether line segment 1 intersects line segment 2 where
           line segment 1 connects vertices u1 and u2 (but does not include them) and
           line segment 2 connects vertices v1 and v2 (but does not include them) */

//        System.err.println(u1.getX() + " " + u1.getY());
//        System.err.println(u2.getX() + " " + u2.getY());
//        System.err.println(v1.getX() + " " + v1.getY());
//        System.err.println(v2.getX() + " " + v2.getY());
	int a = u1.getX() - u2.getX();
        int b = v2.getX() - v1.getX();
        int c = v2.getX() - u2.getX(); 		
    					    
	int d = u1.getY() - u2.getY();
        int e = v2.getY() - v1.getY();
        int f = v2.getY() - u2.getY();

	/* check whether the simultaneous equations a mu + b lambda = c and d mu + e lambda = f
           has a solution 0 < mu < 1 and 0 < lambda < 1 
           where a and d are coefficients of the variable mu
           where b and e are coefficients of the variable lambda
           where c and f are constants */

        Predicate intersectPredicate = (int denominator, int lambda_numerator, int mu_numerator) -> 
                0 < lambda_numerator && lambda_numerator < denominator &&
                0 < mu_numerator && mu_numerator < denominator;
        
	return solve(a, b, c, d, e, f, intersectPredicate);
    }

    private static boolean solve(int a, int b, int c, int d, int e, int f, Predicate p)
    {
	int denominator = d * b - a * e;

	if (denominator == 0)
	{
	    return false;
	}
	else 
	{
	    int lambda_numerator = d * c - a * f;
	    int mu_numerator = b * f - e * c;

	    if (denominator < 0)	
	    {
	    	lambda_numerator = -lambda_numerator;
	    	mu_numerator = -mu_numerator;
	    	denominator = -denominator;
	    }

	    return p.predicate(denominator, lambda_numerator, mu_numerator);
	}	
    }

    public String toString()
    {
	return "(" + x + ", " + y + ")";
    }

    interface Predicate
    {
        boolean predicate(int denominator, int lambda_numerator, int mu_numerator);
    }   
}