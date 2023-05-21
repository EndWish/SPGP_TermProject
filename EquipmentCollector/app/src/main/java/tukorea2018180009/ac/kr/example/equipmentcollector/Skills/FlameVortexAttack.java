package tukorea2018180009.ac.kr.example.equipmentcollector.Skills;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Status;
import tukorea2018180009.ac.kr.example.equipmentcollector.Damage;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI.BattleProfile;

public class FlameVortexAttack extends Attack {
    float lifeTime = 1.5f;
    final float dmgTimings[] = new float[]{1.2f, 0.6f, 0.1f};
    int damageRemaining = 3;

    public FlameVortexAttack(BattleProfile caster, BattleProfile target) {
        super(caster, target, caster.getX(), caster.getY());
        
        // 스프라이트를 생성
        sprite = new Sprite(new Sprite.Builder(R.mipmap.png_effect_skill_flame_vortex, 0, 0, 1000, 800)
                .setPivot(100, 500)
                .setFlip(!allyAttack, false)
                .setAnimation(5, 14, lifeTime, false));
        addChild(sprite);
        
        // 광역공격인지 세팅
        areaOfEffect = true;
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

        int casterIndex = battleScene.getBattleProfileIndex(caster, relativeAllies);
        int targetNum = Math.min(4 - casterIndex, relativeEnemies.size());

        Damage damage = new Damage(caster, null);
        damage.addDamage(Damage.Type.magic, 0.5f * casterTotalStatue.get(Status.Type.magicPower) / targetNum);
        for(int i = 0; i < targetNum; ++i){
            damage.setTarget(relativeEnemies.get(i));
            relativeEnemies.get(i).takeDamage(damage);
        }

    }

}
