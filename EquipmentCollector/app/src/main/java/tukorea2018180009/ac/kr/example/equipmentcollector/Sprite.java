package tukorea2018180009.ac.kr.example.equipmentcollector;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

public class Sprite extends GameObject{
    protected Bitmap bitmap;
    protected RectF dstRect = new RectF();
    protected float x, y, width, height, pivotx, pivoty;
    public Sprite(int bitmapResId, float cx, float cy, float width, float height, float pivotx, float pivoty) {
        this.x = cx;
        this.y = cy;
        this.pivotx = pivotx;
        this.pivoty = pivoty;
        this.width = width;
        this.height = height;
        if (bitmapResId != 0) {
            setBitmapResource(bitmapResId);
        }
        fixDstRect();
    }

    protected void setBitmapResource(int bitmapResId) {
        bitmap = BitmapPool.get(bitmapResId);
    }

    protected void fixDstRect() {
        float half_width = width / 2;
        float half_height = height / 2;
        dstRect.set(x - width * pivotx, y - height * pivoty, x + width - width * pivotx, y + height - height * pivoty);
    }

    @Override
    public void update(float deltaTime) {
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }
}
