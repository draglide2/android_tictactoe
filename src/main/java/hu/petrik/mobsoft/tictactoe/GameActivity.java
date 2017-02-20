package hu.petrik.mobsoft.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import hu.petrik.mobsoft.tictactoe.model.TicTacToeModel;
import hu.petrik.mobsoft.tictactoe.view.TicTacToeView;

public class GameActivity extends AppCompatActivity {
    View gameArea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameArea = findViewById(R.id.ticView);
        gameArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TicTacToeModel.getInstance().checkWinState() == 1) {
                    Toast.makeText(GameActivity.this, R.string.circleWon, Toast.LENGTH_LONG).show();
                    Intent i = new Intent(GameActivity.this, MainMenuActivity.class);
                    startActivity(i);
                } else if (TicTacToeModel.getInstance().checkWinState() == 2) {
                    Toast.makeText(GameActivity.this, R.string.crossWon, Toast.LENGTH_LONG).show();
                    Intent i = new Intent(GameActivity.this, MainMenuActivity.class);
                    startActivity(i);
                } else if (TicTacToeModel.getInstance().checkWinState() == 0) {
                    Toast.makeText(GameActivity.this, R.string.tie, Toast.LENGTH_LONG).show();
                    Intent i = new Intent(GameActivity.this, MainMenuActivity.class);
                    startActivity(i);
                }
            }
        });
    }
}
