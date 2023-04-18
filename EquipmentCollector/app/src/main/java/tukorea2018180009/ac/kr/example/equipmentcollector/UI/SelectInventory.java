package tukorea2018180009.ac.kr.example.equipmentcollector.UI;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;

import tukorea2018180009.ac.kr.example.equipmentcollector.IIcon;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Scenes.BaseScene;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.Text;

public class SelectInventory<T extends IIcon> extends Sprite {
    protected T selectedIcon;
    protected ArrayList<T> icons;
    protected ArrayList<SelectButton<T>> selectButtons = new ArrayList<>();
    protected int pageIndex = 0;

    protected TriggerButton leftPageButton, rightPageButton;
    protected Text pageText;

    // 생성자
    public SelectInventory(ArrayList<T> icons, float cx, float cy, float width, float height, int column, float triggetButtonWidth) {
        super(new Sprite.Builder(R.mipmap.png_black_white_frame_512x256, cx, cy, width, height));
        this.icons = icons;

        // SelectButton들을 자식으로 생성
        final float paddingRatio = 0.2f;    // 패딩이 차지할 비율
        final float padding = width * paddingRatio / (column + 1);
        final float buttonWidth = width * (1 - paddingRatio) / column;
        final int row = (int)(height / (buttonWidth + padding));

        for(int i = 0; i < row * column; ++i){
            float buttonx = padding + (i % column) * (buttonWidth + padding);
            float buttony = padding + (i / column) * (buttonWidth + padding);

            SelectButton<T> selectButton = new SelectButton(this, null, buttonx, buttony, buttonWidth, buttonWidth);
            selectButtons.add(selectButton);
            addChild(selectButton);
        }

        // 페이지 넘기는 버튼 생성 & 텍스트
        leftPageButton = new TriggerButton(new Builder(R.mipmap.png_button_left_arrow, 0, height * 1.02f, triggetButtonWidth, triggetButtonWidth));
        rightPageButton = new TriggerButton(new Builder(R.mipmap.png_button_right_arrow, width, height * 1.02f, triggetButtonWidth, triggetButtonWidth)
                                                .setPivotRightTop());
        pageText = new Text(width / 2.f, height * 1.02f, (pageIndex + 1) + "/" + getMaxPageIndex(), triggetButtonWidth, width, Color.BLACK, Paint.Align.CENTER);
        addChild(leftPageButton);
        addChild(rightPageButton);
        addChild(pageText);

        updatePage();
    }

    // 소멸
    @Override
    public void setDelete() {
        selectedIcon = null;
        icons = null;
        selectButtons = null;
        super.setDelete();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        if(leftPageButton.getTrigger())
            subPageIndex();
        if(rightPageButton.getTrigger())
            addPageIndex();
    }

    // 선택 콜벡 함수
    public void select(T selectedIcon){ this.selectedIcon = selectedIcon; }
    public T getSelectedIcon() { return selectedIcon; }

    // 페이지가 바뀔때 버튼들의 정보를 갱신해주는 함수.
    protected void updatePage(){
        int nSelectButton = selectButtons.size();
        int startIndex = nSelectButton * pageIndex;
        for(SelectButton<T> selectButton : selectButtons){
            if(startIndex < icons.size())
                selectButton.setMyIcon(icons.get(startIndex));
            else
                selectButton.setMyIcon(null);
            ++startIndex;
        }
        updatePageText();
    }
    protected void updatePageText(){
        pageText.setText((pageIndex + 1) + "/" + (getMaxPageIndex() + 1));
    }

    // getter, setter
    public int getPageIndex() {
        return pageIndex;
    }
    public void addPageIndex() {
        if(pageIndex < getMaxPageIndex()){
            ++this.pageIndex;
            updatePage();
        }
    }
    public void subPageIndex() {
        if(0 < pageIndex){
            --this.pageIndex;
            updatePage();
        }
    }

    public int getMaxPageIndex(){
        return (int)(Math.ceil(icons.size() / selectButtons.size()));
    }

    //////////////////////// SelectInventory에 사용될 버튼 클래스 //////////////////////
    class SelectButton<T extends IIcon> extends SpriteButton {
        protected SelectInventory targetInventory;
        protected T myIcon;

        public SelectButton(SelectInventory targetInventory, T icon, float cx, float cy, float width, float height) {
            super(new Sprite.Builder(null, cx, cy, width, height));
            this.targetInventory = targetInventory;
            setMyIcon(icon);
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

        // getter, setter
        public T getMyIcon() {
            return myIcon;
        }
        public void setMyIcon(T myIcon) {
            this.myIcon = myIcon;
            setBitmap(myIcon == null ? null : myIcon.getIcon());
        }
    }

}
