package tukorea2018180009.ac.kr.example.equipmentcollector.ExpenditionAreaInfo;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Random;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.ArthurAdventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.Memory.BitmapPool;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI.BattleProfile;

public class Forest1_ExpeditionAreaInfo extends ExpeditionAreaInfo {

    // 생성자
    Forest1_ExpeditionAreaInfo() {
        this.areaType = AreaType.Forest1;
    }

    @Override
    public Bitmap getIcon() {
        return BitmapPool.get(R.mipmap.jpg_forest_background);
    }

    @Override
    public String getName() {
        return "Forest1";
    }

    @Override
    public ArrayList<BattleProfile> getEnemies(int wave) {
        ArrayList<BattleProfile> result = new ArrayList<>();

        final float percentage = (float)(Math.random() * 100.0);
        if(percentage <= 30){
            // [수정]A파티 생성
            result.add(new BattleProfile(new ArthurAdventurer(), 0,0,0));   // 1열
            result.add(new BattleProfile(new ArthurAdventurer(), 0,0,0));   // 2열
        }
        else{
            // [수정]B파티 생성
            result.add(new BattleProfile(new ArthurAdventurer(), 0,0,0));   // 1열
        }
        return result;
    }
}
