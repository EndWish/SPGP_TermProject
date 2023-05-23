package tukorea2018180009.ac.kr.example.equipmentcollector.Skills;

import android.graphics.Bitmap;

import java.util.ArrayList;

import tukorea2018180009.ac.kr.example.equipmentcollector.Memory.BitmapPool;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI.BattleProfile;

public class PoisonMistReinforceSkill extends Skill {

    private static final String name = "PoisonMistReinforce";
    private static final String desc =  "<포이즌 미스트 리인포스>\n 아군에게 사용할 경우 100틱동안 크립팅 톡신이 묻어 있는 적에게 50%의 추가 데미지를 주는 버프를 건다.";
    private static final Bitmap icon = BitmapPool.get(R.mipmap.png_icon_skill_poison_mist_reinforce);

    public PoisonMistReinforceSkill(){
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
        return 0.9f;
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
    public ArrayList<BattleProfile> getAttackableTarget(ArrayList<BattleProfile> enemies, ArrayList<BattleProfile> allies) {
        return allies;
    }

    @Override
    public ArrayList<Attack> createAttacks(BattleProfile caster, BattleProfile target) {
        ArrayList<Attack> attacks = new ArrayList<>();
        attacks.add(new PoisonMistReinforceAttack1(caster, target));
        return attacks;
    }
}
