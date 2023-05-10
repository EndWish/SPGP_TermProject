package tukorea2018180009.ac.kr.example.equipmentcollector.Skills;

import java.util.ArrayList;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Adventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.GameObject;
import tukorea2018180009.ac.kr.example.equipmentcollector.IAbility;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI.BattleProfile;

abstract public class Attack extends GameObject{
    protected Sprite sprite;
    protected BattleProfile caster, target;
    protected boolean areaOfEffect, allyAttack;

    public Attack(BattleProfile caster, BattleProfile target) {
        this.caster = caster;
        this.target = target;
        this.allyAttack = caster.isAlly();
    }

}
