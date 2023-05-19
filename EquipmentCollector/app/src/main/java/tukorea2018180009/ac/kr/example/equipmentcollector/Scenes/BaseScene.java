package tukorea2018180009.ac.kr.example.equipmentcollector.Scenes;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;

import tukorea2018180009.ac.kr.example.equipmentcollector.IIcon;
import tukorea2018180009.ac.kr.example.equipmentcollector.Object;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.SpriteButton;
import tukorea2018180009.ac.kr.example.equipmentcollector.GameObject;
import tukorea2018180009.ac.kr.example.equipmentcollector.Metrics;

public class BaseScene {
    private static final String TAG = BaseScene.class.getSimpleName();;

    private static ArrayList<BaseScene> stack = new ArrayList<>();

    protected ArrayList<GameObject> gameObjects = new ArrayList<>();
    protected ArrayList<GameObject> addPostGameObjects = new ArrayList<>();
    protected SpriteButton buttonOnMouse, clickedButton;
    protected Object hand;
    protected RectF handRectF = new RectF();
    protected float mx = 0;
    protected float my = 0;
    protected int buttonLayer = 0;
    protected Canvas canvasForButton = new Canvas();


    public static BaseScene getTopScene() {
        int top = stack.size() - 1;
        if (top < 0) return null;
        return stack.get(top);
    }
    public static int popScene() {
        stack.remove(stack.size() - 1);
        return stack.size();
    }

    public void init() { }

    public int pushScene() {
        stack.add(this);
        init();
        return stack.size();
    }

    protected int add(GameObject object) {
        gameObjects.add(object);
        return gameObjects.size();
    }

    public void addPost(GameObject object){
        addPostGameObjects.add(object);
    }

    public void update(float deltaTime) {
        gameObjects.removeIf(child -> child == null || child.isDeleted());
        for (GameObject object : gameObjects) {
            object.update(deltaTime);
        }
        gameObjects.addAll(addPostGameObjects);
        addPostGameObjects.clear();
    }
    public void draw(Canvas canvas) {
        for (GameObject object : gameObjects) {
            if(object.isDeleted())
                continue;
            object.drawAll(canvas);
        }

        if(hand != null && hand instanceof IIcon){
            Log.d(TAG, mx + "," + my);
            handRectF.set(mx - 50f, my - 50f, mx + 50f, my + 50f);
            canvas.drawBitmap(((IIcon) hand).getIcon(), null, handRectF, null);
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

        mx = Metrics.toGameX(event.getX());
        my = Metrics.toGameY(event.getY());
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
                if(clickedButton != null)
                    clickedButton = null;
                setHand(null);
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
    public Object getHand() {
        return hand;
    }
    public void setHand(Object hand) {
        this.hand = hand;
    }
}

