package tukorea2018180009.ac.kr.example.equipmentcollector;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.Log;

import tukorea2018180009.ac.kr.example.equipmentcollector.Memory.BitmapPool;

public class Sprite extends GameObject{
    private static final String TAG = Sprite.class.getSimpleName();
    protected Bitmap bitmap;
    protected RectF dstRect = new RectF();
    protected Rect srcRect = new Rect();

    protected float width, height, px, py;
    protected boolean imgFlipx, imgFlipy;   // 이미지만 반전시키고 그려지는 위치는 바뀌지 않는다.
    protected boolean flipx, flipy;         // 좌표계가 반전된다.
    protected PivotType pivotType;

    // 애니메이션을 위한 변수들
    protected boolean aniEnable, aniLoop;
    protected int aniMaxColumn, aniNFrame, aniIndex;
    protected float aniTime, aniMaxTime;

    public enum PivotType{
        custom, leftTop, rightTop, leftBottom, rightBottom, center;
    }

    protected void fixDstRect() {
        dstRect.set(-px, -py, -px + width, -py + height);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        fixDstRect();
        
        // 애니메이션 시간 경과
        if(aniEnable){
            aniTime += deltaTime;
            if(aniLoop){
                while(aniMaxTime <= aniTime){
                    aniTime -= aniMaxTime;
                }
            }
            else{
                if(aniMaxTime <= aniTime)
                    aniTime = aniMaxTime - deltaTime;
            }
        }
    }

    @Override
    protected void draw(Canvas canvas) {
        super.draw((canvas));
        if(bitmap != null){
            if(aniEnable){
                aniIndex = (int)((aniTime / aniMaxTime) * aniNFrame);
                int aniMaxRow = (aniNFrame + aniMaxColumn - 1) / aniMaxColumn;
                int aniCurrentCol = aniIndex % aniMaxColumn;
                int aniCurrentRow = aniIndex / aniMaxColumn;
                int frameWidth = bitmap.getWidth() / aniMaxColumn;
                int frameHeight = bitmap.getHeight() / aniMaxRow;
                srcRect.set(frameWidth * aniCurrentCol, frameHeight * aniCurrentRow, frameWidth * (aniCurrentCol + 1), frameHeight * (aniCurrentRow + 1));
                canvas.drawBitmap(bitmap, srcRect, dstRect, null);
            }
            else{
                canvas.drawBitmap(bitmap, null, dstRect, null);
            }
        }
    }

    @Override
    protected void TRS(Canvas canvas) {
        canvas.translate(x, y);
        canvas.rotate(rotation);  // 이미지를 모델좌표계에서 회전시킨다.
        canvas.scale(sx * (flipx ? -1 : 1), sy * (flipy ? -1 : 1));   // 이미지를 모델좌표계에서 확대/축소 및 반전 시키낟.
        canvas.scale((imgFlipx ? -1 : 1), (imgFlipy ? -1 : 1), width / 2 - px, height / 2 - py);    // 이미지의 중심을 기준으로 이미지를 반전시킨다.
    }

    public Sprite(Builder builder){
        super(builder.x, builder.y, builder.sx, builder.sy, builder.rotation);
        bitmap = builder.bitmap;
        width = builder.width;
        height = builder.height;
        pivotType = builder.pivotType;
        px = builder.px;
        py = builder.py;
        imgFlipx = builder.imgFlipx;
        imgFlipy = builder.imgFlipy;
        flipx = builder.flipx;
        flipy = builder.flipy;

        // 애니메이션 관련 변수 초기화
        aniMaxColumn = builder.aniMaxColumn;
        aniNFrame = builder.aniNFrame;
        aniMaxTime = builder.aniMaxTime;
        aniLoop = builder.aniLoop;
        aniEnable = builder.aniEnable;
        aniIndex = builder.aniIndex;
        aniTime = builder.aniTime;

        fixDstRect();
    }

