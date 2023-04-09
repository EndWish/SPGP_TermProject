package tukorea2018180009.ac.kr.example.equipmentcollector.Scenes;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.ListIterator;

import tukorea2018180009.ac.kr.example.equipmentcollector.GameObject;
import tukorea2018180009.ac.kr.example.equipmentcollector.Metrics;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;

public class BaseScene {
    private static ArrayList<BaseScene> stack = new ArrayList<>();

    protected ArrayList<GameObject> gameObjects = new ArrayList<>();

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
        gameObjects.add(object);
        return gameObjects.size();
    }

    public void update(float deltaTime) {
        gameObjects.removeIf(child -> child.isDeleted());
        for (GameObject object : gameObjects) {
            if(object.isDeleted())
                continue;
            object.update(deltaTime);
        }
    }

    public void draw(Canvas canvas) {
        for (GameObject object : gameObjects) {
            if(object.isDeleted())
                continue;
            object.draw(canvas);
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                float mx = Metrics.toGameX(event.getX());
                float my = Metrics.toGameY(event.getY());

                // 가장 마지막 자식이 가장 앞에 그려지기 때문에 클릭되었는지 판단은 반대순으로 한다.
                ListIterator<GameObject> reverseIter = gameObjects.listIterator(gameObjects.size());
                while (reverseIter.hasPrevious()) {
                    GameObject gameObject = reverseIter.previous();
                    if(!gameObject.isDeleted() && gameObject instanceof Sprite) {
                        Sprite sprite = (Sprite) gameObject;
                        if (sprite.actionDown(mx, my, new Canvas()))
                            return true;
                    }
                }
                break;

            case MotionEvent.ACTION_MOVE:
                break;
        }

        return false;
    }
}
