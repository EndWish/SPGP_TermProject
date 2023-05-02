package tukorea2018180009.ac.kr.example.equipmentcollector.UI.EquipmentUI;

import android.graphics.Color;
import android.graphics.Paint;

import tukorea2018180009.ac.kr.example.equipmentcollector.Equipment.Equipment;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Scenes.BaseScene;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.Text;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.TriggerButton;

public class EquipmentInfoUI  extends Sprite {
    Equipment equipment;

    protected Sprite icon;
    protected Text nameText, desc, upgradeLevelText;
    protected TriggerButton upgradeButton, sellButton;
    protected TriggerButton closeButton;

    // 생성자
    public EquipmentInfoUI(Equipment equipment) {
        super(new Sprite.Builder(R.mipmap.png_black_white_frame_512x256, 25, 25, 1550, 850));
        // 버튼 레이어를 하나 상승시킨다.
        BaseScene.getTopScene().addButtonLayer();

        this.equipment = equipment;

        // 프로필 사진 초기화
        icon = new Sprite(new Builder(equipment.getIcon(), 25, 25, 300, 300));
        addChild(icon);

        // 강화수치 텍스트
        upgradeLevelText = new Text(325f, 25f, "+" + equipment.getUpgradeLevel(), 100f, 300f, Color.YELLOW, Paint.Align.RIGHT);
        addChild(upgradeLevelText);

        // 이름 텍스트
        nameText = new Text(162.5f, 350f, "< " + equipment.getName() + " >", 50f, 500f, Color.BLACK, Paint.Align.CENTER);
        addChild(nameText);

        // 설명 텍스트
        desc = new Text(350f, 50f, equipment.getDesc(), 50f, 1150f, Color.BLACK, Paint.Align.LEFT);
        addChild(desc);

        // 강화 버튼 & 판매 버튼
        upgradeButton = new TriggerButton(new Builder(R.mipmap.png_button_upgrade, 25, 425f, 300 ,150));
        addChild(upgradeButton);
        sellButton = new TriggerButton(new Builder(R.mipmap.png_button_sell, 25, 600f, 300 ,150));
        addChild(sellButton);

        // 닫기 버튼 생성
        closeButton = new TriggerButton(new Builder(R.mipmap.png_button_x, width, 0, 50, 50)
                .setPivot(PivotType.rightTop));
        addChild(closeButton);

    }

    // 소멸자
    @Override
    public void setDelete() {
        BaseScene.getTopScene().subButtonLayer();

        //closeButton = null;
        super.setDelete();
    }

    @Override
    public void update(float deltaTime) {
        if(equipment == null || equipment.isDeleted())
            setDelete();

        super.update(deltaTime);

        updatePage();

        if(upgradeButton.getTrigger())
            BaseScene.getTopScene().add(new SelectIngredientEquipmentInventory(equipment));

        // 닫기 버튼을 눌렀을 경우
        if(closeButton.getTrigger())
            setDelete();
    }

    protected void updatePage() {
        desc.setText(equipment.getDesc());
        upgradeLevelText.setText("+" + equipment.getUpgradeLevel());
    }


}
