package tukorea2018180009.ac.kr.example.equipmentcollector.UI;

import tukorea2018180009.ac.kr.example.equipmentcollector.Scenes.BaseScene;

public class TriggerButton extends SpriteButton {
    protected boolean trigger = false;

    public TriggerButton(Builder builder) {
        super(builder);
    }

    @Override
    public void clickDown() {
        super.clickDown();
        trigger = true;
    }

    public boolean getTrigger(){
        if(trigger){
            trigger = false;
            return true;
        }
        return false;
    }
}
