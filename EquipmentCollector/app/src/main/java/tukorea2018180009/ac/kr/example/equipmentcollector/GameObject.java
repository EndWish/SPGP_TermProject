package tukorea2018180009.ac.kr.example.equipmentcollector;

import android.graphics.Canvas;

import java.util.ArrayList;

public class GameObject {
    private boolean isDeleted;
    private ArrayList<GameObject> children = new ArrayList<GameObject>();

    GameObject(){
        isDeleted = false;
    }

    public void update(float deltaTime){
        // 자식들도 똑같이 update()를 해준다.
        for(GameObject child : children)
            child.update(deltaTime);
    }

    public void draw(Canvas canvas){
        // 자식들도 똑같이 draw()를 해준다.
        for(GameObject child : children)
            child.draw(canvas);
    }

    public void SetDelete(){
        isDeleted = true;
        for(GameObject child : children)
            child.SetDelete();
        children.clear();
    }
    public boolean IsDeleted(){
        return isDeleted;
    }

}
