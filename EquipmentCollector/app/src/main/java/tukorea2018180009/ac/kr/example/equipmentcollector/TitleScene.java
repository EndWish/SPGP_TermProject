package tukorea2018180009.ac.kr.example.equipmentcollector;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class TitleScene extends BaseScene{

    public TitleScene() {
        Sprite backgroundObj = new Sprite(R.mipmap.jpg_village_background, 0,0,16,9,0,0);
        add(backgroundObj);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }
}
