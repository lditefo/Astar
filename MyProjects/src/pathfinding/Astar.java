package pathfinding;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Astar {
	private double totalCost = 0;
	private List<Node> notes;
	private Map map;
	LinkedList<Node> path = new LinkedList<Node>();
	
	public LinkedList<Node> findShortestPath(Map map){
		notes = map.getNodes();	
		this.map = map;
		 Node currentNode  = map.getFirstNode();
		 Node goalNode = map.getLastNode();
		
		 path.add(currentNode);		  
	  	 currentNode.parent = null;
	  	System.out.println(" current: " + currentNode);
	  	
	  	
	      while(!currentNode.equals(goalNode)){
	    	  
	  	      //Remove un-walkable node from your neighbours
	  	      List<Node> neighbours = map.getNeighbours(currentNode);
	  	      List<Node> waterNodes =new ArrayList<Node>();
	  	      for(Node node : neighbours){
	  		      if(node.getId().equals(Node.WATER)){
	  		    	  waterNodes.add(node); 	
	  	    		  continue;
	  	    	  }
	  	      }
	  	      
	  	      neighbours.removeAll(waterNodes);
	  	      neighbours.removeAll(path);
	  	      Node cheapestNode = getLeastCost(currentNode, neighbours, map.getLastNode());
	          cheapestNode.parent = currentNode;
	  	      path.add(cheapestNode);	  	    
	  	      currentNode = cheapestNode;
	  	      System.out.println(" current: " + currentNode);
	  	      
	      }

        //loop is over, return true
        return path;
       
	}
	
	public  void addTotalCost(Node nextNode){		
		totalCost = totalCost +  nextNode.getCost();		
	}
	public  double addToCost(Node current, Node nextNode){		
		return current.getCost() + nextNode.getCost();		
	}
	
    public double calcManhattanDistance(Node start, Node end){
        return Math.abs(start.getX() - end.getX()) + Math.abs(start.getY() - end.getY());
    }
    
    public double calcScorePerTile(Node start, Node end){
    	return totalCost + calcManhattanDistance(start, end);
    	
    }
	

	/**
	 *  get  the neighbor with cheapest cost form currentPosition
	 * @param current
	 * @param neighbors
	 * @return
	 */	
	public Node getLeastCost(Node current, List<Node> neighbors, Node goalNode){
		int shortestXdistance = 0; 
		int shortestYdistance = 0;
		
		List<Node> cheapestNodeList = cheapestNodes(neighbors);
		int tempY = 0;
		int tempX = 0;
		if (cheapestNodeList.size() > 1){
			for (Node cheapNode: cheapestNodeList){
				tempX = goalNode.getX() - cheapNode.getX();
				tempY = goalNode.getY() - cheapNode.getY();
				
				if (tempX >= shortestXdistance){
					shortestXdistance = cheapNode.getX();
				}
				if (tempY >= shortestYdistance){
					shortestYdistance = cheapNode.getY();
					
				}
			}
			return map.getNode(shortestXdistance, shortestYdistance);
		}else{
			return cheapestNodeList.get(0);
		}						
	}
	
	private List<Node> cheapestNodes(List<Node> neighbours){
		double lowestCost = Double.MAX_VALUE;
		List<Node> cheapestNodeList = new ArrayList<Node>();
		
		for (Node temp: neighbours ){
			double leastCost = calcScorePerTile(temp, map.getLastNode());
			if(leastCost <= lowestCost){
				lowestCost = leastCost;
			}
		}
		
		for (Node finalNodes: neighbours){
			double leastCost = calcScorePerTile(finalNodes, map.getLastNode());
			if (leastCost <= lowestCost){
				cheapestNodeList.add(finalNodes);
			}
		}
		
		return cheapestNodeList;
	}

}
