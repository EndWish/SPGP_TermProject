package tukorea2018180009.ac.kr.example.equipmentcollector.Skills;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Status;
import tukorea2018180009.ac.kr.example.equipmentcollector.Damage;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI.BattleProfile;

public class OrbitalFlameAttack2 extends Attack {
    float lifeTime = 0.7f;
    final float dmgTimings[] = new float[]{0.7f, 0.35f, 0.0f};
    int damageRemaining = 3;

    public OrbitalFlameAttack2(BattleProfile caster, BattleProfile target) {
        super(caster, target, target.getX(), target.getY());
        
        // 스프라이트를 생성
        sprite = new Sprite(new Sprite.Builder(R.mipmap.png_effect_skill_orbital_flame2, 0,0, 400, 400)
                .setPivot(Sprite.PivotType.center)
                .setFlip(!allyAttack, false)
                .setAnimation(5, 8, lifeTime, false));
        addChild(sprite);
        
        // 광역공격인지 세팅
        areaOfEffect = false;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        lifeTime -= deltaTime;

        for(int i = 0; i <3; ++i){
            if(lifeTime <= dmgTimings[i] && damageRemaining == 3- i){
                damageApply();
            }
        }

        if(lifeTime <= 0) {
            setDelete();
        }
    }

    protected void damageApply() {
        --damageRemaining;

        // 데미지를 계산하여 적에게 데미지를 준다.
        Status casterTotalStatue = caster.getAdventurer().getTotalStatus();

        Damage damage = new Damage();
        damage.addDamage(Damage.Type.magic, 0.3f * casterTotalStatue.get(Status.Type.magicPower));
        target.takeDamage(damage);
    }

}
