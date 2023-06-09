package tukorea2018180009.ac.kr.example.equipmentcollector;

import android.graphics.Canvas;
import android.graphics.Matrix;

import java.util.ArrayList;

import tukorea2018180009.ac.kr.example.equipmentcollector.UI.SpriteButton;

public class GameObject extends Object {
    protected boolean visible;
    protected float x, y, sx, sy, rotation; // degree (x+ 쪽이 앞쪽이다)
    protected ArrayList<GameObject> children = new ArrayList<GameObject>();

    public GameObject() {
        super();
        deleted = false;
        visible = true;
        x = 0;
        y = 0;
        sx = 1;
        sy = 1;
        rotation = 0;
    }
    public GameObject(float cx, float cy){
        this();
        this.x = cx;
        this.y = cy;
    }
    public GameObject(float cx, float cy, float sx, float sy, float rotation){
        this(cx, cy);
        this.sx = sx;
        this.sy = sy;
        this.rotation = rotation;
    }

    /// update 함수
    @Override
    public void update(float deltaTime){
        // 삭제 표시가 된 오브젝트를 삭제해준다.
        children.removeIf(child -> child == null || child.isDeleted());

        // 자식들도 똑같이 update()를 해준다.
        for(GameObject child : children) {
            child.update(deltaTime);
        }
    }

    /// draw하는 함수
    // 그리기 위한 캔버스 변환과 계층적으로 ,drawAll을 호출해주는 함수
    public void drawAll(Canvas canvas){
        if(!visible) return;    // visible이 false면 그리지 않는다.(자식도 그리지 않는다.)

        canvas.save();

        // 캔버스에 변환을 적용한다.
        TRS(canvas);
        draw(canvas);

        // 자식들도 똑같이 draw()를 해준다.
        for(GameObject child : children){
            if(child.isDeleted())
                continue;
            child.drawAll(canvas);
        }

        canvas.restore();
    }
    // 그리기 위한 함수
    protected void draw(Canvas canvas){

    }
    // 컨버스를 변환하는 함수
    protected void TRS(Canvas canvas){
        canvas.translate(x, y);
        canvas.rotate(rotation);  // 이미지를 모델좌표계에서 회전시킨다.
        canvas.scale(sx, sy);   // 이미지를 모델좌표계에서 확대/축소 및 반전 시키낟.
    }

    public SpriteButton getButtonOnMouse(float mx, float my, Canvas canvas) {
        canvas.save();
        TRS(canvas);

        // 자식들도 버튼이 있을 수 있으니 확인해주고 리턴한다.
        SpriteButton result;
        for(GameObject child : children){
            if(child.isDeleted())
                continue;
            result = child.getButtonOnMouse(mx, my, canvas);
            if(result != null) {
                canvas.restore();
                return result;
            }
        }

        // 충돌 검사를 해서 충돌할 경우 리턴한다.
        if(this instanceof SpriteButton && ((SpriteButton) this).isSameButtonLayer() && ((SpriteButton) this).isClickable()) {
            SpriteButton me = (SpriteButton)this;

            // 마우스의 위치를 나의 로컬 좌표계로 변환한다.
            float[] mousePoint = { mx, my };
            Matrix inverseMatrix = new Matrix();
            canvas.getMatrix().invert(inverseMatrix);
            inverseMatrix.mapPoints(mousePoint);
            float mx2 = mousePoint[0];
            float my2 = mousePoint[1];

            // 나와 충돌하는지 확인한다.
            if(me.dstRect.left <= mx2 && mx2 <= me.dstRect.right && me.dstRect.top <= my2 && my2 <= me.dstRect.bottom){
                canvas.restore();
                return me;
            }
        }

        canvas.restore();
        return null;
    }

    // 자식 추가 함수
    public void addChild(GameObject gameObject){
        children.add(gameObject);
    }

    // 자신을 삭제/추가 하는 함수
    @Override
    public void setDelete() {
        super.setDelete();
        for(GameObject child : children)
            if(!child.isDeleted())
                child.setDelete();
        children.clear();
    }

    // getter, setter
    public boolean isVisible() {
        return visible;
    }
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public float getX() {
        return x;
    }
    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }
    public void setY(float y) {
        this.y = y;
    }

    public float getSx() {
        return sx;
    }
    public void setSx(float sx) {
        this.sx = sx;
    }

    public float getSy() {
        return sy;
    }
    public void setSy(float sy) {
        this.sy = sy;
    }

    public float getRotation() {
        return rotation;
    }
    public void setRotation(float rotation) {
        this.rotation = rotation;
    }
}
