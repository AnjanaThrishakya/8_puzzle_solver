/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle.service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Anjana
 */
public class Search {
    public List<Node> breadthFirstSearch(Node root) {
        List<Node> pathToSolution = new ArrayList<>();
        List<Node> openList = new ArrayList<>();
        List<Node> closedList = new ArrayList<>();
        
        openList.add(root);
        boolean goalFound = false;
        
        while (openList.size() > 0 && !goalFound) {
            Node currentNode = openList.get(0);
            closedList.add(currentNode);
            openList.remove(0);
            
            currentNode.expandMove();
            
            for (int i = 0; i < currentNode.children.size(); i++) {
                Node currentChild = currentNode.children.get(i);
                
                if (currentChild.goalTest()) {
                    goalFound = true;
                    System.out.println("Goal Found");
                    tracePath(pathToSolution, currentChild);
                    break;
                }
                
                if (!contains(openList, currentChild) && !contains(closedList, currentChild)) {
                    openList.add(currentChild);
                }
            }
        }
        
        return pathToSolution;
    }
    
    public static boolean contains(List<Node> nodeList, Node node) {
        boolean contains = false;
        
        for (int i = 0; i < nodeList.size(); i++) {
            
            if (nodeList.get(i).samePuzzle(node.puzzle)) {
                contains = true;
            }
            
        }
        
        return contains;
    }
    
    public void tracePath(List<Node> path, Node node) {
        System.out.println("Tracing Path ...");
        Node current = node;
        path.add(node);
        
        while (current.parent != null) {            
            current = current.parent;
            path.add(current);
        }
    }
}
