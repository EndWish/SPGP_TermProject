package tukorea2018180009.ac.kr.example.equipmentcollector.Skills;

import android.graphics.Bitmap;

import java.util.ArrayList;

import tukorea2018180009.ac.kr.example.equipmentcollector.Memory.BitmapPool;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI.BattleProfile;

public class AnotherBiteSkill extends Skill {

    private static final String name = "AnotherBite";
    private static final String desc =  "<어나더 바이트>\n 적을 물어 관통 데미지(관통 공격력 100%)를 준다.";
    private static final Bitmap icon = BitmapPool.get(R.mipmap.png_icon_skill_another_bite);

    public AnotherBiteSkill(){
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
    public ArrayList<Attack> createAttacks(BattleProfile caster, BattleProfile target) {
        ArrayList<Attack> attacks = new ArrayList<>();
        attacks.add(new AnotherBiteAttack(caster, target));
        return attacks;
    }
}
