package pathfinding;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
 
public class Map implements Graph{
                
	Node node;
	Edge edge;
	List<Edge> edges = new ArrayList<Edge>();
	List<Node> nodes = new ArrayList<Node>();
                               
	public Map() {
	                
	}
	
	public Map(Node node, Edge edge){
	    this.node = node;
	    this.edge = edge;             
	}
		
	public void addNode(Node node){
        if(!nodes.contains(node)){
        	nodes.add(node);
        }else{
           System.out.println("node already exists, dont add duplicates");
        }
	}
	
	public Node getNode(int X, int Y){
        for (int i = 0; i<nodes.size(); i++){
            if (nodes.get(i).getX() == X && nodes.get(i).getY()== Y){
              return nodes.get(i);
            }                              
        }
        return null;
	}
	
	public void addEdge(Edge edge){
        if((edge != null) && (!edges.contains(edge) )){
        	edges.add(edge);
        }else{
            System.out.println("edge already exists, dont add duplicates");
        }
	}
		
	public void removeEdge(Edge edge){
	    edges.remove(edge);
	}                
                
	public void addConnection(Node start, Node next){
        if(start != null && next != null){
            double cost = start.getCost() + this.calcManhattanDistance(start, getLastNode());
            Edge edge1 = new Edge(start, next, cost);
            if(edge1 != null){
            	this.addEdge(edge1);
            }
        }
    }
    
    public Node getLastNode(){                  
	    for (Node node: nodes){
            if(node.getId().equalsIgnoreCase(Node.DESTINATION)){
                            return node;                      
            }
	    }
	    return null;
    }
    
    public Node getFirstNode(){                   
		for (Node node: nodes){
            if(node.getId().equalsIgnoreCase(Node.START)){
                      return node;                      
            }
		}
		return null;
    }
 
    public void removeConnection(Node start, Node next){
        Edge edge1 = new Edge(start, next);
        this.removeEdge(edge1);
    }
                
                
    public double calcManhattanDistance(Node current, Node next){
        return Math.abs(current.getX() - next.getX()) + Math.abs(current.getY() - next.getY());
    }
                
    public List<Node> getNodes(){
        return nodes;
    }
    
    public List<Edge> getEdges(){
         return edges;
    }
              
    public Map createMap(String fileName){
        Map map = new Map();
        BufferedReader br = null;
        LineNumberReader lnr = null;
        String currentLine = "";
        int noOfLines = 0;
                    
        try {
        //Add nodes to the Map              
		// determine the number of lines there are in the file. This will be used as the max in the X of the nodes in the Graph/Map
		lnr = new LineNumberReader(new FileReader(fileName));
		lnr.skip(Long.MAX_VALUE);
		noOfLines = lnr.getLineNumber();
		noOfLines++;

		
		br  = new BufferedReader(new FileReader(fileName));
		for (int i = 0; i<noOfLines; i++){
		    if ((currentLine = br.readLine()) != null) {
		
		       String nodeId = "";
		       int temp = 0;
		       double cost = 0.0;

				for (int j= 0; j<currentLine.length(); j++){
	                temp = j+1;
	                nodeId = currentLine.substring(j, temp);
	                
	                switch(nodeId){
	                case Node.START: 
	                                cost = Node.FLATLAND_COST;
	                                break;
	                case Node.FLATLAND: 
	                                cost = Node.FLATLAND_COST ;
	                                break;
	                case Node.DESTINATION: 
	                                cost = Node.FLATLAND_COST ;
	                                break;
	                case Node.FOREST: 
	                                cost = Node.FOREST_COST ;
	                                break;
	                case Node.MOUNTAIN: 
	                                cost = Node.MOUNTAIN_COST ;
	                                break;
	                default:
	                                cost = 0.00;
	                }
	                
                    Node node = new Node(i, j, cost, nodeId);
                    if(node != null){
                    	map.addNode(node);
                    }
                    
				}
		    }
		}
    } catch (FileNotFoundException e) {
                    e.printStackTrace();
    } catch (IOException e) {
    e.printStackTrace();
    } finally {
    try {
        if(lnr!= null)
                        lnr.close();
        if (br != null)
                        br.close();
    } catch (IOException ex) {
        ex.printStackTrace();
    }

	Node nextNode;
	for (int x = 0; x<noOfLines; x++){
        for (int y = x; y<noOfLines; y++){
		    Node startNode = map.getNode(x, y);		
		    if(startNode!= null){
		                    		                    
		    //to the left	
			nextNode = map.getNode(x, y-1);
			if(nextNode != null)
			{
			   map.addConnection(startNode, nextNode);      
			}
	
			//to the right
			nextNode = map.getNode(x, y+1);
			if(nextNode != null)
			{
				map.addConnection(startNode, nextNode);      
			}
			
			//up
			nextNode = map.getNode(x-1, y);
			if(nextNode != null)
			{
				map.addConnection(startNode, nextNode);      
			}
			
			//down
			nextNode = map.getNode(x+1, y);
			if(nextNode != null)
			{
				map.addConnection(startNode, nextNode);      
			}
				
			//top right
			nextNode = map.getNode(x-1, y+1);
			if(nextNode != null)
			{			                
				map.addConnection(startNode, nextNode);      
			}
		
			//bottom right
			nextNode = map.getNode(x+1, y+1);
			if(nextNode != null)
			{			                
			   map.addConnection(startNode, nextNode);      
			}
	
			//top left
			nextNode = map.getNode(x-1, y+1);
            if(nextNode != null)
            {
            	map.addConnection(startNode, nextNode);      
            }
                       
            //bottom left 
            nextNode = map.getNode(x-1, y-1);
            if(nextNode != null)
            {                           
               map.addConnection(startNode, nextNode);      
            }            
		  }
        }
	}               
		
		}
	return map;
	}
    
