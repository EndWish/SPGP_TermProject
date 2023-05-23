package tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers;

import android.graphics.Bitmap;

import java.util.ArrayList;

import tukorea2018180009.ac.kr.example.equipmentcollector.Equipment.Equipment;
import tukorea2018180009.ac.kr.example.equipmentcollector.Equipment.PlainSwordEquipment;
import tukorea2018180009.ac.kr.example.equipmentcollector.Memory.BitmapPool;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Skills.ArrowBlasterSkill;
import tukorea2018180009.ac.kr.example.equipmentcollector.Skills.EnchantedQuiverSkill;
import tukorea2018180009.ac.kr.example.equipmentcollector.Skills.RushSkill;
import tukorea2018180009.ac.kr.example.equipmentcollector.Skills.SlashSkill;

public class ShamarAdventurer extends Adventurer{
    private static final String name = "Shamar";
    private static final Bitmap icon = BitmapPool.get(R.mipmap.png_icon_adventurer_shamar_256x256);
    private static final Bitmap profile = BitmapPool.get(R.mipmap.png_profile_adventurer_shamar_256x384);

    int quiver = 0;

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
        basicStatus.set(Status.Type.hpm, 100);

        basicStatus.set(Status.Type.essentialSkillSpeed, 2);
        basicStatus.set(Status.Type.normalSkillSpeed, 2);
        basicStatus.set(Status.Type.ultimateSkillSpeed, 2);

        basicStatus.set(Status.Type.slashPower, 5);
        basicStatus.set(Status.Type.piercePower, 10);
        basicStatus.set(Status.Type.impactPower, 0);

        basicStatus.set(Status.Type.magicPower, 0);
        basicStatus.set(Status.Type.holyPower, 0);
        basicStatus.set(Status.Type.cursedPower, 0);

        basicStatus.set(Status.Type.slashDefense, 4);
        basicStatus.set(Status.Type.pierceDefense, 5);
        basicStatus.set(Status.Type.impactDefense, 5);

        basicStatus.set(Status.Type.magicResistance, 8);
        basicStatus.set(Status.Type.holyResistance, 10);
        basicStatus.set(Status.Type.cursedResistance, 8);


    }

    @Override
    protected void initSkills() {
        skills.add(new EnchantedQuiverSkill());
        skills.add(new ArrowBlasterSkill());
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
    public void initForBattle() {
        super.initForBattle();
        setQuiver(0);
    }

    @Override
    protected void initForTest() {
        super.initForTest();
//        for(int i = 0; i < 8; ++i)
//            addEquipment(new PlainSwordEquipment());
    }

    // getter, setter
    public int getQuiver() {
        return quiver;
    }
    public void addQuiver(int add) {
        quiver += add;
    }
    public void setQuiver(int quiver) {
        this.quiver = quiver;
    }

}
