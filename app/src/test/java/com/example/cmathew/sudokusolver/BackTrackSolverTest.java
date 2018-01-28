package com.example.cmathew.sudokusolver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class BackTrackSolverTest {
    private int[][] board;

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        int[][] board1 = {{5,3,0,0,7,0,0,0,0}, {6,0,0,1,9,5,0,0,0}, {0,9,8,0,0,0,0,6,0}, {8,0,0,0,6,0,0,0,3}, {4,0,0,8,0,3,0,0,1}, {7,0,0,0,2,0,0,0,6}, {0,6,0,0,0,0,2,8,0}, {0,0,0,4,1,9,0,0,5}, {0,0,0,0,8,0,0,7,9}};
        int[][] board2 = {{7,9,0,0,0,0,3,0,0}, {0,0,0,0,0,6,9,0,0}, {8,0,0,0,3,0,0,7,6}, {0,0,0,0,0,5,0,0,2}, {0,0,5,4,1,8,7,0,0}, {4,0,0,7,0,0,0,0,0}, {6,1,0,0,9,0,0,0,8}, {0,0,2,3,0,0,0,0,0}, {0,0,9,0,0,0,0,5,4}};
        return Arrays.asList(new Object[][] { { board1 }, { board2 }});
    }

    public BackTrackSolverTest(int[][] board) {
        this.board = board;
    }

    @Test
    public void solveBoard() throws Exception {
        SudokuSolver solver = new SudokuSolver();
        boolean solutionFound = solver.solveBoard(board);
        assertTrue(solutionFound);
    }
}