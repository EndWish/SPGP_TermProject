package tukorea2018180009.ac.kr.example.equipmentcollector;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;

public class BaseScene {
    private static ArrayList<BaseScene> stack = new ArrayList<>();

    private ArrayList<GameObject> objects = new ArrayList<>();

    public static BaseScene getTopScene() {
        int top = stack.size() - 1;
        if (top < 0) return null;
        return stack.get(top);
    }

    public int pushScene() {
        stack.add(this);
        return stack.size();
    }

    public int add(GameObject object) {
        objects.add(object);
        return objects.size();
    }

    public void update(float deltaTime) {
        objects.removeIf(child -> child.isDeleted());
        for (GameObject object : objects) {
            if(object.isDeleted())
                continue;
            object.update(deltaTime);
        }
    }

    public void draw(Canvas canvas) {
        for (GameObject object : objects) {
            if(object.isDeleted())
                continue;
            object.draw(canvas);
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }
}
