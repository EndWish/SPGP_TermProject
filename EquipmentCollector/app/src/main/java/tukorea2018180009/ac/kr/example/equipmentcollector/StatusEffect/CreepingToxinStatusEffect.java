package tukorea2018180009.ac.kr.example.equipmentcollector.StatusEffect;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Adventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Status;
import tukorea2018180009.ac.kr.example.equipmentcollector.Damage;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI.BattleProfile;

public class CreepingToxinStatusEffect extends StatusEffect{
    protected int lifeTick;
    final int dmgTimings[] = new int[]{0, 20, 40, 60, 80};
    protected float cursedPower;
    protected Sprite effectSprite;

    public CreepingToxinStatusEffect(BattleProfile target, float cursedPower) {
        super(target);
        this.cursedPower = cursedPower;
        lifeTick = 100;

        effectSprite = new Sprite(new Sprite.Builder(R.mipmap.png_effect_skill_creeping_toxin2, 0,0,300,300)
                .setAnimation(5, 7, 0.7f, true)
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
    public void advanceTick(Adventurer adventurer) {
        super.advanceTick(adventurer);
        lifeTick -= 1;

        for(int dmgTiming : dmgTimings){
            if(dmgTiming == lifeTick){
                damageApply();
                break;
            }
        }

        if(lifeTick <= 0){
            setDelete();
        }
    }

    protected void damageApply() {
        Damage damage = new Damage();
        damage.addDamage(Damage.Type.cursed, 0.25f * cursedPower);
        target.takeDamage(damage);
    }
}
