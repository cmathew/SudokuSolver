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

    public boolean solveBoard(int[][] board) {
        // Base condition: return if possibilites arrays are empty
        boolean allSolved = isBoardSolved(board);
        if (allSolved) {
            return true;
        }

        ArrayList<ArrayList<HashSet<Integer>>> possibles = getPossibilities(board);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                HashSet<Integer> cellPossibles = possibles.get(i).get(j);
                for (Integer answer : cellPossibles) {
                    board[i][j] = answer;
                    if (solveBoard(board)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private ArrayList<ArrayList<HashSet<Integer>>> getPossibilities(int[][] board) {
        // Populate initial possibilities for each cell
        ArrayList<ArrayList<HashSet<Integer>>> possiblesMatrix = new ArrayList<>();
        for (int row = 0; row < 9; row++) {
            possiblesMatrix.add(new ArrayList<HashSet<Integer>>());
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    HashSet<Integer> allPossible = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
                    possiblesMatrix.get(row).add(allPossible);
                } else {
                    possiblesMatrix.get(row).add(new HashSet<Integer>());
                }
            }
        }

        // Update all aligned rows and columns
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int value = board[row][col];
                if (value == 0) {
                    continue;
                }

                // for each row
                for (ArrayList<HashSet<Integer>> possiblesRow : possiblesMatrix) {
                    possiblesRow.get(col).remove(value);
                }

                // for each column
                for (HashSet<Integer> possiblesCell : possiblesMatrix.get(row)) {
                    possiblesCell.remove(value);
                }

                // for each 3x3 sub-board neighbor
                int miniGameY = row / 3;
                int miniGameX = col / 3;
                for (int mi = miniGameY * 3; mi < (miniGameY + 1) * 3; mi++) {
                    for (int mj = miniGameX * 3; mj < (miniGameX + 1) * 3; mj++) {
                        possiblesMatrix.get(mi).get(mj).remove(value);
                    }
                }
            }
        }

        return possiblesMatrix;
    }
}