    // getter setter
    public void setBitmap(Bitmap bitmap){
        this.bitmap = bitmap;
    }
    public void setBitmap(int bitmapResId) {
        bitmap = BitmapPool.get(bitmapResId);
    }
    public float getWidth() {
        return width;
    }
    public void setWidth(float width) {
        this.width = width;
        updatePivot();
    }
    public float getHeight() {
        return height;
    }
    public void setHeight(float height) {
        this.height = height;
        updatePivot();
    }
    public boolean isImgFlipx() {
        return imgFlipx;
    }
    public void setImgFlipx(boolean imgFlipx) {
        this.imgFlipx = imgFlipx;
    }

    public boolean isImgFlipy() {
        return imgFlipy;
    }

    public void setImgFlipy(boolean imgFlipy) {
        this.imgFlipy = imgFlipy;
    }

    public PivotType getPivotType() {
        return pivotType;
    }
    public void setPivot(PivotType pivotType) {
        this.pivotType = pivotType;
        updatePivot();
    }
    public void setPivot(float px, float py){
        pivotType = PivotType.custom;
        this.px = px;
        this.py = py;
    }
    protected void updatePivot(){
        switch (this.pivotType){
            case leftTop:
                this.px = 0;
                this.py = 0;
                break;
            case rightTop:
                this.px = width;
                this.py = 0;
                break;
            case leftBottom:
                this.px = 0;
                this.py = height;
                break;
            case rightBottom:
                this.px = width;
                this.py = height;
                break;
            case center:
                this.px = width / 2;
                this.py = height / 2;
                break;
        }
    }

    public void setAnimation(int aniMaxColumn, int aniNFrame, float aniMaxTime, boolean aniLoop){
        aniEnable = true;
        this.aniMaxColumn = aniMaxColumn;
        this.aniNFrame = aniNFrame;
        this.aniMaxTime = aniMaxTime;
        this.aniLoop = aniLoop;
    }
    public boolean isAniEnable() {
        return aniEnable;
    }
    public void setAniEnable(boolean aniEnable) {
        this.aniEnable = aniEnable;
    }
    public void setAniIndex(int aniIndex){
        this.aniIndex = aniIndex;
    }
    public void setAniTime(float aniTime) {
        this.aniTime = aniTime;
    }

    // 빌더 클래스
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
        protected PivotType pivotType = PivotType.leftTop;

        // 애니메이션 관련 변수
        protected boolean aniEnable = false, aniLoop = false;
        protected int aniMaxColumn = 1, aniNFrame = 1, aniIndex = 0;
        protected float aniTime = 0, aniMaxTime = 1;

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

        public Builder setPivot(PivotType pivotType){
            this.pivotType = pivotType;
            switch (pivotType){
                case leftTop:
                    this.px = 0;
                    this.py = 0;
                    break;
                case rightTop:
                    this.px = width;
                    this.py = 0;
                    break;
                case leftBottom:
                    this.px = 0;
                    this.py = height;
                    break;
                case rightBottom:
                    this.px = width;
                    this.py = height;
                    break;
                case center:
                    this.px = width / 2;
                    this.py = height / 2;
                    break;
            }
            return this;
        }
        public Builder setPivot(float px, float py){
            pivotType = PivotType.custom;
            this.px = px;
            this.py = py;
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

        public Builder setAnimation(int aniMaxColumn, int aniNFrame, float aniMaxTime, boolean aniLoop){
            aniEnable = true;
            this.aniMaxColumn = aniMaxColumn;
            this.aniNFrame = aniNFrame;
            this.aniMaxTime = aniMaxTime;
            this.aniLoop = aniLoop;
            return this;
        }
        public Builder setAniIndex(int aniIndex){
            this.aniIndex = aniIndex;
            return this;
        }
        public Builder setAniTime(float aniTime) {
            this.aniTime = aniTime;
            return this;
        }

        public Sprite build(){
            return new Sprite(this);
        }
    }
}



