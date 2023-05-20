package tukorea2018180009.ac.kr.example.equipmentcollector.Skills;

import java.util.ArrayList;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Adventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.GameObject;
import tukorea2018180009.ac.kr.example.equipmentcollector.IAbility;
import tukorea2018180009.ac.kr.example.equipmentcollector.Metrics;
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
    public Attack(BattleProfile caster, BattleProfile target, float x, float y){
        this(caster, target);
        this.x = x;
        this.y = y;
    }

    // gameObject가 있는 방향으로 이동하며 지나치치 않는다.
    protected void moveTo(GameObject gameObject, float moveDistance){
        // 거리를 구한다.
        float distance = Metrics.distance(this, gameObject);

        // 위치가 같으면 바로 리턴한다
        if(distance == 0)
            return;

        // 이동거리가 더 길어서 지나칠 것 같은경우 타겟의 위치로 바로 이동한다.
        if(distance <= moveDistance){
            x = gameObject.getX();
            y = gameObject.getY();
            return;
        }

        // x축과 y축으로 이동할 크기를 구한다.
        float dx = (gameObject.getX() - x) / distance * moveDistance;
        float dy = (gameObject.getY() - y) / distance * moveDistance;
        x += dx;
        y += dy;
    }
    protected boolean isSamePosition(GameObject gameObject){
        return x == gameObject.getX() && y == gameObject.getY();
    }

}
