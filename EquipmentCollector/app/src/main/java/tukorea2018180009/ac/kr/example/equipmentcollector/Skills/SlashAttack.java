package tukorea2018180009.ac.kr.example.equipmentcollector.Skills;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Adventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Status;
import tukorea2018180009.ac.kr.example.equipmentcollector.Damage;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI.BattleProfile;

public class SlashAttack extends Attack {
    float lifeTime = 0.5f;
    final float dmgTiming = 0.2f;
    int damageRemaining = 1;

    public SlashAttack(BattleProfile caster, BattleProfile target) {
        super(caster, target, target.getX(), target.getY());
        
        // 스프라이트를 생성
        sprite = new Sprite(new Sprite.Builder(R.mipmap.png_effect_skill_slash, 0,0, 300, 200)
                .setPivot(200, 100)
                .setFlip(!allyAttack, false)
                .setAnimation(5, 6, lifeTime, false));
        addChild(sprite);
        
        // 광역공격인지 세팅
        areaOfEffect = false;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        lifeTime -= deltaTime;

        if(lifeTime <= 0) {
            setDelete();
        }

        if(lifeTime <= dmgTiming && 0 < damageRemaining){
            damageApply();
        }
    }

    protected void damageApply() {
        --damageRemaining;

        // 데미지를 계산하여 적에게 데미지를 준다.
        Status casterTotalStatue = caster.getAdventurer().getTotalStatus();

        Damage damage = new Damage(caster, target);
        damage.addDamage(Damage.Type.slash, 0.7f * casterTotalStatue.get(Status.Type.slashPower));
        damage.addDamage(Damage.Type.slash, 0.3f * casterTotalStatue.get(Status.Type.piercePower));
        target.takeDamage(damage);
    }

}
