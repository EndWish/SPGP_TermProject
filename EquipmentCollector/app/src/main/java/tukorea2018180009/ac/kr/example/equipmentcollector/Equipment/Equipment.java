package tukorea2018180009.ac.kr.example.equipmentcollector.Equipment;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Adventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.Damage;
import tukorea2018180009.ac.kr.example.equipmentcollector.Object;
import tukorea2018180009.ac.kr.example.equipmentcollector.IAbility;
import tukorea2018180009.ac.kr.example.equipmentcollector.IIcon;

public abstract class Equipment extends Object implements IAbility, IIcon {


    protected Adventurer wearer;
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
    public Adventurer getWearer() {
        if(wearer != null && wearer.isDeleted())
            wearer = null;
        return wearer;
    }
    public void setWearer(Adventurer wearer) {
        this.wearer = wearer;
    }

    // 등급 열거형
    public static enum Grade{
        Common, Uncommon, Rare, Epic, Legendary
    }

    // IAbility를 위한 함수들
    @Override
    public void applyStatus(Adventurer adventurer) {}
    @Override
    public void advanceTick(Adventurer adventurer) {}
    @Override
    public float changeTotalDamageMultiple(Float totalDamage, Damage damage) {
        return 1f;
    }

}
