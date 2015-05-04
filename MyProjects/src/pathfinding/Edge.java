package pathfinding;

public class Edge {
	 
    private Node start;
    private Node next;
    private double cost;
 
	 public Edge(Node start,Node next, double cost){
	    this.start = start;
	    this.next = next;
	    this.cost = cost;
	 }
 
	 public Edge(Node start,Node next){
	    this.start = start;
	    this.next = next;
	 }

    public Node getStart() {
           return start;
    }

    public void setStart(Node start) {
           this.start = start;
    }

    public Node getNext() {
           return next;
    }

    public void setNext(Node next) {
           this.next = next;
    }

    public double getCost() {
           return cost;
    }

    public void setCost(double cost) {
           this.cost = cost;
    }
 
    public String toString(){
           
           return new String("Start Node: "+ start.getX()+":"+start.getY()+"; " + "Next Node: "+ next.getX()+":"+next.getY()+"; " + "Cost: "+ cost);
           
    }
  
}
