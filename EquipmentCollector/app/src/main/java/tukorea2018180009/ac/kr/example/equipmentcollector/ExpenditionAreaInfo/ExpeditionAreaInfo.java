package tukorea2018180009.ac.kr.example.equipmentcollector.ExpenditionAreaInfo;

import android.graphics.Bitmap;
import android.hardware.Camera;

import java.util.ArrayList;
import java.util.HashMap;

import tukorea2018180009.ac.kr.example.equipmentcollector.IIcon;
import tukorea2018180009.ac.kr.example.equipmentcollector.Object;

public abstract class ExpeditionAreaInfo extends Object implements IIcon {
    protected AreaType areaType;

    public enum AreaType {
        Forest1,
    }

    private static ArrayList<ExpeditionAreaInfo> expeditionAreaInfos = new ArrayList<>();
    static {
        expeditionAreaInfos.add(new Forest1_ExpeditionAreaInfo());
    }

    // 탐험지역에 대한 정보를 얻어오기
    public static ArrayList<ExpeditionAreaInfo> getExpeditionAreaInfos(){
        return expeditionAreaInfos;
    }
    public static ExpeditionAreaInfo getExpeditionAreaInfo(AreaType areaType){
        return expeditionAreaInfos.get(areaType.ordinal());
    }

    public abstract String getName();

    // getter, setter
    public AreaType getAreaType(){
        return areaType;
    }

}
