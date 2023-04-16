package tukorea2018180009.ac.kr.example.equipmentcollector.UI;

import java.util.ArrayList;

import tukorea2018180009.ac.kr.example.equipmentcollector.IIcon;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Scenes.BaseScene;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;

public class SelectInventory<T extends IIcon> extends Sprite {
    protected T selectedIcon;

    // 생성자
    public SelectInventory(ArrayList<T> icons, float cx, float cy, float width, float height, int column) {
        super(new Sprite.Builder(R.mipmap.png_black_white_frame_256x256, cx, cy, width, height));

        // 버튼 레이어를 하나 상승시킨다.
        BaseScene.getTopScene().addButtonLayer();

        // SelectButton들을 자식으로 생성
        final float paddingRatio = 0.2f;    // 패딩이 차지할 비율
        final float padding = width * paddingRatio / (column + 1);
        final float buttonWidth = width * (1 - paddingRatio) / column;

        for(int i = 0; i < icons.size(); ++i){
            float buttonx = padding + (i % column) * buttonWidth;
            float buttony = padding + (i / column) * buttonWidth;
            SelectButton<T> selectButton = new SelectButton(this, icons.get(i), buttonx, buttony, buttonWidth, buttonWidth);
            addChild(selectButton);
        }
    }

    // 소멸
    @Override
    public void setDelete() {
        BaseScene.getTopScene().subButtonLayer();
        selectedIcon = null;
        super.setDelete();
    }

    // 선택 콜벡 함수
    public void select(T selectedIcon){ this.selectedIcon = selectedIcon; }
    public T getSelectedIcon() { return selectedIcon; }

    //////////////////////// SelectInventory에 사용될 버튼 클래스 //////////////////////
    class SelectButton<T extends IIcon> extends SpriteButton {
        protected SelectInventory targetInventory;
        protected T myIcon;

        public SelectButton(SelectInventory targetInventory, T icon, float cx, float cy, float width, float height) {
            super(new Sprite.Builder(icon.getIcon(), cx, cy, width, height));
            this.targetInventory = targetInventory;
            this.myIcon = icon;
        }

        @Override
        public void setDelete() {
            super.setDelete();
            targetInventory = null;
            myIcon = null;
        }

        @Override
        public void clickUp() {
            if(isClickedThis()) {
                if(myIcon != null)
                    targetInventory.select(this.myIcon);
            }
            super.clickUp();
        }
    }

}
