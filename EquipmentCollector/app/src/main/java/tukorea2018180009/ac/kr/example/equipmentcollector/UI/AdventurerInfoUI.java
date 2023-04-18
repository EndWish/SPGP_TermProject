package tukorea2018180009.ac.kr.example.equipmentcollector.UI;

import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Adventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Status;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Scenes.BaseScene;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.Text;

public class AdventurerInfoUI extends Sprite {
    TriggerButton closeButton;

    Sprite profileImage;
    Text nameText;

    ArrayList<StatusText> statusTexts;

    public AdventurerInfoUI(Adventurer adventurer) {
        super(new Sprite.Builder(R.mipmap.png_black_white_frame_512x256, 25, 25, 1550, 850));
        // 버튼 레이어를 하나 상승시킨다.
        BaseScene.getTopScene().addButtonLayer();

        // 프로필 사진 초기화
        profileImage = new Sprite(new Builder(adventurer.getProfile(), 25, 25, 300, 450));
        addChild(profileImage);

        // 이름 텍스트
        nameText = new Text(162.5f, 475f, "< " + adventurer.getName() + " >", 50f, 500f, Color.BLACK, Paint.Align.CENTER);
        addChild(nameText);

        // 능력치 텍스트들 생성
        statusTexts = new ArrayList<>();
        Status.Type[] statusTypes = Status.Type.values();
        for(int i = 0; i < 4; ++i){
            StatusText statusText = new StatusText(adventurer.getTotalStatus(), statusTypes[i], 350f, 50f + i * 75f, 70f);
            statusTexts.add(statusText);
            addChild(statusText);
        }
        for(int i = 0; i < 6; ++i){
            StatusText statusText = new StatusText(adventurer.getTotalStatus(), statusTypes[4 + i], 750f, 50f + i * 75f, 70f);
            statusTexts.add(statusText);
            addChild(statusText);
        }
        for(int i = 0; i < 6; ++i){
            StatusText statusText = new StatusText(adventurer.getTotalStatus(), statusTypes[10 + i], 1150f, 50f + i * 75f, 70f);
            statusTexts.add(statusText);
            addChild(statusText);
        }

        // 닫기 버튼 생성
        closeButton = new TriggerButton(new Builder(R.mipmap.png_button_x, width, 0, 50, 50)
                .setPivotRightTop());
        addChild(closeButton);
        
        // 스킬 버튼 생성
        
    }

    // 소멸자
    @Override
    public void setDelete() {
        // 버튼 레이어를 감소시킨다.
        BaseScene.getTopScene().subButtonLayer();

        closeButton = null;
        profileImage = null;
        super.setDelete();
    }

    // update
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        // 닫기 버튼을 눌렀을 경우
        if(closeButton.getTrigger())
            setDelete();
    }
}
