package tukorea2018180009.ac.kr.example.equipmentcollector.Skills;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Status;
import tukorea2018180009.ac.kr.example.equipmentcollector.Damage;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI.BattleProfile;

public class ArrowBlasterAttack1 extends Attack {
    float lifeTime = 0.9f;

    public ArrowBlasterAttack1(BattleProfile caster, BattleProfile target) {
        super(caster, target, caster.getX(), caster.getY());
        
        // 스프라이트를 생성
        sprite = new Sprite(new Sprite.Builder(R.mipmap.png_effect_skill_arrow_blaster1, 0,0, 600, 660)
                .setPivot(Sprite.PivotType.center)
                .setFlip(!allyAttack, false)
                .setAnimation(5, 11, lifeTime, false));
        addChild(sprite);
        
        // 광역공격인지 세팅
        areaOfEffect = false;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        lifeTime -= deltaTime;

        if(lifeTime <= 0 && !isDeleted()) {
            battleScene.addAttack(new ArrowBlasterAttack2(caster, target));
            setDelete();
        }
    }

}
