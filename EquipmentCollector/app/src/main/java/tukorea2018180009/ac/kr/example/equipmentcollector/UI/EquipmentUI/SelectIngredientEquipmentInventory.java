package tukorea2018180009.ac.kr.example.equipmentcollector.UI.EquipmentUI;

import java.util.ArrayList;

import tukorea2018180009.ac.kr.example.equipmentcollector.Equipment.Equipment;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Scenes.BaseScene;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.SelectInventory;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.TriggerButton;
import tukorea2018180009.ac.kr.example.equipmentcollector.UserInfo;

public class SelectIngredientEquipmentInventory extends Sprite {
    SelectInventory<Equipment> selectInventory;
    Equipment upgradeEquipment;
    TriggerButton closeButton;
    ArrayList<Equipment> ingredients;

    public SelectIngredientEquipmentInventory(Equipment upgradeEquipment) {
        super(new Sprite.Builder(R.mipmap.png_black_white_frame_512x256, 20, 20, 1560, 860));

        // 버튼 레이어를 하나 상승시킨다.
        BaseScene.getTopScene().addButtonLayer();

        this.upgradeEquipment = upgradeEquipment;

        // 재료로 사용할 수 있는 장비들의 리스트를 뽑아낸다.
        ingredients = new ArrayList<>();
        for (Equipment equipment : UserInfo.getInstance().getEquipments()) {
            if (upgradeEquipment.getName() == equipment.getName() && upgradeEquipment != equipment) {
                ingredients.add(equipment);
            }
        }
        selectInventory  = new SelectInventory<Equipment>(ingredients,
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
        upgradeEquipment = null;
        closeButton = null;
    }

    @Override
    public void update(float deltaTime) {
        if(upgradeEquipment == null || upgradeEquipment.isDeleted()){
            setDelete();
            return;
        }
        super.update(deltaTime);

        // 화면 갱신
        updatePage();
        
        // 재료로 사용할 장비를 선택했을 경우
        if(selectInventory.getSelectedIcon() != null){
            BaseScene.getTopScene().addPost(new EquipmentUpgradeQueryUI(upgradeEquipment, selectInventory.getSelectedIcon()));
            selectInventory.select(null);
        }

        // 닫기 버튼을 눌렀을 경우
        if(closeButton.getTrigger())
            setDelete();
    }

    protected void updatePage() {
        // 재료로 사용할 수 있는 장비들의 리스트를 뽑아낸다.
        ingredients.clear();
        for (Equipment equipment : UserInfo.getInstance().getEquipments()) {
            if (equipment != null && !equipment.isDeleted() && upgradeEquipment.getName() == equipment.getName() && upgradeEquipment != equipment) {
                ingredients.add(equipment);
            }
        }
        selectInventory.setIcons(ingredients);
    }


}
