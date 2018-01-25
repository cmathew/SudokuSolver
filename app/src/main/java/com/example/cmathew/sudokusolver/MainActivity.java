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

    private int[][] board = {{5,3,0,7,0,0,0,0,0},
            {6,0,0,1,9,5,0,0,0},
            {0,9,8,0,0,0,0,6,0},
            {8,0,0,0,6,0,0,0,3},
            {4,0,0,8,0,3,0,0,1},
            {7,0,0,0,2,0,0,0,6},
            {0,6,0,0,0,0,2,8,0},
            {0,0,0,4,1,9,0,0,5},
            {0,0,0,0,8,0,0,7,9}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.puzzleDisplay = findViewById(R.id.puzzle_display);
        this.solveButton = findViewById(R.id.solve_button);

        this.sudokuAdapter = new SudokuAdapter(board);
        GridLayoutManager gridMgr = new GridLayoutManager(this, 9);
        puzzleDisplay.setLayoutManager(gridMgr);
        puzzleDisplay.setAdapter(sudokuAdapter);

        solveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Solver solver = new Solver();
                boolean solvable = solver.solveBoard(board);
                solveButton.setText(String.valueOf(solvable));
                //sudokuAdapter.setSquares(solution);
                //sudokuAdapter.notifyDataSetChanged();
            }
        });
    }
}
