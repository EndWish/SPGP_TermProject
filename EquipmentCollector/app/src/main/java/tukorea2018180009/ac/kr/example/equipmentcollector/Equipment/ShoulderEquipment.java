package tukorea2018180009.ac.kr.example.equipmentcollector.Equipment;

import android.graphics.Bitmap;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Adventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Status;
import tukorea2018180009.ac.kr.example.equipmentcollector.Memory.BitmapPool;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;

public class ShoulderEquipment extends Equipment {
    private static final String name = "숄더";
    private static final Bitmap icon = BitmapPool.get(R.mipmap.png_equipment_shoulder);


    
    // 장비의 정보를 리턴해주는 함수
    @Override
    public Grade getGrade() {
        return Grade.Common;
    }
    @Override
    public String getDesc() {
        return "<방패> : 커먼\n 베기/관통 방어력을 " + (upgradeLevel + 1) +" 올려주고\n" +
                "충격 방어력을 " + (upgradeLevel + 5) +" 올려준다.\n";
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
        adventurer.getExtraBasicStatus().add(Status.Type.slashDefense, (upgradeLevel + 1));
        adventurer.getExtraBasicStatus().add(Status.Type.pierceDefense, (upgradeLevel + 1));
        adventurer.getExtraBasicStatus().add(Status.Type.impactDefense, (upgradeLevel + 5));
    }
}
