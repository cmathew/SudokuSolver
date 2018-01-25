package com.example.cmathew.sudokusolver;

import java.io.*;
import java.util.*;

public class Solver {
    private boolean isBoardSolved(int[][] board) {
        boolean allSolved = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    allSolved = false;
                }
            }
        }

        return allSolved;
    }

    public int[][] solveBoard(int[][] board) {
        // Base condition: return if possibilites arrays are empty
        boolean allSolved = isBoardSolved(board);
        if (allSolved) {
            return board;
        }


        ArrayList<ArrayList<HashSet<Integer>>> possibles = getPossibilities(board);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                HashSet<Integer> cellPossibles = possibles.get(i).get(j);
                for (Integer answer : cellPossibles) {
                    board[i][j] = answer;
                    solveBoard(board);
                    //boolean resultSolved = isBoardSolved(result);
                }
            }
        }

        // if no change in possibilites, then this board is unsolvable?


        return board;
    }

    private ArrayList<ArrayList<HashSet<Integer>>> getPossibilities(int[][] board) {
        // Populate initial possbilites for each cell
        ArrayList<ArrayList<HashSet<Integer>>> possibles = new ArrayList<>();

        //= new HashSet<Integer>[9][9];
        for (int i = 0; i < 9; i++) {
            possibles.add(new ArrayList<HashSet<Integer>>());

            for (int j = 0; j < 9; j++) {
                possibles.get(i).add(new HashSet<Integer>());

                if (board[i][j] == 0) {
                    possibles.get(i).set(j, new HashSet<Integer>());

                } else {
                    HashSet<Integer> allPossible = new HashSet<>();
                    allPossible.add(1);
                    allPossible.add(2);
                    allPossible.add(3);
                    allPossible.add(4);
                    allPossible.add(5);
                    allPossible.add(6);
                    allPossible.add(7);
                    allPossible.add(8);
                    allPossible.add(9);

                    possibles.get(i).set(j, allPossible);
                }
            }
        }


        // for each row
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int value = board[i][j];
                if (value != 0) {
                    possibles.get(i).get(j).remove(value);
                }
            }
        }


        // for each column
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int value = board[j][i];
                if (value != 0) {
                    possibles.get(j).get(i).remove(value);
                }
            }
        }


        // Game board has 3x3 "mini-boards"
        // Index by real index / 3
        //ArrayList<ArrayList<HashSet<Integer>>> miniBoards = new ArrayList<ArrayList<HashSet<Integer>>>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int value = board[i][j];
                int miniGameY = i / 3;
                int miniGameX = j / 3;

                if (value == 0) {
                    continue;
                }


                for (int mi = miniGameY * 3; mi < (miniGameY + 1) * 3; mi++) {
                    for (int mj = miniGameX * 3; mj < (miniGameX + 1) * 3; mj++) {
                        possibles.get(mi).get(mj).remove(value);
                    }
                }
            }
        }

        return possibles;
    }
}


