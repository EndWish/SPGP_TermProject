package tukorea2018180009.ac.kr.example.equipmentcollector;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import tukorea2018180009.ac.kr.example.equipmentcollector.Scenes.TitleScene;
import tukorea2018180009.ac.kr.example.equipmentcollector.Scenes.VillageScene;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new TitleScene().pushScene();
        //new VillageScene().pushScene();
    }

}