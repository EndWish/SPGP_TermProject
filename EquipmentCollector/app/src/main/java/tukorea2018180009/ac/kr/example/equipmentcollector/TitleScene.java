package tukorea2018180009.ac.kr.example.equipmentcollector;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class TitleScene extends BaseScene{
    private Paint paint;    // 씬이 제대로 만들어져서 push되는지 확인하기 위해 임시로 추가한 페인트

    public TitleScene() {
        paint = new Paint();
        paint.setColor(Color.BLUE);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(1,1, 15, 8, paint);
        super.draw(canvas);
    }
}
