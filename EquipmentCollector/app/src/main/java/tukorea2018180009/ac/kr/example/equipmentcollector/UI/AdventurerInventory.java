package tukorea2018180009.ac.kr.example.equipmentcollector.UI;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.Log;

import java.util.ArrayList;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Adventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Status;
import tukorea2018180009.ac.kr.example.equipmentcollector.GameView;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Scenes.BaseScene;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.Text;
import tukorea2018180009.ac.kr.example.equipmentcollector.UserInfo;

public class AdventurerInventory extends Sprite {
    SelectInventory<Adventurer> selectInventory;
    TriggerButton closeButton;

    // 생성자
    public AdventurerInventory() {
        super(new Sprite.Builder(R.mipmap.png_black_white_frame_512x256, 20, 20, 1560, 860));

        // 버튼 레이어를 하나 상승시킨다.
        BaseScene.getTopScene().addButtonLayer();

        // 용사 리스트들을 출력하는 inventory를 생성한다.
        selectInventory  = new SelectInventory<Adventurer>(UserInfo.getInstance().getAdventurers(),
                50f, 50f, 1460, 650, 7, 100);
        addChild(selectInventory);

        // 닫기 버튼 생성
        closeButton = new TriggerButton(new Builder(R.mipmap.png_button_x, width, 0, 50, 50)
                .setPivotRightTop());
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

        // 모험가를 선택했을 경우
        if(selectInventory.getSelectedIcon() != null){
            BaseScene.getTopScene().add(new AdventurerInfoUI(selectInventory.getSelectedIcon()));
            selectInventory.select(null);
        }
        
        // 닫기 버튼을 눌렀을 경우
        if(closeButton.getTrigger())
            setDelete();
    }

}
