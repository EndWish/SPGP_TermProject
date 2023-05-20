package tukorea2018180009.ac.kr.example.equipmentcollector.Skills;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Status;
import tukorea2018180009.ac.kr.example.equipmentcollector.Damage;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI.BattleProfile;

public class EmpoweredArrowsAttack2 extends Attack {
    float lifeTime = 1.5f;
    final float dmgTiming = 1.3f;
    int damageRemaining = 1;

    public EmpoweredArrowsAttack2(BattleProfile caster, BattleProfile target) {
        super(caster, target, caster.getX(), caster.getY());
        
        // 스프라이트를 생성
        sprite = new Sprite(new Sprite.Builder(R.mipmap.png_effect_skill_empowered_arrows2, 0, 0, 1500, 300)
                .setPivot(100, 150)
                .setFlip(!allyAttack, false)
                .setAnimation(5, 17, lifeTime, false));
        addChild(sprite);
        
        // 광역공격인지 세팅
        areaOfEffect = true;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        lifeTime -= deltaTime;

        if(lifeTime <= dmgTiming && 0 < damageRemaining){
            damageApply();
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
        damage.addDamage(Damage.Type.pierce, 0.8f * casterTotalStatue.get(Status.Type.piercePower));
        for(BattleProfile enemy : relativeEnemies)
            enemy.takeDamage(damage);
    }

}
