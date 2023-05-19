package tukorea2018180009.ac.kr.example.equipmentcollector.StatusEffect;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Adventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Status;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI.BattleProfile;

public class BlessingStatusEffect extends StatusEffect{
    protected int lifeTick;
    protected float holyPower;
    protected Sprite effectSprite;

    public BlessingStatusEffect(BattleProfile target, float holyPower) {
        super(target);
        this.holyPower = holyPower;
        lifeTick = 100;

        effectSprite = new Sprite(new Sprite.Builder(R.mipmap.png_effect_skill_blessing2, 0,0,300,300)
                .setAnimation(5, 14, 1.5f, true)
                .setPivot(Sprite.PivotType.center));
        target.addChild(effectSprite);
    }

    @Override
    public void setDelete() {
        super.setDelete();
        effectSprite.setDelete();
        effectSprite = null;
    }

    @Override
    public void applyStatus(Adventurer adventurer) {
        super.applyStatus(adventurer);
        Status extraBasicStatus = adventurer.getExtraBasicStatus();
        extraBasicStatus.add(Status.Type.slashDefense, holyPower * 0.4f);
        extraBasicStatus.add(Status.Type.pierceDefense, holyPower * 0.4f);
        extraBasicStatus.add(Status.Type.impactDefense, holyPower * 0.4f);

        extraBasicStatus.add(Status.Type.magicResistance, holyPower * 0.6f);
        extraBasicStatus.add(Status.Type.holyResistance, holyPower * 0.6f);
        extraBasicStatus.add(Status.Type.cursedResistance, holyPower * 0.6f);
    }

    @Override
    public void advanceTick(Adventurer adventurer) {
        super.advanceTick(adventurer);
        lifeTick -= 1;

        if(lifeTick == 0 || lifeTick == 25 || lifeTick == 50 || lifeTick == 75){
            target.receiveHealing(holyPower / 4);
        }

        if(lifeTick <= 0){
            setDelete();
        }
    }
}
