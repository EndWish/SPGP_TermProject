package tukorea2018180009.ac.kr.example.equipmentcollector.Skills;

import android.graphics.Bitmap;

import java.util.ArrayList;

import tukorea2018180009.ac.kr.example.equipmentcollector.Memory.BitmapPool;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI.BattleProfile;

public class FlameVortexSkill extends Skill {

    private static final String name = "FlameVortex";
    private static final String desc =  "<플레임 볼텍스>\n 자신의 앞 최대 4칸까지 적이 있을 경우 마법 데미지(마법 공격력 50%)를 3번 준다." +
            "\n만약 여러 적을 공격하게 될 경우 인원수 만큼 데미지가 감소한다.";
    private static final Bitmap icon = BitmapPool.get(R.mipmap.png_icon_skill_flame_vortex);

    public FlameVortexSkill(){
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
        return 0.45f;
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
        attacks.add(new FlameVortexAttack(caster, target));
        return attacks;
    }
}
