package tukorea2018180009.ac.kr.example.equipmentcollector.Scenes;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.ListIterator;

import tukorea2018180009.ac.kr.example.equipmentcollector.Buttons.SpriteButton;
import tukorea2018180009.ac.kr.example.equipmentcollector.GameObject;
import tukorea2018180009.ac.kr.example.equipmentcollector.GameView;
import tukorea2018180009.ac.kr.example.equipmentcollector.Metrics;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;

public class BaseScene {
    private static final String TAG = BaseScene.class.getSimpleName();;

    private static ArrayList<BaseScene> stack = new ArrayList<>();

    protected ArrayList<GameObject> gameObjects = new ArrayList<>();
    protected SpriteButton buttonOnMouse, clickedButton;
    protected int buttonLayer = 0;
    protected Canvas canvasForButton = new Canvas();


    public static BaseScene getTopScene() {
        int top = stack.size() - 1;
        if (top < 0) return null;
        return stack.get(top);
    }

    public void init() { }

    public int pushScene() {
        stack.add(this);
        init();
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
            object.drawAll(canvas);
        }
    }

    public void getButtonOnMouse(float mx, float my){
        buttonOnMouse = null;
        for (GameObject gameObject : gameObjects) {
            if(gameObject.isDeleted())
                continue;
            buttonOnMouse = gameObject.getButtonOnMouse(mx, my, canvasForButton);

            if(buttonOnMouse != null)
                return;
        }
    }

    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();

        float mx = Metrics.toGameX(event.getX());
        float my = Metrics.toGameY(event.getY());
        getButtonOnMouse(mx, my);

        switch (action) {
            case MotionEvent.ACTION_MOVE:
                if(clickedButton != null && !clickedButton.isDeleted()){
                    clickedButton.drag();
                }
                return true;
            case MotionEvent.ACTION_DOWN:
                if(buttonOnMouse != null && !buttonOnMouse.isDeleted()){
                    buttonOnMouse.clickDown();
                }
                return true;
            case MotionEvent.ACTION_UP:
                if(buttonOnMouse != null && !buttonOnMouse.isDeleted()){
                    buttonOnMouse.clickUp();
                }
                return true;
        }

        return false;
    }

    // getter, setter
    public int getButtonLayer(){
        return buttonLayer;
    }
    public void addButtonLayer(){
        ++buttonLayer;
    }
    public void subButtonLayer(){
        --buttonLayer;
    }

    public SpriteButton getButtonOnMouse() { return buttonOnMouse;}
    public SpriteButton getClickedButton() { return clickedButton;}
    public void setButtonOnMouse(SpriteButton buttonOnMouse) { this.buttonOnMouse = buttonOnMouse;}
    public void setClickedButton(SpriteButton clickedButton) { this.clickedButton = clickedButton;}
}

