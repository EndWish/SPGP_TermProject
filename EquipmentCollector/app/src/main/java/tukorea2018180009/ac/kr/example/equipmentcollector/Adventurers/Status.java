package tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers;

public class Status {
    public enum Type{
        hp, hpm,
        essentialSkillSpeed, normalSkillSpeed, ultimateSkillSpeed,
        slashPower, piercePower, impactPower,
        magicPower, holyPower, cursedPower,
        slashDefense, pierceDefense, impactDefense,
        magicResistance, holyResistance, cursedResistance,
    }

    public float[] status = new float[Type.values().length];

    // 값을 초기화하는 함수
    public void set(float value){
        for(int i = 0; i < Type.values().length; ++i)
            this.status[i] = value;
    }
    public void set(Status other){
        for(int i = 0; i < Type.values().length; ++i)
            this.status[i] = other.status[i];
    }

    // 값을 더하는 함수
    public void add(Status other){
        for(int i = 0; i < Type.values().length; ++i)
            this.status[i] += other.status[i];
    }

    // 값을 빼는 함수
    public void sub(Status other){
        for(int i = 0; i < Type.values().length; ++i)
            this.status[i] -= other.status[i];
    }

    // 값을 곱하는 함수
    public void mul(Status other){
        for(int i = 0; i < Type.values().length; ++i)
            this.status[i] *= other.status[i];
    }

}
