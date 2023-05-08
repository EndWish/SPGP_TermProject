package tukorea2018180009.ac.kr.example.equipmentcollector.UI.AdventurerUI;

import java.util.ArrayList;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Adventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.Equipment.Equipment;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Scenes.BaseScene;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.EquipmentUI.EquipmentEquipQueryUI;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.EquipmentUI.EquipmentUpgradeQueryUI;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.SelectInventory;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.TriggerButton;
import tukorea2018180009.ac.kr.example.equipmentcollector.UserInfo;

public class SelectWearerAdventurerInventory  extends Sprite {
    SelectInventory<Adventurer> selectInventory;
    Equipment equipment;
    TriggerButton closeButton;

    public SelectWearerAdventurerInventory(Equipment equipment) {
        super(new Sprite.Builder(R.mipmap.png_black_white_frame_512x256, 20, 20, 1560, 860));

        // 버튼 레이어를 하나 상승시킨다.
        BaseScene.getTopScene().addButtonLayer();

        this.equipment = equipment;

        // 착용할 모험가들의 인벤토리를 생성
        selectInventory  = new SelectInventory<Adventurer>(UserInfo.getInstance().getAdventurers(),
                50f, 50f, 1460, 650, 7, 100);
        addChild(selectInventory);

        // 닫기 버튼 생성
        closeButton = new TriggerButton(new Builder(R.mipmap.png_button_x, width, 0, 50, 50)
                .setPivot(PivotType.rightTop));
        addChild(closeButton);
    }

    // 소멸자
    @Override
    public void setDelete() {
        super.setDelete();
        BaseScene.getTopScene().subButtonLayer();
        selectInventory = null;
        equipment = null;
        closeButton = null;
    }

    @Override
    public void update(float deltaTime) {
        // 장비가 파괴되서 없어지거나 장비착용에 성공할 경우 이 창을 삭제한다.
        if(equipment == null || equipment.isDeleted() || equipment.getWearer() != null){
            setDelete();
            return;
        }
        super.update(deltaTime);

        // 장착할 모험가를 선택했을 경우
        if(selectInventory.getSelectedIcon() != null){
            BaseScene.getTopScene().add(new EquipmentEquipQueryUI(equipment, selectInventory.getSelectedIcon()));
            selectInventory.select(null);
        }

        // 닫기 버튼을 눌렀을 경우
        if(closeButton.getTrigger())
            setDelete();
    }

}
