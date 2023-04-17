package tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers;

import android.graphics.Bitmap;

import java.util.ArrayList;

import tukorea2018180009.ac.kr.example.equipmentcollector.IIcon;
import tukorea2018180009.ac.kr.example.equipmentcollector.Skills.Skill;

public abstract class Adventurer implements IIcon {

    protected Status basicStatus = new Status();
    protected Status extraBasicStatus = new Status();
    protected Status coefficientStatus = new Status();
    protected Status totalStatus = new Status();

    protected ArrayList<Skill> skills = new ArrayList<>();
    //protected ArrayList<EuipmentItem> equipments;
    //protected ArrayList<StateChange> stateChanges;

    public abstract String getName();
    public abstract Bitmap getIcon();
    public abstract Bitmap getProfile();

    public Adventurer() {
        initBasicStatus();
    }

    protected abstract void initBasicStatus();
    protected abstract void initSkills();

    public void calculateTotalStatus(){
        totalStatus.set(0);
        totalStatus.add(basicStatus);
        totalStatus.add(extraBasicStatus);
        totalStatus.add(coefficientStatus);
    }

    //public void AdvanceTick();
    //public float GetMaximumGauge();
    //public void UseMaximumGaugeSkill(targets);

    //public void ResetExtraBasicStats();
    //public void ResetCoefficientBasicStats();
    //public void ResetTotalStats();
    //public void UpdateStats();

    // getter setter
    public Status getBasicStatus() {
        return basicStatus;
    }
    public Status getExtraBasicStatus() {
        return extraBasicStatus;
    }
    public Status getCoefficientStatus() {
        return coefficientStatus;
    }
    public Status getTotalStatus() {
        return totalStatus;
    }
}
