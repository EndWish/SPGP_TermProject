package tukorea2018180009.ac.kr.example.equipmentcollector.Skills;

import android.graphics.Bitmap;

import java.util.ArrayList;

import tukorea2018180009.ac.kr.example.equipmentcollector.Memory.BitmapPool;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI.BattleProfile;

public class FinalBlowSkill extends Skill {

    private static final String name = "FinalBlow";
    private static final String desc =  "<파이널 블로우>\n 적을 강력하게 후려쳐 베기 데미지(베기 공격력 100%) + 충격 데미지(충격 공격력 165%)를 준다.";
    private static final Bitmap icon = BitmapPool.get(R.mipmap.png_icon_skill_final_blow);

    public FinalBlowSkill(){
        super();
    }

    // IAbility의 함수들을 Override하여 이 스킬만의 특성들을 만든다.

    // 이 스킬만의 고유한 값을 리턴하는 함수
    @Override
    public Bitmap getIcon() {
        return icon;
    }
    @Override
    public Grade getGrade() {
        return Grade.normal;
    }
    @Override
    public float getCorrectionSpeed() {
        return 0.35f;
    }
    @Override
    public String getDesc() {
        return desc;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public ArrayList<Attack> createAttacks(BattleProfile caster, BattleProfile target) {
        ArrayList<Attack> attacks = new ArrayList<>();
        attacks.add(new FinalBlowAttack(caster, target));
        return attacks;
    }
}
