package tukorea2018180009.ac.kr.example.equipmentcollector.Scenes;

import android.graphics.Canvas;

import tukorea2018180009.ac.kr.example.equipmentcollector.UI.AdventurerUI.AdventurerInventoryButton;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.UserInfo;

public class VillageScene extends BaseScene{

    @Override
    public void init() {
        super.init();
        Sprite backgroundObj = new Sprite
                .Builder(R.mipmap.jpg_village_background, 0.f,0.f,1600.f,900.f)
                .build();
        add(backgroundObj);

        AdventurerInventoryButton adventurerInventoryButton = new AdventurerInventoryButton(150f, 750f);
        add(adventurerInventoryButton);
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
