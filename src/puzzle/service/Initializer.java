/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import puzzle.view.PuzzleView;

/**
 *
 * @author Anjana
 */
public class Initializer {
    
    public List<int []> initializeSearch(){
        int [] initialPuzzle = {
            1,2,0,
            3,4,5,
            6,7,8
        };
        
        Node node = new Node(initialPuzzle);
        Search search = new Search();
        List<Node> solution = search.breadthFirstSearch(node);
        
        List<int []> arr = new ArrayList<>();
        
        if(solution.size()> 0){
            for (int i = solution.size() - 1 ; i >= 0; i--) {
                solution.get(i).printPuzzle();
                arr.add(solution.get(i).puzzle);
                
            }
        }else{
            System.out.println("No Path found");
        }
        
        return arr;
    }    
}
