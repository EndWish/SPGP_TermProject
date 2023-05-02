package tukorea2018180009.ac.kr.example.equipmentcollector.UI.AdventurerUI;

import java.util.Base64;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Adventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.Object;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Scenes.BaseScene;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.SpriteButton;
import tukorea2018180009.ac.kr.example.equipmentcollector.UserInfo;

public class PartySettingBarUI extends Sprite {
    boolean alwaysVisible;

    // 생성자
    public PartySettingBarUI() {
        super(new Builder(R.mipmap.png_black_white_frame_512x256, 800, 900 - 75, 525, 150)
                .setPivot(PivotType.center));

        alwaysVisible = false;

        for(int i = 0; i < 4; ++i){
            addChild(new AdventurerIcon(i, new Builder(0, -187.5f + 125 * i, 0f, 100f, 100f).setPivot(PivotType.center)));
        }
    }

    @Override
    public void setDelete() {
        super.setDelete();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        if(alwaysVisible == false){
            Object  object = BaseScene.getTopScene().getHand();
            if(object instanceof Adventurer)
                setVisible(true);
            else
                setVisible(false);
        } else {
            setVisible(true);
        }

    }

    // getter, setter
    public boolean isAlwaysVisible() {
        return alwaysVisible;
    }

    public void setAlwaysVisible(boolean alwaysVisible) {
        this.alwaysVisible = alwaysVisible;
    }

    /// AdventurerIcon 클래스 ////////////////////////////////////////////////////
    class AdventurerIcon extends SpriteButton {
        int partyIndex;

        public AdventurerIcon(int partyIndex, Builder builder) {
            super(builder);
            this.partyIndex = partyIndex;
        }

        @Override
        public void update(float deltaTime) {
            super.update(deltaTime);

            Adventurer adventurer = UserInfo.getInstance().getPartyAdventerer(partyIndex);
            if(adventurer != null)
                setBitmap(adventurer.getIcon());
            else
                setBitmap(R.mipmap.png_black_white_frame_256x256);
        }

        @Override
        public void clickUp() {
            Object object = BaseScene.getTopScene().getHand();
            if(object instanceof Adventurer){
                UserInfo.getInstance().setPartyAdventerer(partyIndex, (Adventurer)(object));
            }
            super.clickUp();
        }
    }

}
