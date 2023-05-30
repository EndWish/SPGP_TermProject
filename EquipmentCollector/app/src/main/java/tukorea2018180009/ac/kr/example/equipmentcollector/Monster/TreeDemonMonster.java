package tukorea2018180009.ac.kr.example.equipmentcollector.Monster;

import android.graphics.Bitmap;

import java.util.ArrayList;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Adventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Status;
import tukorea2018180009.ac.kr.example.equipmentcollector.Equipment.BeltEquipment;
import tukorea2018180009.ac.kr.example.equipmentcollector.Equipment.Equipment;
import tukorea2018180009.ac.kr.example.equipmentcollector.Equipment.PlainSwordEquipment;
import tukorea2018180009.ac.kr.example.equipmentcollector.Memory.BitmapPool;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Skills.BlessingSkill;
import tukorea2018180009.ac.kr.example.equipmentcollector.Skills.FlameVortexSkill;
import tukorea2018180009.ac.kr.example.equipmentcollector.Skills.RushSkill;

public class TreeDemonMonster extends Adventurer {
    private static final String name = "TreeDemon";
    private static final Bitmap icon = BitmapPool.get(R.mipmap.png_icon_adventurer_arthur_256x256);
    private static final Bitmap profile = BitmapPool.get(R.mipmap.png_profile_monster_tree_demon_256x384);

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
        basicStatus.set(Status.Type.hpm, 45);

        basicStatus.set(Status.Type.essentialSkillSpeed, 2);
        basicStatus.set(Status.Type.normalSkillSpeed, 2);
        basicStatus.set(Status.Type.ultimateSkillSpeed, 2);

        basicStatus.set(Status.Type.slashPower, 5);
        basicStatus.set(Status.Type.piercePower, 10);
        basicStatus.set(Status.Type.impactPower, 5);

        basicStatus.set(Status.Type.magicPower, 0);
        basicStatus.set(Status.Type.holyPower, 0);
        basicStatus.set(Status.Type.cursedPower, 0);

        basicStatus.set(Status.Type.slashDefense, 10);
        basicStatus.set(Status.Type.pierceDefense, 20);
        basicStatus.set(Status.Type.impactDefense, 18);

        basicStatus.set(Status.Type.magicResistance, 8);
        basicStatus.set(Status.Type.holyResistance, 5);
        basicStatus.set(Status.Type.cursedResistance, 16);


    }

    @Override
    protected void initSkills() {
        skills.add(new RushSkill());
        skills.add(new RushSkill());
        skills.add(new RushSkill());
    }

    @Override
    public ArrayList<Equipment> getRewardEquipments() {
        ArrayList<Equipment> rewards = new ArrayList<Equipment>();
        rewards.add(new BeltEquipment());
        return rewards;
    }
    @Override
    public int getRewardGold() {
        return 100;
    }

}
