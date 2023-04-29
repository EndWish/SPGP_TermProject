package tukorea2018180009.ac.kr.example.equipmentcollector.Scenes;

import android.graphics.Canvas;

import tukorea2018180009.ac.kr.example.equipmentcollector.UI.AdventurerUI.AdventurerInventoryButton;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.ExpeditionSelectWindowOpenButton;
import tukorea2018180009.ac.kr.example.equipmentcollector.UserInfo;

public class VillageScene extends BaseScene{

    @Override
    public void init() {
        super.init();
        Sprite backgroundObj = new Sprite
                .Builder(R.mipmap.jpg_village_background, 0.f,0.f,1600.f,900.f)
                .build();
        add(backgroundObj);

        // 모험가 인벤토리 오픈 버튼
        AdventurerInventoryButton adventurerInventoryButton = new AdventurerInventoryButton(150f, 750f);
        add(adventurerInventoryButton);

        // 탐험지역 선택창 오픈 버튼
        ExpeditionSelectWindowOpenButton expeditionSelectWindowOpenButton = new ExpeditionSelectWindowOpenButton(1450f, 750f);
        add(expeditionSelectWindowOpenButton);

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        UserInfo.getInstance().update();
    }
}
