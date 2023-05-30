package tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers;

import android.graphics.Bitmap;

import java.util.ArrayList;

import tukorea2018180009.ac.kr.example.equipmentcollector.Equipment.Equipment;
import tukorea2018180009.ac.kr.example.equipmentcollector.Memory.BitmapPool;
import tukorea2018180009.ac.kr.example.equipmentcollector.Equipment.PlainSwordEquipment;
import tukorea2018180009.ac.kr.example.equipmentcollector.Object;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Skills.RushSkill;
import tukorea2018180009.ac.kr.example.equipmentcollector.Skills.SlashSkill;

public class ArthurAdventurer extends Adventurer{
    private static final String name = "Arthur";
    private static final Bitmap icon = BitmapPool.get(R.mipmap.png_icon_adventurer_arthur_256x256);
    private static final Bitmap profile = BitmapPool.get(R.mipmap.png_profile_adventurer_arthur_256x384);

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
        basicStatus.set(Status.Type.hpm, 30);

        basicStatus.set(Status.Type.essentialSkillSpeed, 2);
        basicStatus.set(Status.Type.normalSkillSpeed, 2);
        basicStatus.set(Status.Type.ultimateSkillSpeed, 2);

        basicStatus.set(Status.Type.slashPower, 10);
        basicStatus.set(Status.Type.piercePower, 5);
        basicStatus.set(Status.Type.impactPower, 3);

        basicStatus.set(Status.Type.magicPower, 0);
        basicStatus.set(Status.Type.holyPower, 0);
        basicStatus.set(Status.Type.cursedPower, 0);

        basicStatus.set(Status.Type.slashDefense, 10);
        basicStatus.set(Status.Type.pierceDefense, 8);
        basicStatus.set(Status.Type.impactDefense, 8);

        basicStatus.set(Status.Type.magicResistance, 5);
        basicStatus.set(Status.Type.holyResistance, 10);
        basicStatus.set(Status.Type.cursedResistance, 3);


    }

    @Override
    protected void initSkills() {
        skills.add(new SlashSkill());
        skills.add(new RushSkill());
    }

    @Override
    public ArrayList<Equipment> getRewardEquipments() {
        ArrayList<Equipment> rewards = new ArrayList<Equipment>();
        rewards.add(new PlainSwordEquipment());
        return rewards;
    }
    @Override
    public int getRewardGold() {
        return 50;
    }

    @Override
    protected void initForTest() {
        super.initForTest();
//        for(int i = 0; i < 8; ++i)
//            addEquipment(new PlainSwordEquipment());
    }
}
