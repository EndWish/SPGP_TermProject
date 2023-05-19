package tukorea2018180009.ac.kr.example.equipmentcollector.Skills;

import android.graphics.Bitmap;

import java.util.ArrayList;

import tukorea2018180009.ac.kr.example.equipmentcollector.Memory.BitmapPool;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI.BattleProfile;

public class BlessingSkill extends Skill {

    private static final String name = "Blessing";
    private static final String desc =  "<힐>\n 100틱동안 아군의 모든 방어력(베기/관통/충격)을 (신성 공격력 40%)만큼, 모든 저항력(마법/신성/저주)을 (신성 공격력 60%)만큼강화 시킨다. \n" +
                                        "또한 4번에 걸쳐 총(신성 공격력 100%)만큼 회복시킨다.";
    private static final Bitmap icon = BitmapPool.get(R.mipmap.png_icon_skill_blessing);

    public BlessingSkill(){
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
    public ArrayList<BattleProfile> getAttackableTarget(ArrayList<BattleProfile> enemies, ArrayList<BattleProfile> allies) {
        return allies;
    }

    @Override
    public ArrayList<Attack> createAttacks(BattleProfile caster, BattleProfile target) {
        ArrayList<Attack> attacks = new ArrayList<>();
        attacks.add(new BlessingAttack(caster, target));
        return attacks;
    }
}
