package tukorea2018180009.ac.kr.example.equipmentcollector.Skills;

import java.util.ArrayList;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Adventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.GameObject;
import tukorea2018180009.ac.kr.example.equipmentcollector.IAbility;
import tukorea2018180009.ac.kr.example.equipmentcollector.Scenes.BaseScene;
import tukorea2018180009.ac.kr.example.equipmentcollector.Scenes.BattleScene;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI.BattleProfile;

abstract public class Attack extends GameObject{
    protected Sprite sprite;
    protected BattleScene battleScene;
    protected BattleProfile caster, target;
    protected ArrayList<BattleProfile> relativeAllies, relativeEnemies;
    protected boolean areaOfEffect, allyAttack;

    public Attack(BattleProfile caster, BattleProfile target) {
        if(BaseScene.getTopScene() instanceof BattleScene)
            battleScene = (BattleScene)BaseScene.getTopScene();

        this.caster = caster;
        this.target = target;
        this.allyAttack = caster.isAlly();

        if(battleScene != null) {
            if(allyAttack){
                relativeAllies = battleScene.getMyParty();
                relativeEnemies = battleScene.getEnemyParty();
            }
            else{
                relativeAllies = battleScene.getEnemyParty();
                relativeEnemies = battleScene.getMyParty();
            }
        }
    }

}
