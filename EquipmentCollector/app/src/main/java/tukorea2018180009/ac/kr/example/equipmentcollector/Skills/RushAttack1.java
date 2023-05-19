package tukorea2018180009.ac.kr.example.equipmentcollector.Skills;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Status;
import tukorea2018180009.ac.kr.example.equipmentcollector.Damage;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI.BattleProfile;

public class RushAttack1 extends Attack {
    float lifeTime = 0.5f;

    public RushAttack1(BattleProfile caster, BattleProfile target) {
        super(caster, target);
        
        // 스프라이트를 생성
        sprite = new Sprite(new Sprite.Builder(R.mipmap.png_effect_skill_rush1, caster.getX(), caster.getY(), 300, 300)
                .setPivot(Sprite.PivotType.center)
                .setFlip(!allyAttack, false)
                .setAnimation(5, 7, lifeTime, false));
        addChild(sprite);
        
        // 광역공격인지 세팅
        areaOfEffect = true;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        lifeTime -= deltaTime;

        if(lifeTime <= 0) {
            setDelete();
        }
    }

}
