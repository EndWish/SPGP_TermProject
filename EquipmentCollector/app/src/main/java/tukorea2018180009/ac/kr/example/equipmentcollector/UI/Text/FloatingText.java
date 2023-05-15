package tukorea2018180009.ac.kr.example.equipmentcollector.UI.Text;

import android.graphics.Paint;

public class FloatingText extends Text{
    protected float lifeTime;

    public FloatingText(float cx, float cy, String text, int color) {
        super(cx, cy, text, 75, 1000, color, Paint.Align.CENTER);

        lifeTime = 1.f;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        lifeTime -= deltaTime;
        setY(getY() - deltaTime * 100);
        if(lifeTime <= 0)
            setDelete();
    }
}
