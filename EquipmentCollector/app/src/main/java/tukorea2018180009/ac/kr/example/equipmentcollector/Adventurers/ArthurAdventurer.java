package tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers;

import android.graphics.Bitmap;

import tukorea2018180009.ac.kr.example.equipmentcollector.BitmapPool;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;

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
        basicStatus.set(Status.Type.hpm, 100);
        basicStatus.set(Status.Type.hp, basicStatus.get(Status.Type.hpm));

        basicStatus.set(Status.Type.essentialSkillSpeed, 2);
        basicStatus.set(Status.Type.normalSkillSpeed, 2);
        basicStatus.set(Status.Type.ultimateSkillSpeed, 2);

        basicStatus.set(Status.Type.slashPower, 100);
        basicStatus.set(Status.Type.piercePower, 50);
        basicStatus.set(Status.Type.impactPower, 30);

        basicStatus.set(Status.Type.magicPower, 0);
        basicStatus.set(Status.Type.holyPower, 0);
        basicStatus.set(Status.Type.cursedPower, 0);

        basicStatus.set(Status.Type.slashDefense, 100);
        basicStatus.set(Status.Type.pierceDefense, 80);
        basicStatus.set(Status.Type.impactDefense, 80);

        basicStatus.set(Status.Type.magicResistance, 50);
        basicStatus.set(Status.Type.holyResistance, 100);
        basicStatus.set(Status.Type.cursedResistance, 30);


    }
}