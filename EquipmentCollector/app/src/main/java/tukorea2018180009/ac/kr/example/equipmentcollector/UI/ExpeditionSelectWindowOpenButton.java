package tukorea2018180009.ac.kr.example.equipmentcollector.UI;

import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Scenes.BaseScene;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.AdventurerUI.AdventurerInventory;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.AdventurerUI.AdventurerInventoryButton;

public class ExpeditionSelectWindowOpenButton extends SpriteButton {

    private static final String TAG = AdventurerInventoryButton.class.getSimpleName();

    public ExpeditionSelectWindowOpenButton(float cx, float cy){
        super(new Sprite
                .Builder(R.mipmap.png_button_open_select_expedition, cx, cy, 200, 200)
                .setPivotCenter());
    }

    @Override
    public void clickDown() {
        super.clickDown();
        //BaseScene.getTopScene().add(new ExpeditionSelectWindow());
    }

}
