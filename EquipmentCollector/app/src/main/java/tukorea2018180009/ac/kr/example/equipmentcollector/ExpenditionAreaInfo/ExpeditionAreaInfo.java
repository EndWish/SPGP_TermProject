package tukorea2018180009.ac.kr.example.equipmentcollector.ExpenditionAreaInfo;

import android.graphics.Bitmap;
import android.hardware.Camera;

import java.util.ArrayList;
import java.util.HashMap;

import tukorea2018180009.ac.kr.example.equipmentcollector.IIcon;
import tukorea2018180009.ac.kr.example.equipmentcollector.Object;
import tukorea2018180009.ac.kr.example.equipmentcollector.Scenes.BattleScene;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI.BattleProfile;

public abstract class ExpeditionAreaInfo extends Object implements IIcon {
    // 지역 enum
    public enum AreaType {
        Forest1,
    }

    // 전역 변수
    private static ArrayList<ExpeditionAreaInfo> expeditionAreaInfos = new ArrayList<>();
    private static AreaType currentAreaType;
    static {
        expeditionAreaInfos.add(new Forest1_ExpeditionAreaInfo());
    }

    // 자신의 지역
    protected AreaType areaType;

    // 탐험지역에 대한 정보를 얻어오기
    public static ArrayList<ExpeditionAreaInfo> getExpeditionAreaInfos(){
        return expeditionAreaInfos;
    }
    public static ExpeditionAreaInfo getExpeditionAreaInfo(AreaType areaType){
        return expeditionAreaInfos.get(areaType.ordinal());
    }
    public static ExpeditionAreaInfo getCurrentExpeditionAreaInfo(){
        return expeditionAreaInfos.get(currentAreaType.ordinal());
    }
    public static void setCurrentAreaType(AreaType areaType){
        currentAreaType = areaType;
    }

    public abstract ArrayList<BattleProfile> getEnemies(int wave);

    public abstract String getName();

    // getter, setter
    public AreaType getAreaType(){
        return areaType;
    }

}
