package pathfinding;

public interface Graph {
    
    public void addConnection(Node start, Node next);
    public void removeConnection(Node start, Node next);
    public void addEdge(Edge edge);
    public void removeEdge(Edge edge);
    public void addNode(Node node);
    public Node getNode(int X, int Y);
    

}

