package tukorea2018180009.ac.kr.example.equipmentcollector;

import android.graphics.Canvas;
import android.icu.text.Edits;

import java.util.ArrayList;
import java.util.Iterator;

public class GameObject {
    protected boolean deleted;
    protected ArrayList<GameObject> children = new ArrayList<GameObject>();

    GameObject(){
        deleted = false;
    }

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

    public void draw(Canvas canvas){
        // 자식들도 똑같이 draw()를 해준다.
        for(GameObject child : children){
            if(child.isDeleted())
                continue;
            child.draw(canvas);
        }
    }

    public void addChild(GameObject gameObject){
        children.add(gameObject);
    }

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
