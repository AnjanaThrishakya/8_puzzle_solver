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
public class Node {

    public List<Node> children = new ArrayList<>();
    public Node parent;
    public int[] puzzle = new int[9];
    public int x = 0;
    public int collumns = 3;
    private PuzzleView view;

    public Node(int[] puzzle) {
        this.view = view;
        setPuzzle(puzzle);
    }

    public void setPuzzle(int[] p) {
        //System.arraycopy(puzzle, 0, this.puzzle, 0, puzzle.length);
        //System.arraycopy(goalPuzzle, 0, this.goalPuzzle, 0, goalPuzzle.length);

        for (int i = 0; i < this.puzzle.length; i++) {
            this.puzzle[i] = p[i];
        }
    }

    public void copyPuzzle(int puzzle[], int copiedPuzzle[]) {
        for (int i = 0; i < puzzle.length; i++) {
            copiedPuzzle[i] = puzzle[i];
        }
        
    }

    public void moveToRight(int[] puzzle, int i) {
        if ((i % this.collumns) < (this.collumns - 1)) {
            int copiedPuzzle[] = new int[9];
            copyPuzzle(puzzle, copiedPuzzle);

            int temp = copiedPuzzle[i + 1];
            copiedPuzzle[i + 1] = puzzle[i];
            copiedPuzzle[i] = temp;

            Node child = new Node(copiedPuzzle);
            children.add(child);
            child.parent = this;

        }
    }

    public void moveToLeft(int[] puzzle, int i) {
        if (i % this.collumns > 0) {
            int copiedPuzzle[] = new int[9];
            copyPuzzle(puzzle, copiedPuzzle);

            int temp = copiedPuzzle[i - 1];
            copiedPuzzle[i - 1] = puzzle[i];
            copiedPuzzle[i] = temp;

            Node child = new Node(copiedPuzzle);
            children.add(child);
            child.parent = this;

        }
    }

    public void moveToDown(int[] puzzle, int i) {
        if ((i + this.collumns) < this.puzzle.length) {
            int copiedPuzzle[] = new int[9];
            copyPuzzle(puzzle, copiedPuzzle);

            int temp = copiedPuzzle[i + 3];
            copiedPuzzle[i + 3] = puzzle[i];
            copiedPuzzle[i] = temp;

            Node child = new Node(copiedPuzzle);
            children.add(child);
            child.parent = this;

        }
    }

    public void moveToUp(int[] puzzle, int i) {
        if ((i - this.collumns) > 0) {
            int copiedPuzzle[] = new int[9];
            copyPuzzle(puzzle, copiedPuzzle);

            int temp = copiedPuzzle[i - 3];
            copiedPuzzle[i - 3] = puzzle[i];
            copiedPuzzle[i] = temp;

            Node child = new Node(copiedPuzzle);
            children.add(child);
            child.parent = this;

        }
    }

    public void printPuzzle() {
        System.out.println("");
        for (int i = 0; i < this.puzzle.length; i++) {

            if (i % this.collumns == 0) {
                System.out.println("");
            }
            System.out.print(this.puzzle[i] + " ");
           // view.setResults(puzzle);

        }
        System.out.println("");

    }

    public boolean samePuzzle(int[] checkingPuzzle) {
        boolean isSame = true;
        for (int i = 0; i < this.puzzle.length; i++) {
            if (this.puzzle[i] != checkingPuzzle[i]) {
                isSame = false;
            }
        }
        return isSame;
    }

    public boolean goalTest() {
        boolean isGoal = true;
        int m = this.puzzle[0];

        for (int i = 1; i < puzzle.length; i++) {
            if (m > this.puzzle[i]) {
                isGoal = false;
            }
            m = this.puzzle[i];
        }
        return isGoal;
    }

    public void expandMove() {
        for (int i = 0; i < this.puzzle.length; i++) {
            if (puzzle[i] == 0) {
                System.out.println("I = " + i + " puzzle " + puzzle[i]);
                this.x = i;
            }
            moveToDown(this.puzzle, x);
            moveToUp(this.puzzle, x);
            moveToRight(this.puzzle, x);
            moveToLeft(this.puzzle, x);

        }
    }
}
