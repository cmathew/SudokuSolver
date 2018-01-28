package com.example.cmathew.sudokusolver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private RecyclerView puzzleDisplay;
    private Button solveButton;

    private SudokuAdapter sudokuAdapter;

    private int[][] emptyBoard = {{0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0}};

    private int[][] board1 = {{5,3,0,0,7,0,0,0,0},
            {6,0,0,1,9,5,0,0,0},
            {0,9,8,0,0,0,0,6,0},
            {8,0,0,0,6,0,0,0,3},
            {4,0,0,8,0,3,0,0,1},
            {7,0,0,0,2,0,0,0,6},
            {0,6,0,0,0,0,2,8,0},
            {0,0,0,4,1,9,0,0,5},
            {0,0,0,0,8,0,0,7,9}};

    private int[][] board2 = {{7,9,0,0,0,0,3,0,0},
            {0,0,0,0,0,6,9,0,0},
            {8,0,0,0,3,0,0,7,6},
            {0,0,0,0,0,5,0,0,2},
            {0,0,5,4,1,8,7,0,0},
            {4,0,0,7,0,0,0,0,0},
            {6,1,0,0,9,0,0,0,8},
            {0,0,2,3,0,0,0,0,0},
            {0,0,9,0,0,0,0,5,4}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.puzzleDisplay = findViewById(R.id.puzzle_display);
        this.solveButton = findViewById(R.id.solve_button);

        this.sudokuAdapter = new SudokuAdapter(board1);
        GridLayoutManager gridMgr = new GridLayoutManager(this, 9);
        puzzleDisplay.setLayoutManager(gridMgr);
        puzzleDisplay.setAdapter(sudokuAdapter);

        solveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (solveWithBacktrack(board1)) {
                    sudokuAdapter.notifyDataSetChanged();
                } else {
                    Log.e("SudoSolve", "No Solution!");
                }
            }
        });
    }

    private boolean solveWithBacktrack(int[][] board) {
        BacktrackSolver solver = new BacktrackSolver();
        return solver.solveBoard(board);
    }
}
