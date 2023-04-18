package tukorea2018180009.ac.kr.example.equipmentcollector.Skills;

import android.graphics.Bitmap;

import tukorea2018180009.ac.kr.example.equipmentcollector.BitmapPool;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;

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
    Grade getGrade() {
        return Grade.essential;
    }
    @Override
    float getCorrectionSpeed() {
        return 1.0f;
    }
    @Override
    String getDesc() {
        return desc;
    }
    @Override
    String getName() {
        return name;
    }
}