package tukorea2018180009.ac.kr.example.equipmentcollector.Skills;

import android.graphics.Bitmap;

import tukorea2018180009.ac.kr.example.equipmentcollector.Memory.BitmapPool;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI.BattleProfile;

public class SlashSkill extends Skill {

    private static final String name = "Slash";
    private static final String desc =  "<슬래쉬>\n 적을 칼로 베어 베기 데미지(베기 공격력 70% + 관통 공격력 30%)를 준다.";
    private static final Bitmap icon = BitmapPool.get(R.mipmap.png_icon_skill_slash);

    public SlashSkill(){
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
        return Grade.essential;
    }
    @Override
    public float getCorrectionSpeed() {
        return 1.0f;
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
    public Attack CreateAttack(BattleProfile caster, BattleProfile target) {
        return new SlashAttack(caster, target);
    }
}