    /**
     * Gets Neighbours for current Node
     * @param current
     * @return
     */
    public List<Node> getNeighbours(Node current){
    	List<Node> neighbours = new ArrayList<Node>();
    	
    	if(!current.equals(this.getLastNode())){
        	neighbours.addAll(getNeighboursLeft(current));
        	neighbours.addAll(getNeighboursRight(current));
        	neighbours.addAll(getNeighboursTopDown(current));
    	}
    	else{
    		System.out.println(" Testing");
    	}

    	   	
    	return neighbours;
    }

	private List<Node> getNeighboursTopDown(Node current) {
		List<Node> neighbours = new ArrayList<Node>();
    	int add = -1;  
		add = nodes.indexOf(new Node(current.getX() ,current.getY()+1 ));
		if(add != -1){
			neighbours.add(nodes.get(add));
		}
		 add = -1; 
		add = nodes.indexOf(new Node(current.getX() ,current.getY() -1 ));
		if(add != -1){
			neighbours.add(nodes.get(add));
		}
		return neighbours;
	}

	private List<Node> getNeighboursRight(Node current) {
		List<Node> neighbours = new ArrayList<Node>();
    	int add = -1;  
		add = nodes.indexOf(new Node(current.getX() +1,current.getY()+1 ));
		if(add != -1){
			neighbours.add(nodes.get(add));
		}
		 add = -1; 
		add = nodes.indexOf(new Node(current.getX() +1,current.getY()  ));
		if(add != -1){
			neighbours.add(nodes.get(add));
		}
		 add = -1; 
		add = nodes.indexOf(new Node(current.getX() +1 ,current.getY() -1 ));
		if(add != -1){
			neighbours.add(nodes.get(add));
		}
		
		return neighbours;
	}

	private List<Node> getNeighboursLeft(Node current) {
		List<Node> neighbours = new ArrayList<Node>();
    	int add = -1;  
		add = nodes.indexOf(new Node(current.getX() - 1,current.getY()+1 ));
		if(add != -1){
			neighbours.add(nodes.get(add));
		}
		 add = -1; 
		add = nodes.indexOf(new Node(current.getX() - 1,current.getY() ));
		if(add != -1){
			neighbours.add(nodes.get(add));
		}
		 add = -1; 
		add = nodes.indexOf(new Node(current.getX() - 1,current.getY() -1 ));
		if(add != -1){
			neighbours.add(nodes.get(add));
		}
		return neighbours;
	}
}
