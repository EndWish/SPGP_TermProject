package tukorea2018180009.ac.kr.example.equipmentcollector.Skills;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Status;
import tukorea2018180009.ac.kr.example.equipmentcollector.Damage;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI.BattleProfile;

public class OrbitalFlameAttack1 extends Attack {
    float oldX, oldY;
    float jumpPower = 1000;

    public OrbitalFlameAttack1(BattleProfile caster, BattleProfile target) {
        super(caster, target, caster.getX(), caster.getY());
        
        // 스프라이트를 생성
        sprite = new Sprite(new Sprite.Builder(R.mipmap.png_effect_skill_orbital_flame1, 0,0, 200, 170)
                .setPivot(Sprite.PivotType.center)
                .setFlip(!allyAttack, false)
                .setAnimation(5, 6, 0.5f, true));
        addChild(sprite);
        
        // 광역공격인지 세팅
        areaOfEffect = true;

        oldX = getX();
        oldY = getY();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        // 중력 추가.
        jumpPower -= deltaTime * 400f;
        jumpPower = Math.max(jumpPower, 0f);
        y -= jumpPower * deltaTime;

        // 적이 있는 방향으로 이동한다.
        moveTo(target, deltaTime * 800f);

        // 회전시키기
        rotation = (float)Math.toDegrees( Math.atan2(y -oldY, x - oldX));
        oldX = getX();
        oldY = getY();

        // 적이 있는 곳까지 도달할 경우
        if(isSamePosition(target) && !isDeleted()){
            damageApply();
            setDelete();
        }
    }

    protected void damageApply() {

        battleScene.addAttack(new OrbitalFlameAttack2(caster, target));
    }

}
