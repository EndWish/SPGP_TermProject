package tukorea2018180009.ac.kr.example.equipmentcollector.Scenes;

import android.graphics.Canvas;

import tukorea2018180009.ac.kr.example.equipmentcollector.UI.AdventurerInventoryButton;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;

public class VillageScene extends BaseScene{

    @Override
    public void init() {
        super.init();
        Sprite backgroundObj = new Sprite
                .Builder(R.mipmap.jpg_village_background, 0.f,0.f,16.f,9.f)
                .build();
        add(backgroundObj);

        AdventurerInventoryButton adventurerInventoryButton = new AdventurerInventoryButton(1.5f, 7.5f);
        add(adventurerInventoryButton);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

}
