package hu.petrik.mobsoft.tictactoe.model;

import android.widget.Toast;

/**
 * Created by Wulfrith on 20/02/2017.
 */

public class TicTacToeModel {
    private static TicTacToeModel instance = null;

    private TicTacToeModel() {
    }

    public static TicTacToeModel getInstance() {
        if (instance == null) {
            instance = new TicTacToeModel();
        }
        return instance;
    }

    public static final short EMPTY = 0;
    public static final short CIRCLE = 1;
    public static final short CROSS = 2;

    private short[][] model = {
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY}
    };

    private short nextPlayer = CIRCLE;

    public void resetModel() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                model[i][j] = EMPTY;
            }
        }
    }

    public short getFieldContent(int x, int y) {
        return this.model[x][y];
    }

    public void setFieldContent(int x, int y, short content) {
        this.model[x][y] = content;
    }

    public short getNextPlayer() {
        return this.nextPlayer;
    }

    public void changeNextPlayer() {
        if(this.nextPlayer == CIRCLE) {
            this.nextPlayer = CROSS;
        } else if (this.nextPlayer == CROSS) {
            this.nextPlayer = CIRCLE;
        }
    }

    public short checkWinState() {
        //vízszintes check
        for(int i = 0; i < 3; i++) {
            if(model[i][0] != EMPTY && model[i][0] == model[i][1] && model[i][0] == model[i][2]) {
                return getFieldContent(i, 0);
            }
        }
        //függőleges check
        for(int i = 0; i < 3; i++) {
            if(model[0][i] != EMPTY && model[0][i] == model[1][i] && model[0][i] == model[2][i] ) {
                return getFieldContent(0, i);
            }
        }
        // \ átló
        if(model[0][0] != EMPTY && model[0][0] == model[1][1] && model[0][0] == model[2][2]) {
            return getFieldContent(0, 0);
        }
        // / átló
        if(model[0][2] != EMPTY && model[0][2] == model[1][1] && model[0][2] == model[2][0]) {
            return getFieldContent(0, 2);
        }
        //döntetlen
        boolean noEmpty = true;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(getFieldContent(i, j) == EMPTY) {
                    noEmpty = false;
                }
            }
        }
        if(noEmpty) {
            return 0;
        }
        return -1;
    }
}
