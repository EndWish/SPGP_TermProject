package tukorea2018180009.ac.kr.example.equipmentcollector.Monster;

import android.graphics.Bitmap;

import java.util.ArrayList;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Adventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Status;
import tukorea2018180009.ac.kr.example.equipmentcollector.Equipment.Equipment;
import tukorea2018180009.ac.kr.example.equipmentcollector.Equipment.PlainSwordEquipment;
import tukorea2018180009.ac.kr.example.equipmentcollector.Equipment.SkullWandEquipment;
import tukorea2018180009.ac.kr.example.equipmentcollector.Memory.BitmapPool;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Skills.AnotherBiteSkill;
import tukorea2018180009.ac.kr.example.equipmentcollector.Skills.CreepingToxinSkill;

public class BabyHydraMonster extends Adventurer {
    private static final String name = "BabyHydra";
    private static final Bitmap icon = BitmapPool.get(R.mipmap.png_icon_adventurer_arthur_256x256);
    private static final Bitmap profile = BitmapPool.get(R.mipmap.png_profile_monster_baby_hydra_256x384);

    @Override
    public String getName() {
        return name;
    }
    @Override
    public Bitmap getIcon() {
        return icon;
    }
    @Override
    public Bitmap getProfile() {
        return profile;
    }

    @Override
    protected void initBasicStatus() {
        basicStatus.set(Status.Type.hpm, 15);

        basicStatus.set(Status.Type.essentialSkillSpeed, 2);
        basicStatus.set(Status.Type.normalSkillSpeed, 2);
        basicStatus.set(Status.Type.ultimateSkillSpeed, 2);

        basicStatus.set(Status.Type.slashPower, 0);
        basicStatus.set(Status.Type.piercePower, 8);
        basicStatus.set(Status.Type.impactPower, 0);

        basicStatus.set(Status.Type.magicPower, 0);
        basicStatus.set(Status.Type.holyPower, 0);
        basicStatus.set(Status.Type.cursedPower, 0);

        basicStatus.set(Status.Type.slashDefense, 6);
        basicStatus.set(Status.Type.pierceDefense, 5);
        basicStatus.set(Status.Type.impactDefense, 5);

        basicStatus.set(Status.Type.magicResistance, 5);
        basicStatus.set(Status.Type.holyResistance, 5);
        basicStatus.set(Status.Type.cursedResistance, 5);


    }

    @Override
    protected void initSkills() {
        skills.add(new AnotherBiteSkill());
    }

    @Override
    public ArrayList<Equipment> getRewardEquipments() {
        ArrayList<Equipment> rewards = new ArrayList<Equipment>();
        rewards.add(new SkullWandEquipment());
        return rewards;
    }
    @Override
    public int getRewardGold() {
        return 50;
    }

}
