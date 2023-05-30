package tukorea2018180009.ac.kr.example.equipmentcollector.Equipment;

import android.graphics.Bitmap;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Adventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Status;
import tukorea2018180009.ac.kr.example.equipmentcollector.Memory.BitmapPool;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;

public class BeltEquipment extends Equipment {
    private static final String name = "벨트";
    private static final Bitmap icon = BitmapPool.get(R.mipmap.png_equipment_belt);


    
    // 장비의 정보를 리턴해주는 함수
    @Override
    public Grade getGrade() {
        return Grade.Common;
    }
    @Override
    public String getDesc() {
        return "<벨트> : 커먼\n 마법/신성/저주 저항력을 " + (upgradeLevel + 3) +" 올려준다.";
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
        adventurer.getExtraBasicStatus().add(Status.Type.magicResistance, (upgradeLevel + 3));
        adventurer.getExtraBasicStatus().add(Status.Type.holyResistance, (upgradeLevel + 3));
        adventurer.getExtraBasicStatus().add(Status.Type.cursedResistance, (upgradeLevel + 3));
    }
}
