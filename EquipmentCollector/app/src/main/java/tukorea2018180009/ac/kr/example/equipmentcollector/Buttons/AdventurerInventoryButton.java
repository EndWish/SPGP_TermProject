package tukorea2018180009.ac.kr.example.equipmentcollector.Buttons;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Adventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Scenes.BaseScene;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.UserInfo;

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
        // 용사 리스트들을 출력하는 inventory를 생성한다.
        SelectInventory<Adventurer> newInventory  = new SelectInventory<Adventurer>(UserInfo.getInstance().getAdventurers(), 1, 1, 14, 7, 5);
        BaseScene.getTopScene().add(newInventory);
    }

}
