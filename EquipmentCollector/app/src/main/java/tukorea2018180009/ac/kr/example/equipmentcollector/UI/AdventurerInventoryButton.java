package tukorea2018180009.ac.kr.example.equipmentcollector.UI;

import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Scenes.BaseScene;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;

public class AdventurerInventoryButton extends SpriteButton {
    private static final String TAG = AdventurerInventoryButton.class.getSimpleName();

    public AdventurerInventoryButton(float cx, float cy){
        super(new Sprite
                .Builder(R.mipmap.png_button_inventory_adventurer, cx, cy, 2, 2)
                .setPivotCenter());
    }

    @Override
    public void clickDown() {
        super.clickDown();
        BaseScene.getTopScene().add(new AdventurerInventory());
    }

}
