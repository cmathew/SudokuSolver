package com.example.cmathew.sudokusolver;

public class BacktrackSolver {
    private static int BOARD_SIZE = 9;
    private static int BOX_SIZE = 3;
    private static int[] PossibleValues = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    private boolean isLegal(int[][] board, int row, int column, int proposedValue) {
        return isLegalInRow(board, row, proposedValue) &&
                isLegalInColumn(board, column, proposedValue) &&
                isLegalInBox(board, row, column, proposedValue);
    }

    private boolean isLegalInRow(int[][] board, int row, int proposedValue) {
        for (int column = 0; column < BOARD_SIZE; column++) {
            if (board[row][column] == proposedValue) {
                return false;
            }
        }

        return true;
    }

    private boolean isLegalInColumn(int[][] board, int column, int proposedValue) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            if (board[row][column] == proposedValue) {
                return false;
            }
        }

        return true;
    }

    private boolean isLegalInBox(int[][] board, int row, int column, int proposedValue) {
        int boxRowOffset = (row / BOX_SIZE) * BOX_SIZE;
        int boxColumnOffset = (column / BOX_SIZE) * BOX_SIZE;

        for (int i = 0; i < BOX_SIZE; i++) {
            for (int j = 0; j < BOX_SIZE; j++) {
                if (board[boxRowOffset + i][boxColumnOffset + j] == proposedValue) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean solveBoard(int[][] board) {
        return solveCell(board, 0, 0);
    }

    // Each call attempts to populate cell
    public boolean solveCell(int[][] board, int targetRow, int targetColumn) {
        if (targetRow > 8) {
            return true;
        }

        // Determine next cell in sequence
        int nextTargetColumn = targetColumn + 1;
        int nextTargetRow = (targetRow + (nextTargetColumn / BOARD_SIZE));
        nextTargetColumn = nextTargetColumn % BOARD_SIZE;

        // This call's success if a function of one other call
        int value = board[targetRow][targetColumn];
        if (value != 0) {
            return solveCell(board, nextTargetRow, nextTargetColumn);
        }

        // This call's success if a function of many calls
        for (int possibleValue : PossibleValues) {
            if (!isLegal(board, targetRow, targetColumn, possibleValue)) {
                continue;
            }

            board[targetRow][targetColumn] = possibleValue;
            if (solveCell(board, nextTargetRow, nextTargetColumn)) {
                return true;
            }
        }

        // This call failed
        board[targetRow][targetColumn] = 0;
        return false;
    }
}

