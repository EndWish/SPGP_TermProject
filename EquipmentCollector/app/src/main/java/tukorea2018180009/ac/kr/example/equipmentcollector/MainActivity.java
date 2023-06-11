package tukorea2018180009.ac.kr.example.equipmentcollector;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import tukorea2018180009.ac.kr.example.equipmentcollector.Scenes.TitleScene;
import tukorea2018180009.ac.kr.example.equipmentcollector.Scenes.VillageScene;

public class MainActivity extends AppCompatActivity {

    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        gameView.setFullScreen();
        setContentView(gameView);

        new TitleScene().pushScene();
        //new VillageScene().pushScene();
    }

    @Override
    protected void onPause() {
        gameView.pauseGame();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resumeGame();
    }

}