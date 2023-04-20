package tukorea2018180009.ac.kr.example.equipmentcollector.Equipment;

import tukorea2018180009.ac.kr.example.equipmentcollector.Object;
import tukorea2018180009.ac.kr.example.equipmentcollector.IAbility;
import tukorea2018180009.ac.kr.example.equipmentcollector.IIcon;

public abstract class Equipment extends Object implements IAbility, IIcon {



    protected int upgradeLevel;

    public Equipment() {
        super();
        upgradeLevel = 0;
    }

    // 자식 클래스에서 설정할 함수(값)들
    public abstract Equipment.Grade getGrade();
    public abstract String getDesc();
    public abstract String getName();
    public abstract int getUpgradeCost();

    // getter, setter
    public int getUpgradeLevel() {
        return upgradeLevel;
    }
    public void setUpgradeLevel(int upgradeLevel) {
        this.upgradeLevel = upgradeLevel;
    }
    public void addUpgradeLevel(int add){
        upgradeLevel += add;
    }

    // 등급 열거형
    public static enum Grade{
        Common, Uncommon, Rare, Epic, Legendary
    }
}
