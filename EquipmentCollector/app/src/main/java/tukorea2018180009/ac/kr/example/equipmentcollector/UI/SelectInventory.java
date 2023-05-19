package tukorea2018180009.ac.kr.example.equipmentcollector.UI;

import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Adventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.Equipment.Equipment;
import tukorea2018180009.ac.kr.example.equipmentcollector.ExpenditionAreaInfo.ExpeditionAreaInfo;
import tukorea2018180009.ac.kr.example.equipmentcollector.IIcon;
import tukorea2018180009.ac.kr.example.equipmentcollector.Object;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Scenes.BaseScene;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.Text.Text;

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
                                                .setPivot(PivotType.rightTop));
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

        updatePage();

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

    public void setIcons(ArrayList<T> icons){
        this.icons = icons;
        updatePage();
    }

    public int getMaxPageIndex(){
        return (int)(Math.ceil(icons.size() / selectButtons.size()));
    }

    //////////////////////// SelectInventory에 사용될 버튼 클래스 //////////////////////
    class SelectButton<T extends IIcon> extends SpriteButton {
        protected SelectInventory targetInventory;
        protected T myIcon;
        protected Text childUpgradeLevelText, childExpeditionAreaNameText;

        public SelectButton(SelectInventory targetInventory, T icon, float cx, float cy, float width, float height) {
            super(new Sprite.Builder(null, cx, cy, width, height));
            this.targetInventory = targetInventory;
            // 장비일 경우 강화수치를 나타내주기 위해 따로 Text를 추가한다.
            childUpgradeLevelText = new Text(width, 0, "", width / 3f, width, Color.YELLOW, Paint.Align.RIGHT);
            addChild(childUpgradeLevelText);
            // 탐험지역 선택 버튼일 경우 text를 추가한다.
            childExpeditionAreaNameText = new Text(width / 2f, height / 3, "", width / 5f, width, Color.RED, Paint.Align.CENTER);
            addChild(childExpeditionAreaNameText);

            setMyIcon(icon);
        }

        @Override
        public void setDelete() {
            super.setDelete();
            targetInventory = null;
            myIcon = null;
            childUpgradeLevelText = null;
            childExpeditionAreaNameText = null;
        }

        @Override
        public void clickDown() {
            super.clickDown();
            if(myIcon != null && myIcon instanceof Adventurer)
                BaseScene.getTopScene().setHand((Object)myIcon);
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

            // 장비 아이콘일 경우 강화수치를 표시한다.
            childUpgradeLevelText.setText("");
            if(myIcon instanceof Equipment){
                childUpgradeLevelText.setText("+" + ((Equipment) myIcon).getUpgradeLevel());
            }

            // 탐험지역 선택 버튼일 경우 text를 표시한다.
            childExpeditionAreaNameText.setText("");
            if(myIcon instanceof ExpeditionAreaInfo){
                childExpeditionAreaNameText.setText(((ExpeditionAreaInfo) myIcon).getName());
            }
        }
    }

}
