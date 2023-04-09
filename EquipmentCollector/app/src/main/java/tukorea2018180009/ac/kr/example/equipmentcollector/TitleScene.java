package tukorea2018180009.ac.kr.example.equipmentcollector;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import java.util.ListIterator;

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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(super.onTouchEvent(event))
            return true;

        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                float mx = Metrics.toGameX(event.getX());
                float my = Metrics.toGameY(event.getY());

                // 가장 마지막 자식이 가장 앞에 그려지기 때문에 클릭되었는지 판단은 반대순으로 한다.
                ListIterator<GameObject> reverseIter = gameObjects.listIterator(gameObjects.size());
                while (reverseIter.hasPrevious()) {
                    GameObject gameObject = reverseIter.previous();
                    if(!gameObject.isDeleted() && gameObject instanceof Sprite) {
                        Sprite sprite = (Sprite) gameObject;
                        if (sprite.actionDown(mx, my, new Canvas()))
                            return true;
                    }
                }
                break;

            case MotionEvent.ACTION_MOVE:
                break;
        }

        return false;
    }
}
