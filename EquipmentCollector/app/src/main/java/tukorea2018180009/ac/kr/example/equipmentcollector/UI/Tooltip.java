package tukorea2018180009.ac.kr.example.equipmentcollector.UI;

import android.graphics.Color;
import android.graphics.Paint;

import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.Text;

public class Tooltip extends Sprite {
    Text childText;

    public Tooltip(String text, float width, float cx, float cy) {
        super(new Builder(R.mipmap.png_black_white_frame_512x256, cx, cy, width, width));

        float textSize = (width - 50f) / 30f;

        // 텍스트 추가
        childText = new Text(25, 25, text, textSize, width - 50, Color.BLUE, Paint.Align.LEFT);
        addChild(childText);

        // 텍스트의 길이에 따라 높이 조절
        setHeight(childText.getNLine() * textSize + 50.f);
    }

    @Override
    public void setDelete() {
        childText = null;
        super.setDelete();
    }
}
