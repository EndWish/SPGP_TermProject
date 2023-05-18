package tukorea2018180009.ac.kr.example.equipmentcollector.Skills;

import java.util.ArrayList;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Adventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Status;
import tukorea2018180009.ac.kr.example.equipmentcollector.IAbility;
import tukorea2018180009.ac.kr.example.equipmentcollector.IIcon;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI.BattleProfile;

public abstract class Skill implements IAbility, IIcon {
    protected float gauge;
    
    // 생성자
    public Skill() {
        gauge = 0;
    }

    // 자식 클래스에서 설정할 함수(값)들
    public abstract Grade getGrade();
    public abstract float getCorrectionSpeed();
    public abstract String getDesc();
    public abstract String getName();

    public abstract ArrayList<Attack> createAttacks(BattleProfile caster, BattleProfile target);

    // 해당 스킬이 공격할 수 있는 적들을 리턴해줌. (기본은 모든적들을 공격할 수 있는 것으로 함.)
    public ArrayList<BattleProfile> getAttackableTarget(ArrayList<BattleProfile> enemies){
        ArrayList<BattleProfile> result = new ArrayList<BattleProfile>();
        result.addAll(enemies);
        return result;
    }

    // getter, setter
    public float getGauge() {
        return gauge;
    }
    public void setGauge(float gauge) {
        this.gauge = gauge;
    }
    public void tickGauge(float skillSpeed) {
        this.gauge += skillSpeed * getCorrectionSpeed();    // 이 스킬의 보정값을 곱해서 더한다.
    }
    
    // 등급 열거형
    public enum Grade{
        essential, normal, ultimate;
    }

    // IAbility를 위한 함수들
    @Override
    public void applyStatus(Adventurer adventurer) {}
    public void advanceTick(Adventurer adventurer) {
        if(getGrade() == Grade.essential)
            tickGauge(adventurer.getTotalStatus().get(Status.Type.essentialSkillSpeed));
        else if(getGrade() == Grade.normal)
            tickGauge(adventurer.getTotalStatus().get(Status.Type.normalSkillSpeed));
        else if(getGrade() == Grade.ultimate)
            tickGauge(adventurer.getTotalStatus().get(Status.Type.ultimateSkillSpeed));
    }
}
