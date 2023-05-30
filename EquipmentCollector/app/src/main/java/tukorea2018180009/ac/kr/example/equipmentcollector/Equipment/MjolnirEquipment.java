package tukorea2018180009.ac.kr.example.equipmentcollector.Equipment;

import android.graphics.Bitmap;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Adventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Status;
import tukorea2018180009.ac.kr.example.equipmentcollector.Memory.BitmapPool;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;

public class MjolnirEquipment extends Equipment {
    private static final String name = "묠니르";
    private static final Bitmap icon = BitmapPool.get(R.mipmap.png_equipment_mjolnir);


    
    // 장비의 정보를 리턴해주는 함수
    @Override
    public Grade getGrade() {
        return Grade.Rare;
    }
    @Override
    public String getDesc() {
        return "<묠니르> : 레어\n 충격 공격력을 " + (upgradeLevel * 0.05f + 0.2f) * 100f +"% 올려준다";
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
        adventurer.getCoefficientStatus().add(Status.Type.impactPower, (upgradeLevel * 0.05f + 0.2f));
    }
}
