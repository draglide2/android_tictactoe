package hu.petrik.mobsoft.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import hu.petrik.mobsoft.tictactoe.model.TicTacToeModel;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button btnHighScore = (Button) findViewById(R.id.btnScore);
        btnHighScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainMenuActivity.this, R.string.highscore, Toast.LENGTH_LONG).show();
            }
        });

        Button btnStartGame = (Button) findViewById(R.id.btnStart);
        btnStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TicTacToeModel.getInstance().resetModel();
                Intent i = new Intent(MainMenuActivity.this, GameActivity.class);
                startActivity(i);
            }
        });

        Button btnAbout = (Button) findViewById(R.id.btnAbout);
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(MainMenuActivity.this, AboutActivity.class);
                startActivity(j);
            }
        });
    }
}
