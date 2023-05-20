package tukorea2018180009.ac.kr.example.equipmentcollector.Skills;

import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI.BattleProfile;

public class PiercingArrowAttack1 extends Attack {
    float lifeTime = 0.7f;
    final float fireTiming = 0.1f;
    boolean fire = false;

    public PiercingArrowAttack1(BattleProfile caster, BattleProfile target) {
        super(caster, target, caster.getX(), caster.getY());
        
        // 스프라이트를 생성
        sprite = new Sprite(new Sprite.Builder(R.mipmap.png_effect_skill_piercing_arrow1, 0,0, 600, 600)
                .setPivot(300, 325)
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

        if(lifeTime <= fireTiming && !fire){
            fire = true;
            battleScene.addAttack(new PiercingArrowAttack2(caster, target));
        }

        if(lifeTime <= 0) {
            setDelete();
        }
    }

}
