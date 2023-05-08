package tukorea2018180009.ac.kr.example.equipmentcollector.UI.EquipmentUI;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Adventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.Equipment.Equipment;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Scenes.BaseScene;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.AdventurerUI.AdventurerInfoUI;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.AdventurerUI.PartySettingBarUI;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.SelectInventory;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.TriggerButton;
import tukorea2018180009.ac.kr.example.equipmentcollector.UserInfo;

public class EquipmentInventory extends Sprite {
    SelectInventory<Equipment> selectInventory;
    TriggerButton closeButton;

    public EquipmentInventory() {
        super(new Sprite.Builder(R.mipmap.png_black_white_frame_512x256, 20, 20, 1560, 860));

        // 버튼 레이어를 하나 상승시킨다.
        BaseScene.getTopScene().addButtonLayer();

        // 장비 리스트들을 출력하는 inventory를 생성한다.
        selectInventory  = new SelectInventory<Equipment>(UserInfo.getInstance().getEquipments(),
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
        // 버튼 레이어를 감소시킨다.
        BaseScene.getTopScene().subButtonLayer();
        selectInventory = null;
        closeButton = null;
        super.setDelete();
    }

    // update
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        // 장비를 선택했을 경우
        if(selectInventory.getSelectedIcon() != null){
            BaseScene.getTopScene().addPost(new EquipmentInfoUI(selectInventory.getSelectedIcon()));
            selectInventory.select(null);
        }

        // 닫기 버튼을 눌렀을 경우
        if(closeButton.getTrigger())
            setDelete();
    }

}
