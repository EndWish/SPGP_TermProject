package tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers;

import android.graphics.Bitmap;

import tukorea2018180009.ac.kr.example.equipmentcollector.IIcon;

public abstract class Adventurer implements IIcon {

    protected Status basicStatus, extraBasicStatus, coefficientStatus, totalStatus;

    //protected ArrayList<Skill> skills;
    //protected ArrayList<EuipmentItem> equipments;
    //protected ArrayList<StateChange> stateChanges;

    public abstract String getName();
    public abstract Bitmap getIcon();
    public abstract Bitmap getProfile();

    public Adventurer() {
        basicStatus = new Status();
        extraBasicStatus = new Status();
        coefficientStatus = new Status();
        totalStatus = new Status();

        initBasicStatus();
    }

    protected abstract void initBasicStatus();

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
