package pathfinding.test;

import pathfinding.Astar;
import pathfinding.Map;

public class TestAStar {
	final static String FILE_NAME = "C:\\multichoice\\input.txt";
	
    public static void main(String[] args) {
           Map map = new Map();
           map = map.createMap(FILE_NAME);   
           
           Astar path = new Astar(); 
           path.findShortestPath(map);
           
    }

}