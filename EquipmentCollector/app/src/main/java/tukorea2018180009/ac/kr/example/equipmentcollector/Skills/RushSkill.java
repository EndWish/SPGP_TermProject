package tukorea2018180009.ac.kr.example.equipmentcollector.Skills;

import android.graphics.Bitmap;

import java.util.ArrayList;

import tukorea2018180009.ac.kr.example.equipmentcollector.Memory.BitmapPool;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI.BattleProfile;

public class RushSkill extends Skill {

    private static final String name = "Rush";
    private static final String desc =  "<러쉬>\n 적을 칼로 찔러 관통 데미지(베기 공격력 50% + 관통 공격력 50%)를 준다. 그리고 바로 뒤에 있는 적에게 50% 더 강한 데미지를 춘다.";
    private static final Bitmap icon = BitmapPool.get(R.mipmap.png_icon_skill_rush);

    public RushSkill(){
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
        return 0.4f;
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
        attacks.add(new RushAttack1(caster, target));
        attacks.add(new RushAttack2(caster, target));
        return attacks;
    }
}
