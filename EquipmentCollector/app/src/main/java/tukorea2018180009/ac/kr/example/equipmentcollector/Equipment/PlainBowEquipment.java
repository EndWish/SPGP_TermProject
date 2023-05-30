package tukorea2018180009.ac.kr.example.equipmentcollector.Equipment;

import android.graphics.Bitmap;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Adventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Status;
import tukorea2018180009.ac.kr.example.equipmentcollector.Memory.BitmapPool;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;

public class PlainBowEquipment extends Equipment {
    private static final String name = "무난한 활";
    private static final Bitmap icon = BitmapPool.get(R.mipmap.png_equipment_plain_bow);


    
    // 장비의 정보를 리턴해주는 함수
    @Override
    public Grade getGrade() {
        return Grade.Common;
    }
    @Override
    public String getDesc() {
        return "<무난한 활> : 커먼\n 관통 공격력을 " + (upgradeLevel + 3) +" 올려준다.";
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public Bitmap getIcon() {
        return icon;
    }
    @Override
    public int getUpgradeCost() {
        return 100;
    }

    // IAbility를 위한 함수들
    @Override
    public void applyStatus(Adventurer adventurer) {
        adventurer.getExtraBasicStatus().add(Status.Type.piercePower, (upgradeLevel + 3));
    }
}
