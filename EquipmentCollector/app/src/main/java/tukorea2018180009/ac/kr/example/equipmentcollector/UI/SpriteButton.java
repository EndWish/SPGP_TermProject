package tukorea2018180009.ac.kr.example.equipmentcollector.UI;

import android.util.Log;

import tukorea2018180009.ac.kr.example.equipmentcollector.BuildConfig;
import tukorea2018180009.ac.kr.example.equipmentcollector.Scenes.BaseScene;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;

public class SpriteButton extends Sprite {
    private static final String TAG = SpriteButton.class.getSimpleName();
    boolean clickable;
    int layer;

    public SpriteButton(Builder builder) {
        super(builder);
        clickable = true;
        layer = BaseScene.getTopScene().getButtonLayer();
    }

    public boolean isSameButtonLayer(){
        return layer == BaseScene.getTopScene().getButtonLayer();
    }

    public void clickDown() {
        if(BuildConfig.DEBUG){
            Log.d(TAG, "버튼이 클릭되었습니다.");
        }
        BaseScene.getTopScene().setClickedButton(this);
    }
    public void clickUp(){
        // clickUp이 되면 자기 자신이 선택된것을 해제하기 때문에 이 함수를 Override할때는 마지막에 super를 불러줘야 한다.
        BaseScene.getTopScene().setClickedButton(null);
    }
    public void drag() {

    }

    protected boolean isClickedThis(){
        return BaseScene.getTopScene().getClickedButton() == this;
    }

    // getter, setter
    public boolean isClickable() {
        return clickable;
    }
    public void setClickable(boolean clickable) {
        this.clickable = clickable;
    }
}
