package tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI;

import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

import tukorea2018180009.ac.kr.example.equipmentcollector.Equipment.Equipment;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Scenes.BaseScene;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.EquipmentUI.EquipmentInfoUI;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.SelectInventory;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.Text.Text;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.TriggerButton;
import tukorea2018180009.ac.kr.example.equipmentcollector.UserInfo;

public class ClearRewardsCheckUI extends Sprite {

    protected Text goldText;
    protected TriggerButton closeButton;
    protected SelectInventory<Equipment> rewardsInventory;

    public ClearRewardsCheckUI(ArrayList<Equipment> rewardsEquipments, int rewardsGold) {
        super(new Sprite.Builder(R.mipmap.png_black_white_frame_512x256, 20, 20, 1560, 860));
        // 버튼 레이어를 하나 상승시킨다.
        BaseScene.getTopScene().addButtonLayer();

        // 장비 리스트들을 출력하는 inventory를 생성한다.
        rewardsInventory  = new SelectInventory<Equipment>(rewardsEquipments,
                50f, 150f, 1460, 450, 7, 100);
        addChild(rewardsInventory);

        // 획득 골드량을 표시해주는 텍스트 생성.
        goldText = new Text(width / 2f,50, "Gold : +" + rewardsGold, 100, 1550, Color.BLACK, Paint.Align.CENTER);
        addChild(goldText);

        // 닫기 버튼 생성
        closeButton = new TriggerButton(new Builder(R.mipmap.png_button_ok, width / 2f, height - 85, 300, 125)
                .setPivot(PivotType.center));
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
        super.update(deltaTime);

        // 장비를 선택했을 경우
        if(rewardsInventory.getSelectedIcon() != null){
            BaseScene.getTopScene().addPost(new EquipmentInfoUI(rewardsInventory.getSelectedIcon()));
            rewardsInventory.select(null);
        }

        // 닫기 버튼을 눌렀을 경우
        if(closeButton.getTrigger())
            setDelete();
    }
}
