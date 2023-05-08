package tukorea2018180009.ac.kr.example.equipmentcollector.UI.EquipmentUI;

import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Scenes.BaseScene;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.AdventurerUI.AdventurerInventory;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.SpriteButton;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.TriggerButton;

public class EquipmentInventoryButton extends SpriteButton {

    public EquipmentInventoryButton(float cx, float cy) {
        super(new Sprite
                .Builder(R.mipmap.png_button_inventory_equipment, cx, cy, 200, 200)
                .setPivot(PivotType.center));
    }

    @Override
    public void clickDown() {
        super.clickDown();
        BaseScene.getTopScene().add(new EquipmentInventory());
    }

}
