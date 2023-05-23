package tukorea2018180009.ac.kr.example.equipmentcollector.Skills;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.ShamarAdventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI.BattleProfile;

public class EnchantedQuiverAttack extends Attack {
    float lifeTime = 0.75f;

    public EnchantedQuiverAttack(BattleProfile caster, BattleProfile target) {
        super(caster, target, target.getX(), target.getY());
        
        // 스프라이트를 생성
        sprite = new Sprite(new Sprite.Builder(R.mipmap.png_effect_skill_enchanted_quiver, 0,0, 200, 400)
                .setPivot(Sprite.PivotType.center)
                .setFlip(!allyAttack, false)
                .setAnimation(5, 17, lifeTime, false));
        addChild(sprite);
        
        // 광역공격인지 세팅
        areaOfEffect = false;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        lifeTime -= deltaTime;

        if(lifeTime <= 0 && !isDeleted()) {
            buffApply();
            setDelete();
        }
    }

    protected void buffApply() {
        if(target.getAdventurer() instanceof ShamarAdventurer){
            ShamarAdventurer shamarAdventurer = (ShamarAdventurer)target.getAdventurer();
            shamarAdventurer.addQuiver(1);
        }
    }

}
