package tukorea2018180009.ac.kr.example.equipmentcollector.Scenes;

import android.graphics.Canvas;

import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;

public class TitleScene extends BaseScene{

    public TitleScene() {
        Sprite backgroundObj = new Sprite
                .Builder(R.mipmap.jpg_village_background, 1.f,1.f,16.f,9.f)
                .setImgFlip(false, true)
                .setFlip(false, true)
                .setPivot(0, 0)
                .setRotation(30)
                .setScale(1.5f, 1.5f)
                .build();
        add(backgroundObj);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

}
