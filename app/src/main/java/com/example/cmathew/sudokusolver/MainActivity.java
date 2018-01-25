package com.example.cmathew.sudokusolver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    RecyclerView puzzleDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[][] board = {{5,3,0,7,0,0,0,0,0},
                         {6,0,0,1,9,5,0,0,0},
                         {0,9,8,0,0,0,0,6,0},
                         {8,0,0,0,6,0,0,0,3},
                         {4,0,0,8,0,3,0,0,1},
                         {7,0,0,0,2,0,0,0,6},
                         {0,6,0,0,0,0,2,8,0},
                         {0,0,0,4,1,9,0,0,5},
                         {0,0,0,0,8,0,0,7,9}};

        this.puzzleDisplay = findViewById(R.id.puzzle_display);
        SudokuAdapter sudokuAdapter = new SudokuAdapter(board);
        GridLayoutManager gridMgr = new GridLayoutManager(this, 9);
        puzzleDisplay.setLayoutManager(gridMgr);
        puzzleDisplay.setAdapter(sudokuAdapter);

        /*
        Solver solver = new Solver();
        int[][] solution = solver.solveBoard(board);
        for (int i = 0; i < 9; i++) {
            System.out.println("\n");
            for (int j = 0; j < 9; j++) {
                System.out.print(solution[i][j] + " ");
            }
        }
        */
    }
}
