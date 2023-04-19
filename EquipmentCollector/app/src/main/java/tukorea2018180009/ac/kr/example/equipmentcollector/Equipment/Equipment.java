package tukorea2018180009.ac.kr.example.equipmentcollector.Equipment;

import tukorea2018180009.ac.kr.example.equipmentcollector.IAbility;
import tukorea2018180009.ac.kr.example.equipmentcollector.IIcon;
import tukorea2018180009.ac.kr.example.equipmentcollector.Skills.Skill;

public abstract class Equipment  implements IAbility, IIcon {



    protected int upgradeLevel;

    public Equipment() {
        upgradeLevel = 0;
    }

    // 자식 클래스에서 설정할 함수(값)들
    public abstract Equipment.Grade getGrade();
    public abstract String getDesc();
    public abstract String getName();

    // getter, setter
    public int getUpgradeLevel() {
        return upgradeLevel;
    }
    public void setUpgradeLevel(int upgradeLevel) {
        this.upgradeLevel = upgradeLevel;
    }

    // 등급 열거형
    public static enum Grade{
        Common, Uncommon, Rare, Epic, Legendary
    }
}
