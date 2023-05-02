package tukorea2018180009.ac.kr.example.equipmentcollector.Skills;

import android.graphics.Bitmap;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Adventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Status;
import tukorea2018180009.ac.kr.example.equipmentcollector.IAbility;
import tukorea2018180009.ac.kr.example.equipmentcollector.IIcon;

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
