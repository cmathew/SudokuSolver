package com.example.cmathew.sudokusolver;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SudokuGridAdapter extends RecyclerView.Adapter<SudokuGridAdapter.ViewHolder> {
    private int[][] squares;

    public SudokuGridAdapter(int[][] squares) {
        this.squares = squares;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.sudoku_square, parent, false);

        return new SudokuGridAdapter.ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int row = position / 9;
        int col = position % 9;
        int val = squares[row][col];
        holder.squareText.setText(String.valueOf(val));
    }

    @Override
    public int getItemCount() {
        return 81;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView squareText;

        public ViewHolder(View view) {
            super(view);

            this.squareText = view.findViewById(R.id.square_text);
        }
    }
}
