package tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers;

import static java.lang.Math.max;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;

import tukorea2018180009.ac.kr.example.equipmentcollector.Damage;
import tukorea2018180009.ac.kr.example.equipmentcollector.Equipment.Equipment;
import tukorea2018180009.ac.kr.example.equipmentcollector.GameObject;
import tukorea2018180009.ac.kr.example.equipmentcollector.IIcon;
import tukorea2018180009.ac.kr.example.equipmentcollector.Object;
import tukorea2018180009.ac.kr.example.equipmentcollector.Skills.Skill;
import tukorea2018180009.ac.kr.example.equipmentcollector.StatusEffect.StatusEffect;

public abstract class Adventurer extends Object implements IIcon {

    private static final String TAG = Adventurer.class.getSimpleName();
    protected float hp;
    protected Status basicStatus = new Status();
    protected Status extraBasicStatus = new Status();
    protected Status coefficientStatus = new Status();
    protected Status totalStatus = new Status();

    protected ArrayList<Skill> skills = new ArrayList<>();
    protected ArrayList<Equipment> equipments = new ArrayList<>();
    protected ArrayList<StatusEffect> statusEffects = new ArrayList<>();
    protected ArrayList<StatusEffect> postStatusEffects = new ArrayList<>();

    // 생성자
    public Adventurer() {
        super();
        initBasicStatus();
        initSkills();
        initForTest();
    }

    // 소멸자
    @Override
    public void setDelete() {
        super.setDelete();
        equipments.forEach(equipment -> equipment.setDelete());
        equipments = null;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        equipments.removeIf(equipment -> equipment.isDeleted());
    }

    public abstract String getName();
    public abstract Bitmap getIcon();
    public abstract Bitmap getProfile();


    protected void initForTest() {}
    protected abstract void initBasicStatus();
    protected abstract void initSkills();

    public void addEquipment(Equipment equipment) {
        equipments.add(equipment);
        equipment.setWearer(this);
    }



    public void initForBattle(){
        // 능력치 초기화
        applyStatus();

        // 스킬 게이지를 0으로 초기화
        for (Skill skill :skills){
            skill.setGauge(50);
        }

        // 버프/디버프를 초기화한다.
        statusEffects.clear();

        // hp를 최대 체력으로 채워 놓는다.
        hp = totalStatus.get(Status.Type.hpm);
    }

    public void advanceTick() {
        for (Equipment equipment :equipments)
            equipment.advanceTick(this);
        for (Skill skill :skills)
            skill.advanceTick(this);
        for (StatusEffect statusEffect :statusEffects)
            statusEffect.advanceTick(this);
        statusEffectUpdateInsertAndRemove();

        applyStatus();
    }
    public void applyStatus() {
        // 추가 능력치들을 초기화 한다.
        extraBasicStatus.set(0);
        coefficientStatus.set(1f);

        // 장비와 스킬의 배틀 최기화 함수를 부른다. + 스킬 게이지를 0으로 초기화
        for (Equipment equipment :equipments)
            equipment.applyStatus(this);
        for (Skill skill :skills)
            skill.applyStatus(this);
        for (StatusEffect statusEffect :statusEffects)
            statusEffect.applyStatus(this);
        statusEffectUpdateInsertAndRemove();

        calculateTotalStatus();
    }
    public void calculateTotalStatus(){
        totalStatus.set(0);
        totalStatus.add(basicStatus);
        totalStatus.add(extraBasicStatus);
        totalStatus.mul(coefficientStatus);
    }

    public void addStatusEffects(ArrayList<StatusEffect> addStatusEffects) {
        if(addStatusEffects != null){
            for(StatusEffect statusEffect : addStatusEffects)
                addStatusEffect(statusEffect);
        }
    }
    public void addStatusEffect(StatusEffect addStatusEffect) {
        if(addStatusEffect != null)
            statusEffects.add(addStatusEffect);
    }

    public float getMaxSkillGauge() {
        float maxGauge = 0;
        for (Skill skill :skills){
            maxGauge = max(maxGauge, skill.getGauge());
        }
        return maxGauge;
    }
    public Skill getMaxGaugedSkill(){
        Skill result = null;
        for (Skill skill :skills){
            if(result == null || result.getGauge() < skill.getGauge()){
                result = skill;
            }
        }
        return result;
    }

    protected void statusEffectUpdateInsertAndRemove(){ // 버프/디버프가 삭제되서 배열에서 제거하는 것과 추가하는 것을 진행해주는 함수.
        statusEffects.removeIf(statusEffect -> statusEffect.isDeleted());   // 삭제된 버프/디버프를 제거한다.
        statusEffects.addAll(postStatusEffects);
        postStatusEffects.clear();
    }

    abstract public ArrayList<Equipment> getRewardEquipments();
    abstract public int getRewardGold();

    //public void UseMaximumGaugeSkill(targets);

    //public void ResetExtraBasicStats();
    //public void ResetCoefficientBasicStats();
    //public void ResetTotalStats();
    //public void UpdateStats();

    // getter setter
    public Status getBasicStatus() {
        return basicStatus;
    }
    public Status getExtraBasicStatus() {
        return extraBasicStatus;
    }
    public Status getCoefficientStatus() {
        return coefficientStatus;
    }
    public Status getTotalStatus() {
        return totalStatus;
    }
    public ArrayList<Skill> getSkills() {
        return skills;
    }
    public ArrayList<Equipment> getEquipments() {
        return equipments;
    }
    public float getHp() {
        return hp;
    }
    public void setHp(float hp) {
        this.hp = hp;
    }
    public void addHp(float addHp) {
        float hpm = getTotalStatus().get(Status.Type.hpm);
        this.hp += addHp;
        if(hpm < this.hp){
            this.hp = hpm;
        }
    }
}
