package tukorea2018180009.ac.kr.example.equipmentcollector.StatusEffect;

import android.util.Log;

import java.util.ArrayList;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Adventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.Damage;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI.BattleProfile;

public class PoisonMistReinforceStatusEffect extends StatusEffect{
    protected int lifeTick;
    protected Sprite effectSprite;

    public PoisonMistReinforceStatusEffect(BattleProfile target) {
        super(target);
        lifeTick = 100;

        effectSprite = new Sprite(new Sprite.Builder(R.mipmap.png_effect_skill_poison_mist_reinforce2, 0,0,300,300)
                .setAnimation(5, 11, 1.1f, true)
                .setPivot(Sprite.PivotType.center));
        target.addChild(effectSprite);
    }

    @Override
    public void setDelete() {
        super.setDelete();
        effectSprite.setDelete();
        effectSprite = null;
    }

    @Override
    public float changeTotalDamageMultiple(Float totalDamage, Damage damage) {
        // 이 버프를 가진 사람이 공격자일 경우
        if(damage.getCaster() == target){
            // 상대의 버프/디버프를 가져온다.
            ArrayList<StatusEffect> enemyStatusEffects = damage.getTarget().getAdventurer().getStatusEffects();
            // CreepingToxinStatusEffect 디버프가 있을경우 데미지를 50% 상승시킨다.
            for(StatusEffect enemyStatusEffect : enemyStatusEffects){
                if(enemyStatusEffect instanceof CreepingToxinStatusEffect){
                    return 1.5f;
                }
            }
        }
        return 1f;
    }

    @Override
    public void advanceTick(Adventurer adventurer) {
        super.advanceTick(adventurer);
        lifeTick -= 1;

        if(lifeTick <= 0){
            setDelete();
        }
    }

    // getter, setter


    public int getLifeTick() {
        return lifeTick;
    }
    public void setLifeTick(int lifeTick) {
        this.lifeTick = lifeTick;
    }
}
