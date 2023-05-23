package tukorea2018180009.ac.kr.example.equipmentcollector.Skills;

import android.graphics.Bitmap;

import java.util.ArrayList;

import tukorea2018180009.ac.kr.example.equipmentcollector.Memory.BitmapPool;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI.BattleProfile;

public class ArrowBlasterSkill extends Skill {

    private static final String name = "ArrowBlaster";
    private static final String desc =  "<에로우 블래스터>\n 적에게 관통 데미지(관통 공격력의 110%) 만큼 데미지를 준다.\n" +
            "EnchantedQuiver를 통해 충전된 횟수만큼 추가적으로 화살을 쏜다.";
    private static final Bitmap icon = BitmapPool.get(R.mipmap.png_icon_skill_arrow_blaster);

    public ArrowBlasterSkill(){
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
        attacks.add(new ArrowBlasterAttack1(caster, target));
        return attacks;
    }
}
