package tukorea2018180009.ac.kr.example.equipmentcollector.ExpenditionAreaInfo;

import android.graphics.Bitmap;

import java.util.ArrayList;

import tukorea2018180009.ac.kr.example.equipmentcollector.Memory.BitmapPool;
import tukorea2018180009.ac.kr.example.equipmentcollector.Monster.BabyHydraMonster;
import tukorea2018180009.ac.kr.example.equipmentcollector.Monster.BluehideMonster;
import tukorea2018180009.ac.kr.example.equipmentcollector.Monster.GrizzlyeerMonster;
import tukorea2018180009.ac.kr.example.equipmentcollector.Monster.HydraMonster;
import tukorea2018180009.ac.kr.example.equipmentcollector.Monster.IvernMonster;
import tukorea2018180009.ac.kr.example.equipmentcollector.Monster.LiliumSerpensMonster;
import tukorea2018180009.ac.kr.example.equipmentcollector.Monster.MurlocMonster;
import tukorea2018180009.ac.kr.example.equipmentcollector.Monster.ShamanOakMonster;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI.BattleProfile;

public class Forest2_ExpeditionAreaInfo extends ExpeditionAreaInfo {

    // 생성자
    Forest2_ExpeditionAreaInfo() {
        this.areaType = AreaType.Forest2;
    }

    @Override
    public Bitmap getIcon() {
        return BitmapPool.get(R.mipmap.jpg_forest_background);
    }

    @Override
    public String getName() {
        return "Forest2";
    }

    @Override
    public ArrayList<BattleProfile> getEnemies(int wave) {
        ArrayList<BattleProfile> result = new ArrayList<>();

        final float percentage = (float)(Math.random() * 100.0);

        if(percentage <= 20){
            // 파티 생성
            result.add(new BattleProfile(new MurlocMonster(), 0,0,150));   // 1열
            result.add(new BattleProfile(new MurlocMonster(), 0,0,150));   // 2열
            result.add(new BattleProfile(new IvernMonster(), 0,0,150));   // 3열
            result.add(new BattleProfile(new IvernMonster(), 0,0,150));   // 4열
        }
        else if(percentage <= 40){
            // 파티 생성
            result.add(new BattleProfile(new BabyHydraMonster(), 0,0,150));
            result.add(new BattleProfile(new LiliumSerpensMonster(), 0,0,150));
            result.add(new BattleProfile(new ShamanOakMonster(), 0,0,150));
        }
        else if(percentage <= 60){
            // 파티 생성
            result.add(new BattleProfile(new MurlocMonster(), 0,0,150));
            result.add(new BattleProfile(new BluehideMonster(), 0,0,150));
            result.add(new BattleProfile(new IvernMonster(), 0,0,150));
        }
        else if(percentage <= 80){
            // 파티 생성
            result.add(new BattleProfile(new BluehideMonster(), 0,0,150));
            result.add(new BattleProfile(new BluehideMonster(), 0,0,150));
        }
        else if(percentage <= 100){
            // 파티 생성
            result.add(new BattleProfile(new GrizzlyeerMonster(), 0,0,150));
            result.add(new BattleProfile(new ShamanOakMonster(), 0,0,150));
        }

        return result;
    }
}
