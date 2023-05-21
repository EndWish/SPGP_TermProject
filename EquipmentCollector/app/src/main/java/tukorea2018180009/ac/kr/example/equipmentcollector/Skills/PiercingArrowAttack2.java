package tukorea2018180009.ac.kr.example.equipmentcollector.Skills;

import android.util.Log;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Status;
import tukorea2018180009.ac.kr.example.equipmentcollector.Damage;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI.BattleProfile;

public class PiercingArrowAttack2 extends Attack {

    public PiercingArrowAttack2(BattleProfile caster, BattleProfile target) {
        super(caster, target, caster.getX(), caster.getY());
        
        // 스프라이트를 생성
        sprite = new Sprite(new Sprite.Builder(R.mipmap.png_effect_skill_piercing_arrow2, 0,0, 270, 90)
                .setPivot(90,45)
                .setFlip(!allyAttack, false)
                .setAnimation(3, 3, 0.3f, false));
        addChild(sprite);
        
        // 광역공격인지 세팅
        areaOfEffect = true;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        // 적이 있는 방향으로 이동한다.
        moveTo(target, deltaTime * 5000f);

        // 적이 있는 곳까지 도달할 경우
        if(isSamePosition(target)){
            damageApply();
            setDelete();
        }
    }

    protected void damageApply() {

        // 데미지를 계산하여 적에게 데미지를 준다.
        Status casterTotalStatue = caster.getAdventurer().getTotalStatus();

        Damage damage = new Damage(caster, target);
        damage.addDamage(Damage.Type.pierce, 0.5f * casterTotalStatue.get(Status.Type.piercePower));
        damage.addDamage(Damage.Type.impact, 0.3f * casterTotalStatue.get(Status.Type.piercePower));
        damage.addDamage(Damage.Type.impact, 0.5f * casterTotalStatue.get(Status.Type.impactPower));
        target.takeDamage(damage);
    }

}
