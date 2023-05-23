package tukorea2018180009.ac.kr.example.equipmentcollector.Skills;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.ShamarAdventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI.BattleProfile;

public class ArrowBlasterAttack2 extends Attack {
    float lifeTime = 0.4f;
    int attackCount = 1;

    public ArrowBlasterAttack2(BattleProfile caster, BattleProfile target) {
        super(caster, target, caster.getX(), caster.getY());
        
        // 스프라이트를 생성
        sprite = new Sprite(new Sprite.Builder(R.mipmap.png_effect_skill_arrow_blaster2, 0,0, 300, 330)
                .setPivot(Sprite.PivotType.center)
                .setFlip(!allyAttack, false)
                .setAnimation(4, 4, lifeTime, true));
        addChild(sprite);
        
        // 광역공격인지 세팅
        areaOfEffect = false;

        // 공격횟수를 알아낸다.
        if(caster != null && caster.getAdventurer() instanceof ShamarAdventurer){
            ShamarAdventurer shamarAdventurer = (ShamarAdventurer)(caster.getAdventurer());
            attackCount = shamarAdventurer.getQuiver() + 1;
        }

    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        lifeTime -= deltaTime;

        if(lifeTime <= 0 && !isDeleted()) {
            if(0 < attackCount){
                --attackCount;
                battleScene.addAttack(new ArrowBlasterAttack4(caster, target));
                lifeTime = 0.4f;
            }
            else{
                battleScene.addAttack(new ArrowBlasterAttack3(caster, target));
                setDelete();
            }
        }
    }
}
