package tukorea2018180009.ac.kr.example.equipmentcollector.UI.ExpeditionUI;

import android.util.Log;

import java.util.ArrayList;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Adventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.Equipment.Equipment;
import tukorea2018180009.ac.kr.example.equipmentcollector.ExpenditionAreaInfo.ExpeditionAreaInfo;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Scenes.BaseScene;
import tukorea2018180009.ac.kr.example.equipmentcollector.Scenes.BattleScene;
import tukorea2018180009.ac.kr.example.equipmentcollector.Scenes.VillageScene;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.AdventurerUI.AdventurerInfoUI;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.AdventurerUI.PartySettingBarUI;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.SelectInventory;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.TriggerButton;
import tukorea2018180009.ac.kr.example.equipmentcollector.UserInfo;

public class ExpeditionSelectWindow extends Sprite {
    TriggerButton closeButton;
    PartySettingBarUI partySettingBarUI;
    SelectInventory<ExpeditionAreaInfo> selectInventory;

    public ExpeditionSelectWindow() {
        super(new Sprite.Builder(R.mipmap.png_black_white_frame_512x256, 20, 20, 1560, 660));

        // 버튼 레이어를 하나 상승시킨다.
        BaseScene.getTopScene().addButtonLayer();

        // 모험가 세팅바 추가.
        partySettingBarUI = new PartySettingBarUI();
        partySettingBarUI.setAlwaysVisible(true);
        BaseScene.getTopScene().addPost(partySettingBarUI);

        // 탐험지역 버튼 추가.
        // 용사 리스트들을 출력하는 inventory를 생성한다.
        selectInventory  = new SelectInventory<ExpeditionAreaInfo>(ExpeditionAreaInfo.getExpeditionAreaInfos(),
                50f, 50f, 1460, 450, 4, 100);
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
        closeButton = null;
        partySettingBarUI.setDelete();
    }

    // 업데이트
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        // 탐험 버튼이 눌렸을 경우
        if(selectInventory.getSelectedIcon() != null){
            // 모험가 파티가 1명이상 배치되었는지 확인한다.
            if(0 < UserInfo.getInstance().getNumOfAdventererInParty()){
                // 탐험 씬으로 이동 한다.
                ExpeditionAreaInfo.setCurrentAreaType(selectInventory.getSelectedIcon().getAreaType());
                new BattleScene().pushScene();
            }
            selectInventory.select(null);
        }

        // 닫기 버튼을 눌렀을 경우
        if(closeButton.getTrigger())
            setDelete();
    }

}
