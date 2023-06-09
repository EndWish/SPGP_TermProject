package tukorea2018180009.ac.kr.example.equipmentcollector;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Adventurer;

public interface IAbility {
    void applyStatus(Adventurer adventurer);
    void advanceTick(Adventurer adventurer);
    float changeTotalDamageMultiple(Float totalDamage, Damage damage);
}
