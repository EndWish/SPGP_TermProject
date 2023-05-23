package tukorea2018180009.ac.kr.example.equipmentcollector.Skills;

import android.util.Log;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Status;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.StatusEffect.CreepingToxinStatusEffect;
import tukorea2018180009.ac.kr.example.equipmentcollector.StatusEffect.PoisonMistReinforceStatusEffect;
import tukorea2018180009.ac.kr.example.equipmentcollector.StatusEffect.StatusEffect;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI.BattleProfile;

public class PoisonMistReinforceAttack1 extends Attack {
    float lifeTime = 0.5f;

    public PoisonMistReinforceAttack1(BattleProfile caster, BattleProfile target) {
        super(caster, target, target.getX(), target.getY());
        
        // 스프라이트를 생성
        sprite = new Sprite(new Sprite.Builder(R.mipmap.png_effect_skill_poison_mist_reinforce1, 0,0, 300, 300)
                .setPivot(Sprite.PivotType.center)
                .setFlip(!allyAttack, false)
                .setAnimation(5, 5, lifeTime, false));
        addChild(sprite);
        
        // 광역공격인지 세팅
        areaOfEffect = false;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        lifeTime -= deltaTime;

        if(lifeTime <= 0 && !isDeleted()) {
            PoisonMistReinforceStatusEffect existPoisonMistReinforceStatusEffect = null;
            for(StatusEffect statusEffect : target.getAdventurer().getStatusEffects()){
                Log.e("테스트", statusEffect.getClass().getSimpleName());
                if(statusEffect instanceof PoisonMistReinforceStatusEffect){
                    existPoisonMistReinforceStatusEffect = (PoisonMistReinforceStatusEffect)statusEffect;
                    Log.e("테스트", "기존의 버프가 있는것을 확인했다..");
                    break;
                }
            }

            // 상대가 PoisonMistReinforceStatusEffect 에 걸려있지 않을 경우
            if(existPoisonMistReinforceStatusEffect == null){
                // 버프를 추가한다.
                Log.e("테스트", "기존의 버프가 없다.");
                target.getAdventurer().addStatusEffect(new PoisonMistReinforceStatusEffect(target));
            }
            // 상대가 이미 PoisonMistReinforceStatusEffect 에 걸려있을 경우
            else{
                // 지속시간을 초기화 해준다.
                Log.e("테스트", "기존의 버프가 있다.");
                existPoisonMistReinforceStatusEffect.setLifeTick(100);
            }

            setDelete();
        }
    }

}
