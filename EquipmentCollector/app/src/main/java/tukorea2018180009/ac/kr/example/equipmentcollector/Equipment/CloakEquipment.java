package tukorea2018180009.ac.kr.example.equipmentcollector.Equipment;

import android.graphics.Bitmap;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Adventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Status;
import tukorea2018180009.ac.kr.example.equipmentcollector.Memory.BitmapPool;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;

public class CloakEquipment extends Equipment {
    private static final String name = "망토";
    private static final Bitmap icon = BitmapPool.get(R.mipmap.png_equipment_cloak);


    
    // 장비의 정보를 리턴해주는 함수
    @Override
    public Grade getGrade() {
        return Grade.Common;
    }
    @Override
    public String getDesc() {
        return "<망토> : 커먼\n 마법 저항력과 베기 방어력을 " + (upgradeLevel + 4) +" 올려준다.";
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
        adventurer.getExtraBasicStatus().add(Status.Type.magicResistance, (upgradeLevel + 4));
        adventurer.getExtraBasicStatus().add(Status.Type.slashDefense, (upgradeLevel + 4));
    }
}
