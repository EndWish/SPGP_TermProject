package tukorea2018180009.ac.kr.example.equipmentcollector.Equipment;

import android.graphics.Bitmap;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Adventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Status;
import tukorea2018180009.ac.kr.example.equipmentcollector.Memory.BitmapPool;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;

public class GauntletEquipment extends Equipment {
    private static final String name = "건틀릿";
    private static final Bitmap icon = BitmapPool.get(R.mipmap.png_equipment_gauntlet);


    
    // 장비의 정보를 리턴해주는 함수
    @Override
    public Grade getGrade() {
        return Grade.Rare;
    }
    @Override
    public String getDesc() {
        return "<벨트> : 레어\n 베기 공격력을 " + (upgradeLevel * 2 + 6) +" 올려주고 " +
                "베기 방어력을 " + (upgradeLevel * 1.5f + 5) +" 올려준다.";
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
        adventurer.getExtraBasicStatus().add(Status.Type.slashPower, (upgradeLevel * 2.f + 6));
        adventurer.getExtraBasicStatus().add(Status.Type.slashDefense, (upgradeLevel * 1.5f + 5));
    }
}
