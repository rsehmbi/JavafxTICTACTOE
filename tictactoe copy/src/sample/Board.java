package sample;

import java.util.HashMap;

public class Board {
    private String board[][] = new String[4][4];
    boolean playerXTurn = true;
    private int Count_Turns = 0;

    Board() {
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
                this.board[i][j] = "";
            }
    }

    public void setCount_Turns(int count_Turns) {
        Count_Turns = count_Turns;
    }

    public int getCount_Turns() {
        return Count_Turns;
    }

    public String[][] getBoard() {
        return board;
    }

    public boolean checkIfWon() {
        for (int i = 0; i < 4; i++) {
            if ((this.board[0][i] == this.board[1][i]) &&
                    (this.board[0][i] == this.board[2][i]) &&
                    (this.board[0][i] == this.board[3][i]) && (this.board[0][i] != ""))
                return true;

        }

        for (int i = 0; i < 4; i++) {
            if ((this.board[i][0] == this.board[i][1])
                    && (this.board[i][0] == this.board[i][2])
                    && (this.board[i][0] == this.board[i][3]) && (this.board[i][0] != ""))
                return true;
        }


        if ((this.board[0][0] == this.board[1][1])
                && (this.board[0][0] == this.board[2][2])
                && (this.board[0][0] == this.board[3][3]) && (this.board[0][0] != "")) {
            return true;
        }


        if ((this.board[3][0] == this.board[2][1])
                && (this.board[3][0] == this.board[1][2])
                && (this.board[3][0] == this.board[0][3]) && (this.board[3][0] != "")) {
            return true;
        }
        return false;
    }

    public void resetBoard()
    {
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
               this.board[i][j] = "";
            }
        this.playerXTurn = true;
        this.Count_Turns = 0;
    }
}
