package tukorea2018180009.ac.kr.example.equipmentcollector.UI;

import android.graphics.Color;
import android.graphics.Paint;

import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Scenes.BaseScene;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.Text.Text;

public class NotificationMessage extends Sprite {

    protected TriggerButton yesButton;
    protected Text childText;

    // 생성자
    public NotificationMessage() {
        super(new Sprite.Builder(R.mipmap.png_black_white_frame_256x512, 375, 25, 850, 850));

        // 버튼 레이어를 하나 상승시킨다.
        BaseScene.getTopScene().addButtonLayer();

        // 텍스트
        childText = new Text(25, 25, "내용이 없습니다.", 50, 800, Color.RED, Paint.Align.LEFT);
        addChild(childText);

        // Yes 버튼
        yesButton = new TriggerButton(new Builder(R.mipmap.png_button_ok, width / 2.f, height - width / 5, width / 5, width / 10)
                .setPivot(PivotType.center));
        addChild(yesButton);
    }
    public NotificationMessage(String text) {
        this();
        childText.setText(text);
    }

    // 소멸자
    @Override
    public void setDelete() {
        BaseScene.getTopScene().subButtonLayer();
        yesButton = null;
        super.setDelete();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if(yesButton.getTrigger()){
            setDelete();
        }
    }
}
