package tukorea2018180009.ac.kr.example.equipmentcollector;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.renderscript.Float2;
import android.renderscript.Matrix3f;
import android.util.Log;

import java.util.AbstractMap;
import java.util.ListIterator;
import java.util.Vector;

public class Sprite extends GameObject{
    private static final String TAG = Sprite.class.getSimpleName();
    protected Bitmap bitmap;
    protected RectF dstRect = new RectF();

    protected float width, height, px, py;
    protected boolean imgFlipx, imgFlipy;   // 이미지만 반전시키고 그려지는 위치는 바뀌지 않는다.
    protected boolean flipx, flipy;         // 좌표계가 반전된다.


    protected void setBitmapResource(int bitmapResId) {
        bitmap = BitmapPool.get(bitmapResId);
    }

    protected void fixDstRect() {
        dstRect.set(-px, -py, -px + width, -py + height);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        fixDstRect();
    }

    @Override
    protected void draw(Canvas canvas) {
        super.draw((canvas));
        if(bitmap != null)
            canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    @Override
    protected void TRS(Canvas canvas){
        canvas.translate(x, y);
        canvas.rotate(rotation);  // 이미지를 모델좌표계에서 회전시킨다.
        canvas.scale(sx * (flipx ? -1 : 1), sy * (flipy ? -1 : 1));   // 이미지를 모델좌표계에서 확대/축소 및 반전 시키낟.
        canvas.scale((imgFlipx ? -1 : 1), (imgFlipy ? -1 : 1), width / 2 - px, height / 2 - py);    // 이미지의 중심을 기준으로 이미지를 반전시킨다.
    }

    public void actionDownReaction(float mx, float my){
        if (BuildConfig.DEBUG) {
            Log.d(TAG,"클릭 되었습니다.");
        }
    }

    public boolean actionDown(float mx, float my, Canvas canvas){
        canvas.save();
        // TRS
        canvas.translate(x, y);
        canvas.rotate(rotation);  // 이미지를 모델좌표계에서 회전시킨다.
        canvas.scale(sx * (flipx ? -1 : 1), sy * (flipy ? -1 : 1));   // 이미지를 모델좌표계에서 확대/축소 및 반전 시키낟.
        canvas.scale((imgFlipx ? -1 : 1), (imgFlipy ? -1 : 1), width / 2 - px, height / 2 - py);    // 이미지의 중심을 기준으로 이미지를 반전시킨다.

        float[] mousePoint = { mx, my };
        Matrix inverseMatrix = new Matrix();
        canvas.getMatrix().invert(inverseMatrix);
        inverseMatrix.mapPoints(mousePoint);
        float nx = mousePoint[0];
        float ny = mousePoint[1];

        if(dstRect.left <= nx && nx <= dstRect.right && dstRect.top <= ny && ny <= dstRect.bottom) {
            // 자식이 클릭 되었을 경우
            // 가장 마지막 자식이 가장 앞에 그려지기 때문에 클릭되었는지 판단은 반대순으로 한다.
            ListIterator<GameObject> reverseIter = children.listIterator(children.size());
            while (reverseIter.hasPrevious()) {
                GameObject gameObject = reverseIter.previous();
                if(!gameObject.isDeleted() && gameObject instanceof Sprite) {
                    Sprite sprite = (Sprite) gameObject;
                    if (sprite.actionDown(mx, my, canvas)){
                        canvas.restore();
                        return true;
                    }
                }
            }
            
            // 내가 클릭되었을 경우
            actionDownReaction(mx, my);
            canvas.restore();
            return true;
        }
        
        // 클릭되지 않았을 경우
        canvas.restore();
        return false;
    }

    public Sprite(Builder builder){
        super(builder.x, builder.y, builder.sx, builder.sy, builder.rotation);
        bitmap = builder.bitmap;
        width = builder.width;
        height = builder.height;
        px = builder.px;
        py = builder.py;
        imgFlipx = builder.imgFlipx;
        imgFlipy = builder.imgFlipy;
        flipx = builder.flipx;
        flipy = builder.flipy;
        fixDstRect();
    }

    public static class Builder{
        // 필수 인자.
        protected Bitmap bitmap;
        protected float x, y, width, height;

        // 선택적 인자.
        protected float px = 0, py = 0;
        protected float rotation = 0; // degree (x+ 쪽이 앞쪽이다)
        protected float sx = 1, sy = 1;
        protected boolean flipx = false, flipy = false;
        protected boolean imgFlipx = false, imgFlipy = false;

        public Builder(Bitmap bitmap, float cx, float cy, float width, float height){
            if(bitmap != null)
                this.bitmap = bitmap;
            this.x = cx;
            this.y = cy;
            this.width = width;
            this.height = height;
        }
        public Builder(int bitmapResId, float cx, float cy, float width, float height) {
            this(bitmapResId != 0 ? BitmapPool.get(bitmapResId) : null, cx, cy, width, height);
        }

        public Builder setImgFlip(boolean fx, boolean fy){
            this.imgFlipx = fx;
            this.imgFlipy = fy;
            return this;
        }
        public Builder setFlip(boolean fx, boolean fy){
            this.flipx = fx;
            this.flipy = fy;
            return this;
        }

        public Builder setPivot(float px, float py){
            this.px = px;
            this.py = py;
            return this;
        }
        public Builder setPivotCenter(){
            this.px = width / 2;
            this.py = height / 2;
            return this;
        }
        public Builder setRotation(float degree){
            this.rotation = degree;
            return this;
        }
        public Builder setScale(float sx, float sy){
            this.sx = sx;
            this.sy = sy;
            return this;
        }

        public Sprite build(){
            return new Sprite(this);
        }
    }
}



