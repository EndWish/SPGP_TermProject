package tukorea2018180009.ac.kr.example.equipmentcollector.UI;

import android.graphics.Color;
import android.graphics.Paint;

import tukorea2018180009.ac.kr.example.equipmentcollector.Equipment.Equipment;
import tukorea2018180009.ac.kr.example.equipmentcollector.Scenes.BaseScene;
import tukorea2018180009.ac.kr.example.equipmentcollector.Skills.Skill;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.Text;

public class EquipmentButton extends SpriteButton {
    Equipment equipment;
    Text childText;

    // 생성자
    public EquipmentButton(Equipment equipment, float cx, float cy, float width) {
        super(new Builder(equipment == null ? null : equipment.getIcon(), cx, cy, width, width));
        this.equipment = equipment;
        childText = new Text(width, 0, equipment == null ? "" : "+" + equipment.getUpgradeLevel(), width / 3, width, Color.YELLOW, Paint.Align.RIGHT);
        addChild(childText);
    }

    // 소멸자
    @Override
    public void setDelete() {
        equipment = null;
        super.setDelete();
    }

    @Override
    public void clickDown() {
        super.clickDown();
        if(equipment != null)
            BaseScene.getTopScene().add(new EquipmentInfoUI(equipment));
    }

    // getter, setter
    public void setEquipment(Equipment equipment){
        this.equipment = equipment;
        setBitmap(equipment == null ? null : equipment.getIcon());
        childText.setText(equipment == null ? "" : "+" + equipment.getUpgradeLevel());
    }

}
