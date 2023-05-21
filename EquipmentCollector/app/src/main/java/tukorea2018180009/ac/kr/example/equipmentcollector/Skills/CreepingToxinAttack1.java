package tukorea2018180009.ac.kr.example.equipmentcollector.Skills;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Status;
import tukorea2018180009.ac.kr.example.equipmentcollector.Damage;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.StatusEffect.BlessingStatusEffect;
import tukorea2018180009.ac.kr.example.equipmentcollector.StatusEffect.CreepingToxinStatusEffect;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI.BattleProfile;

public class CreepingToxinAttack1 extends Attack {
    float lifeTime = 0.75f;

    public CreepingToxinAttack1(BattleProfile caster, BattleProfile target) {
        super(caster, target, target.getX(), target.getY());
        
        // 스프라이트를 생성
        sprite = new Sprite(new Sprite.Builder(R.mipmap.png_effect_skill_creeping_toxin1, 0,0, 300, 300)
                .setPivot(Sprite.PivotType.center)
                .setFlip(!allyAttack, false)
                .setAnimation(5, 10, lifeTime, false));
        addChild(sprite);
        
        // 광역공격인지 세팅
        areaOfEffect = false;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        lifeTime -= deltaTime;

        if(lifeTime <= 0 && !isDeleted()) {
            Status casterTotalStatue = caster.getAdventurer().getTotalStatus();
            float cursedPower = casterTotalStatue.get(Status.Type.cursedPower);
            target.getAdventurer().addStatusEffect(new CreepingToxinStatusEffect(target, cursedPower));
            setDelete();
        }
    }

}
