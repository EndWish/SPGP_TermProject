package tukorea2018180009.ac.kr.example.equipmentcollector.UI;

import tukorea2018180009.ac.kr.example.equipmentcollector.Equipment.Equipment;
import tukorea2018180009.ac.kr.example.equipmentcollector.Scenes.BaseScene;
import tukorea2018180009.ac.kr.example.equipmentcollector.Skills.Skill;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;

public class EquipmentButton extends SpriteButton {
    Equipment equipment;

    // 생성자
    public EquipmentButton(Equipment equipment, float cx, float cy, float width) {
        super(new Builder(equipment == null ? null : equipment.getIcon(), cx, cy, width, width));
        this.equipment = equipment;
    }

    // 소멸자
    @Override
    public void setDelete() {
        equipment = null;
        super.setDelete();
    }

    // getter, setter
    public void setEquipment(Equipment equipment){
        this.equipment = equipment;
        setBitmap(equipment == null ? null : equipment.getIcon());
    }

}
