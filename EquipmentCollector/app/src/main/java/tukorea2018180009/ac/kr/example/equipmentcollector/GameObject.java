package tukorea2018180009.ac.kr.example.equipmentcollector;

import android.graphics.Canvas;
import android.icu.text.Edits;

import java.util.ArrayList;
import java.util.Iterator;

public class GameObject {
    protected boolean deleted;
    protected float x, y, sx, sy, rotation; // degree (x+ 쪽이 앞쪽이다)
    protected ArrayList<GameObject> children = new ArrayList<GameObject>();

    GameObject() {
        deleted = false;
        x = 0;
        y = 0;
        sx = 1;
        sy = 1;
        rotation = 0;
    }
    GameObject(float cx, float cy){
        this();
        this.x = cx;
        this.y = cy;
    }
    GameObject(float cx, float cy, float sx, float sy, float rotation){
        this(cx, cy);
        this.sx = sx;
        this.sy = sy;
        this.rotation = rotation;
    }

    /// update 함수
    public void update(float deltaTime){
        // 삭제 표시가 된 오브젝트를 삭제해준다.
        children.removeIf(child -> child.isDeleted());

        // 자식들도 똑같이 update()를 해준다.
        for(GameObject child : children){
            if(child.isDeleted())
                continue;
            child.update(deltaTime);
        }
    }

    /// draw하는 함수
    // 그리기 위한 캔버스 변환과 계층적으로 ,drawAll을 호출해주는 함수
    public void drawAll(Canvas canvas){
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

    // 자식 추가 함수
    public void addChild(GameObject gameObject){
        children.add(gameObject);
    }

    // 자신을 삭제/추가 하는 함수
    public void setDelete(){
        deleted = true;
        for(GameObject child : children)
            child.setDelete();
        children.clear();
    }
    public boolean isDeleted(){
        return deleted;
    }

}
