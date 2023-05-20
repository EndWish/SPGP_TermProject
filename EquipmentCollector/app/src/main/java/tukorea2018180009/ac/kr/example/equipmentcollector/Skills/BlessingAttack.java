package tukorea2018180009.ac.kr.example.equipmentcollector.Skills;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Status;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.StatusEffect.BlessingStatusEffect;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI.BattleProfile;

public class BlessingAttack extends Attack {
    float lifeTime = 1.0f;
    final float buffTiming = 0.5f;
    int buffRemaining = 1;

    public BlessingAttack(BattleProfile caster, BattleProfile target) {
        super(caster, target, target.getX(), target.getY());
        
        // 스프라이트를 생성
        sprite = new Sprite(new Sprite.Builder(R.mipmap.png_effect_skill_blessing1, 0,0, 300, 350)
                .setPivot(150, 250)
                .setFlip(!allyAttack, false)
                .setAnimation(5, 13, lifeTime, false));
        addChild(sprite);
        
        // 광역공격인지 세팅
        areaOfEffect = false;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        lifeTime -= deltaTime;

        // 앞에 있는 적에게 데미지를 준다.
        if(lifeTime <= buffTiming && 0 < buffRemaining){
            buffApply();
        }

        if(lifeTime <= 0) {
            setDelete();
        }
    }

    protected void buffApply() {
        --buffRemaining;

        Status casterTotalStatue = caster.getAdventurer().getTotalStatus();

        float holyPower = casterTotalStatue.get(Status.Type.holyPower);
        target.getAdventurer().addStatusEffect(new BlessingStatusEffect(target, holyPower));
    }
}
