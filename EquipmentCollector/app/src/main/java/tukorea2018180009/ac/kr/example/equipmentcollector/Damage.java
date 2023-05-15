package tukorea2018180009.ac.kr.example.equipmentcollector;

import java.util.HashMap;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Status;

public class Damage {

    public float[] values = new float[Damage.Type.values().length];

    public Damage() {
        for(int i = 0; i < Damage.Type.values().length; ++i){
            values[i] = 0;
        }
    }

    public void setDamage(Type type, float value){
        values[type.ordinal()] = value;
    }
    public void addDamage(Type type, float value){
        values[type.ordinal()] += value;
    }
    public float getDamage(Type type){
        return values[type.ordinal()];
    }

    // 타입 정의
    public static enum Type{
        slash, pierce, impact,
        magic, holy, cursed;

        static public HashMap<Type, Status.Type> convertStatusDefType;
        static {
            convertStatusDefType.put(slash, Status.Type.slashDefense);
            convertStatusDefType.put(pierce, Status.Type.pierceDefense);
            convertStatusDefType.put(impact, Status.Type.impactDefense);
            convertStatusDefType.put(magic, Status.Type.magicResistance);
            convertStatusDefType.put(holy, Status.Type.holyResistance);
            convertStatusDefType.put(cursed, Status.Type.cursedResistance);
        }

        static public HashMap<Type, Status.Type> convertStatusPowerType;
        static {
            convertStatusPowerType.put(slash, Status.Type.slashPower);
            convertStatusPowerType.put(pierce, Status.Type.piercePower);
            convertStatusPowerType.put(impact, Status.Type.impactPower);
            convertStatusPowerType.put(magic, Status.Type.magicPower);
            convertStatusPowerType.put(holy, Status.Type.holyPower);
            convertStatusPowerType.put(cursed, Status.Type.cursedPower);
        }
    }
}
