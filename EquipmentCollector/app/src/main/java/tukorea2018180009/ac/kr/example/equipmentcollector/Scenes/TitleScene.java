package tukorea2018180009.ac.kr.example.equipmentcollector.Scenes;

import android.graphics.Canvas;

import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.TriggerButton;

public class TitleScene extends BaseScene{

    TriggerButton backgroundObj;

    @Override
    public void init() {
        super.init();
        backgroundObj = new TriggerButton(new Sprite.Builder(R.mipmap.jpg_title_background, 1.f,1.f,1600.f,900.f)
                .setPivot(0, 0));
        add(backgroundObj);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if(backgroundObj.getTrigger()) {
            BaseScene.popScene();
            new VillageScene().pushScene();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

}
