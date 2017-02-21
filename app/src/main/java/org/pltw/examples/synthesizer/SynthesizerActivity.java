package org.pltw.examples.synthesizer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;

public class SynthesizerActivity extends AppCompatActivity {
    private static final String TAG =
SynthesizerActivity.class.getName();
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private TextView message;
    private char turn = 'X';
    private char[][] board = new char[3][3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synthesizer);

        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        button5 = (Button)findViewById(R.id.button5);
        button6 = (Button)findViewById(R.id.button6);
        button7 = (Button)findViewById(R.id.button7);
        button8 = (Button)findViewById(R.id.button8);
        button9 = (Button)findViewById(R.id.button9);
        message = (TextView)findViewById(R.id.message);

        reset();
    }
    
    protected boolean updateBoard() {
        boolean win = false;

        button1.setText(Character.toString(board[0][0]));
        button2.setText(Character.toString(board[0][1]));
        button3.setText(Character.toString(board[0][2]));
        button4.setText(Character.toString(board[1][0]));
        button5.setText(Character.toString(board[1][1]));
        button6.setText(Character.toString(board[1][2]));
        button7.setText(Character.toString(board[2][0]));
        button8.setText(Character.toString(board[2][1]));
        button9.setText(Character.toString(board[2][2]));

        for (int i = 3; i-- != 0;) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != '?')
                win = true;
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != '?')
                win = true;
        }
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '?')
            win = true;
        if (board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[2][0] != '?')
            win = true;
        if (win) {
            message.setText("Player " + turn + " wins!");
            return true;
        }
        return false;
    }
    public void onReset(View v) {
        reset();
    }

    protected void reset() {
        showTurn();
        Arrays.fill(board[0], '?');
        Arrays.fill(board[1], '?');
        Arrays.fill(board[2], '?');
        updateBoard();
    }
    public void onPlayerAction(View v) {
        int row, column;
        Button button = (Button)v;

        switch (button.getId()) {
            case R.id.button1:
                row = 0;
                column = 0;
                break;
            case R.id.button2:
                row = 0;
                column = 1;
                break;
            case R.id.button3:
                row = 0;
                column = 2;
                break;
            case R.id.button4:
                row = 1;
                column = 0;
                break;
            case R.id.button5:
                row = 1;
                column = 1;
                break;
            case R.id.button6:
                row = 1;
                column = 2;
                break;
            case R.id.button7:
                row = 2;
                column = 0;
                break;
            case R.id.button8:
                row = 2;
                column = 1;
                break;
            default:
                row = 2;
                column = 2;
                break;
        }
        if (board[row][column] == '?')
            board[row][column] = turn;
        if (!updateBoard())
            changeTurn();
    }

    protected void changeTurn() {
        switch (turn) {
            case 'X':
                turn = 'O';
                break;
            case 'O':
                turn = 'X';
                break;
        }
        showTurn();
    }

    protected void showTurn() {
        message.setText("It is player " + turn + "\'s turn");
    }
}
