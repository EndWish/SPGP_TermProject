package tukorea2018180009.ac.kr.example.equipmentcollector.StatusEffect;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Adventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.Damage;
import tukorea2018180009.ac.kr.example.equipmentcollector.IAbility;
import tukorea2018180009.ac.kr.example.equipmentcollector.Object;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI.BattleProfile;

public class StatusEffect extends Object implements IAbility {
    protected BattleProfile target;
    protected boolean isBuff;

    StatusEffect(BattleProfile target){
        this.target = target;
    }

    @Override
    public void applyStatus(Adventurer adventurer) {}
    @Override
    public void advanceTick(Adventurer adventurer) {}
    @Override
    public float changeTotalDamageMultiple(Float totalDamage, Damage damage) {
        return 1f;
    }
}
