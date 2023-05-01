package tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers;

import tukorea2018180009.ac.kr.example.equipmentcollector.R;

public class Status {
    public static enum Type{
        hpm,
        essentialSkillSpeed, normalSkillSpeed, ultimateSkillSpeed,
        slashPower, piercePower, impactPower,
        magicPower, holyPower, cursedPower,
        slashDefense, pierceDefense, impactDefense,
        magicResistance, holyResistance, cursedResistance,
    }
    public static final int[] image = new int[] {
        R.mipmap.png_icon_hp,
        R.mipmap.png_icon_essential_skill_speed, R.mipmap.png_icon_normal_skill_speed, R.mipmap.png_icon_ultimate_skill_speed,
        R.mipmap.png_icon_slash, R.mipmap.png_icon_pierce, R.mipmap.png_icon_impact,
        R.mipmap.png_icon_magic, R.mipmap.png_icon_holy, R.mipmap.png_icon_curse,
        R.mipmap.png_icon_slash_def, R.mipmap.png_icon_pierce_def, R.mipmap.png_icon_impact_def,
        R.mipmap.png_icon_magic_def, R.mipmap.png_icon_holy_def, R.mipmap.png_icon_curse_def,
    };

    public float[] status = new float[Type.values().length];

    // 값을 초기화하는 함수
    public void set(Type type, float value){
        this.status[type.ordinal()] = value;
    }
    public void set(float value){
        for(int i = 0; i < Type.values().length; ++i)
            this.status[i] = value;
    }
    public void set(Status other){
        for(int i = 0; i < Type.values().length; ++i)
            this.status[i] = other.status[i];
    }

    public float get(Type type){
        return this.status[type.ordinal()];
    }

    // 값을 더하는 함수
    public void add(Status other){
        for(int i = 0; i < Type.values().length; ++i)
            this.status[i] += other.status[i];
    }
    public void add(Type type, float value){
        this.status[type.ordinal()] += value;
    }

    // 값을 빼는 함수
    public void sub(Status other){
        for(int i = 0; i < Type.values().length; ++i)
            this.status[i] -= other.status[i];
    }
    public void sub(Type type, float value){
        this.status[type.ordinal()] -= value;
    }

    // 값을 곱하는 함수
    public void mul(Status other){
        for(int i = 0; i < Type.values().length; ++i)
            this.status[i] *= other.status[i];
    }
    public void mul(Type type, float value){
        this.status[type.ordinal()] *= value;
    }

}
