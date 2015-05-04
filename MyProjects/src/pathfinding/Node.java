package pathfinding;

public class Node implements Comparable<Node> {
    
    private String id;
    private int x;
    private int y;
    private double cost = 0;
    
    
    public static final double START_COST = 0.00;
    public static final double FLATLAND_COST = 1.00;
    public static final double FOREST_COST = 2.00;
    public static final double MOUNTAIN_COST = 3.00;
    
    
    public static final String START = "@";
    public static final String DESTINATION = "X";
    public static final String FLATLAND = ".";
    public static final String FOREST = "*";
    public static final String MOUNTAIN = "^";
    public static final String WATER = "~";
    
    Node parent = null;
    
    public Node(int x, int y, double cost, String id){
           this.id = id;
           this.x = x;
           this.y = y;
           this.cost = cost;
    }
    
    public Node(int x, int y)
    {
           this.x = x;
           this.y = y;
    }
    
    public double getCost() {
           return cost;
    }
    public void setCost(double cost) {
           this.cost = cost;
    }
    public String getId() {
           return id;
    }
    public void setId(String id) {
           this.id = id;
    }
    public int getX() {
           return x;
    }
    public void setX(int x) {
           this.x = x;
    }
    public int getY() {
           return y;
    }
    public void setY(int y) {
           this.y = y;
    }
      
    public String toString(){     
           return new String("Start Node: "+ x +":" + y + ":"+ id+ " Cost:"+ cost);  
    }

	@Override
	public int compareTo(Node other) {
		if( (this.x == other.x) && (this.y == other.y)){
			return 0;
		}
		return -1;
		
	}
	
	@Override
	public boolean equals(Object other){
		if((other != null) &&(other instanceof Node)){			
		   Node nodeValue = (Node) other;
		   return (this.x == nodeValue.x) && (this.y == nodeValue.y);
		}
		
		return false;
    
	}

}


