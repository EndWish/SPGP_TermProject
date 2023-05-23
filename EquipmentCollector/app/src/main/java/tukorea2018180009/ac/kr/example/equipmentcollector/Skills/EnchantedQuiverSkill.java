package tukorea2018180009.ac.kr.example.equipmentcollector.Skills;

import android.graphics.Bitmap;

import java.util.ArrayList;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.ShamarAdventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.Memory.BitmapPool;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI.BattleProfile;

public class EnchantedQuiverSkill extends Skill {

    private static final String name = "EnchantedQuiver";
    private static final String desc =  "<인챈트드 퀵버>\n ArrowBlaster의 발사 횟수를 1회 증가시킨다.\n";
    private static final Bitmap icon = BitmapPool.get(R.mipmap.png_icon_skill_enchanted_quiver);

    public EnchantedQuiverSkill(){
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
        return 1.3f;
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
        ArrayList<BattleProfile> result = new ArrayList<>();
        for(BattleProfile battleProfile : allies)
            if(battleProfile.getAdventurer() instanceof  ShamarAdventurer)
                result.add(battleProfile);

        return result.size() == 0 ? null : result;
    }

    @Override
    public ArrayList<Attack> createAttacks(BattleProfile caster, BattleProfile target) {
        ArrayList<Attack> attacks = new ArrayList<>();
        attacks.add(new EnchantedQuiverAttack(caster, target));
        return attacks;
    }
}
