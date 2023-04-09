package tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers;

import android.graphics.Bitmap;

public abstract class Adventurer {

    protected Status basicStats, extraBasicStats, coefficientStats, totalStats;

    //protected ArrayList<Skill> skills;
    //protected ArrayList<EuipmentItem> equipments;
    //protected ArrayList<StateChange> stateChanges;

    public abstract String GetName();
    public abstract Bitmap GetIcon();
    public abstract Bitmap GetProfile();

    //public void AdvanceTick();
    //public float GetMaximumGauge();
    //public void UseMaximumGaugeSkill(targets);

    //public void ResetExtraBasicStats();
    //public void ResetCoefficientBasicStats();
    //public void ResetTotalStats();
    //public void UpdateStats();

}
