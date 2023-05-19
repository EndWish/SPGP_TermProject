package tukorea2018180009.ac.kr.example.equipmentcollector.Skills;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Status;
import tukorea2018180009.ac.kr.example.equipmentcollector.Damage;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI.BattleProfile;

public class HealAttack extends Attack {
    float lifeTime = 1.0f;
    final float healTiming = 0.5f;
    int healRemaining = 1;

    public HealAttack(BattleProfile caster, BattleProfile target) {
        super(caster, target);
        
        // 스프라이트를 생성
        sprite = new Sprite(new Sprite.Builder(R.mipmap.png_effect_skill_heal, target.getX(), target.getY(), 300, 350)
                .setPivot(Sprite.PivotType.center)
                .setFlip(!allyAttack, false)
                .setAnimation(5, 9, lifeTime, false));
        addChild(sprite);
        
        // 광역공격인지 세팅
        areaOfEffect = false;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        lifeTime -= deltaTime;

        // 앞에 있는 적에게 데미지를 준다.
        if(lifeTime <= healTiming && 0 < healRemaining){
            healApplyFront();
        }

        if(lifeTime <= 0) {
            setDelete();
        }
    }

    protected void healApplyFront() {
        --healRemaining;

        Status casterTotalStatue = caster.getAdventurer().getTotalStatus();

        float healingAmount = casterTotalStatue.get(Status.Type.holyPower) * 1.1f;
        target.receiveHealing(healingAmount);
    }
}
