package tukorea2018180009.ac.kr.example.equipmentcollector.Skills;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Status;
import tukorea2018180009.ac.kr.example.equipmentcollector.Damage;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI.BattleProfile;

public class ArrowBlasterAttack4 extends Attack {
    float lifeTime = 0.5f;

    public ArrowBlasterAttack4(BattleProfile caster, BattleProfile target) {
        super(caster, target, target.getX(), target.getY());
        
        // 스프라이트를 생성
        sprite = new Sprite(new Sprite.Builder(R.mipmap.png_effect_skill_arrow_blaster4, 0,0, 600, 600)
                .setPivot(Sprite.PivotType.center)
                .setFlip(!allyAttack, false)
                .setAnimation(5, 7, lifeTime, false));
        addChild(sprite);
        
        // 광역공격인지 세팅
        areaOfEffect = false;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        lifeTime -= deltaTime;

        if(lifeTime <= 0) {
            damageApply();
            setDelete();
        }
    }

    protected void damageApply() {

        // 데미지를 계산하여 적에게 데미지를 준다.
        Status casterTotalStatue = caster.getAdventurer().getTotalStatus();

        Damage damage = new Damage(caster, target);
        damage.addDamage(Damage.Type.pierce, 1.1f * casterTotalStatue.get(Status.Type.piercePower));
        target.takeDamage(damage);
    }

}
