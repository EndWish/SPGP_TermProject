package tukorea2018180009.ac.kr.example.equipmentcollector.Skills;

import android.graphics.Bitmap;

import java.util.ArrayList;

import tukorea2018180009.ac.kr.example.equipmentcollector.Memory.BitmapPool;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI.BattleProfile;

public class OrbitalFlameSkill extends Skill {

    private static final String name = "OrbitalFlame";
    private static final String desc =  "<오비탈 플레임>\n 적에게 불덩이를 발사하여 3번의 마법 데미지(마법 공격력 30%)를 줍니다.";
    private static final Bitmap icon = BitmapPool.get(R.mipmap.png_icon_skill_orbital_flame);

    public OrbitalFlameSkill(){
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
        return 1.2f;
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
        attacks.add(new OrbitalFlameAttack1(caster, target));
        return attacks;
    }
}
