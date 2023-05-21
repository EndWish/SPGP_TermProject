package tukorea2018180009.ac.kr.example.equipmentcollector.Skills;

import android.graphics.Bitmap;

import java.util.ArrayList;

import tukorea2018180009.ac.kr.example.equipmentcollector.Memory.BitmapPool;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI.BattleProfile;

public class CreepingToxinSkill extends Skill {

    private static final String name = "CreepingToxin";
    private static final String desc =  "<크립팅 톡신>\n 적을 중독시켜서 20틱 마다 저주 데미지(저주 공격력 25%)를 총 5번 준다.";
    private static final Bitmap icon = BitmapPool.get(R.mipmap.png_icon_skill_creeping_toxin);

    public CreepingToxinSkill(){
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
        return 1.1f;
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
        attacks.add(new CreepingToxinAttack1(caster, target));
        return attacks;
    }
}
