package tukorea2018180009.ac.kr.example.equipmentcollector.UI;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.provider.ContactsContract;
import android.util.Log;

import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;

public class GaugeSprite extends Sprite {

    RectF fillRect;
    protected Paint fillPaint, framePaint, backgroundPaint;
    float ratio;

    public GaugeSprite(Builder builder, int fillColor, float ratio) {
        super(builder);

        fillRect = new RectF();

        this.ratio = ratio;

        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.GRAY);
        backgroundPaint.setStyle(Paint.Style.FILL);

        fillPaint = new Paint();
        fillPaint.setColor(fillColor);
        fillPaint.setStyle(Paint.Style.FILL);


        framePaint = new Paint();
        framePaint.setColor(Color.BLACK);   // 테두리 생상
        //framePaint.setAntiAlias(true); // 경계면을 부드럽게 한다.
        framePaint.setStyle(Paint.Style.STROKE);    // 테두리만 그린다.
        framePaint.setStrokeWidth(Math.min(width, height) / 5f);
    }

    @Override
    protected void draw(Canvas canvas) {
        // 1. 게이지 배경을 그린다.
        canvas.drawRect(dstRect, backgroundPaint);

        // 2. 게이지 내부를 그린다.
        fillRect.left = dstRect.left;
        fillRect.right = dstRect.left + (dstRect.right - dstRect.left) * ratio;
        fillRect.top = dstRect.top;
        fillRect.bottom = dstRect.bottom;
        canvas.drawRect(fillRect, fillPaint);

        // 3. 게이지 테두리를 그린다.
        canvas.drawRect(dstRect, framePaint);

    }

    // getter, setter

    @Override
    public void setHeight(float height) {
        super.setHeight(height);
        framePaint.setStrokeWidth(Math.min(width, height) / 5f);
    }
    @Override
    public void setWidth(float width) {
        super.setWidth(width);
        framePaint.setStrokeWidth(Math.min(width, height) / 5f);
    }
    public float getRatio() {
        return ratio;
    }
    public void setRatio(float ratio) {
        this.ratio = ratio;
    }


}
