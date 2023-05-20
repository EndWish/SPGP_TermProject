package tukorea2018180009.ac.kr.example.equipmentcollector.Skills;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Status;
import tukorea2018180009.ac.kr.example.equipmentcollector.Damage;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI.BattleProfile;

public class RushAttack2 extends Attack {
    float lifeTime = 0.5f;
    final float dmgTiming1 = 0.4f;
    final float dmgTiming2 = 0.1f;
    int damageRemaining = 1;
    BattleProfile nextTarget;

    public RushAttack2(BattleProfile caster, BattleProfile target) {
        super(caster, target, target.getX(), target.getY());
        
        // 스프라이트를 생성
        sprite = new Sprite(new Sprite.Builder(R.mipmap.png_effect_skill_rush2, 0,0, 600, 300)
                .setPivot(200, 150)
                .setFlip(!allyAttack, false)
                .setAnimation(5, 8, lifeTime, false));
        addChild(sprite);
        
        // 광역공격인지 세팅
        areaOfEffect = true;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        lifeTime -= deltaTime;

        // 앞에 있는 적에게 데미지를 준다.
        if(lifeTime <= dmgTiming1 && 0 < damageRemaining){
            damageApplyFront();
        }
        // 뒤에 있는적을 후속타를 준다.
        if(lifeTime <= dmgTiming2 && nextTarget != null){
            damageApplyBack();
        }

        if(lifeTime <= 0) {
            setDelete();
        }
    }

    protected void damageApplyFront() {
        --damageRemaining;

        // 데미지를 계산하여 적에게 데미지를 준다.
        Status casterTotalStatue = caster.getAdventurer().getTotalStatus();

        Damage damage = new Damage();
        damage.addDamage(Damage.Type.slash, 0.5f * casterTotalStatue.get(Status.Type.slashPower));
        damage.addDamage(Damage.Type.slash, 0.5f * casterTotalStatue.get(Status.Type.piercePower));
        target.takeDamage(damage);

        // 뒤에 있는 적을 찾아낸다.
        int nextTargetIndex = battleScene.getBattleProfileIndex(target, relativeEnemies) + 1;
        if(nextTargetIndex < relativeEnemies.size()){
            nextTarget = relativeEnemies.get(nextTargetIndex);
        }
    }

    protected void damageApplyBack() {
        // 데미지를 계산하여 적에게 데미지를 준다.
        Status casterTotalStatue = caster.getAdventurer().getTotalStatus();

        Damage damage = new Damage();
        damage.addDamage(Damage.Type.slash, 0.75f * casterTotalStatue.get(Status.Type.slashPower));
        damage.addDamage(Damage.Type.slash, 0.75f * casterTotalStatue.get(Status.Type.piercePower));
        nextTarget.takeDamage(damage);

        nextTarget = null;
    }

}
