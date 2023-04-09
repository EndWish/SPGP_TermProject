package tukorea2018180009.ac.kr.example.equipmentcollector.Buttons;

import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;

public class AdventurerInventoryButton extends Sprite {
    private static final String TAG = AdventurerInventoryButton.class.getSimpleName();

    public AdventurerInventoryButton(float cx, float cy){
        super(new Sprite
                .Builder(R.mipmap.png_button_inventory_adventurer, cx, cy, 2, 2)
                .setPivotCenter());
    }

    @Override
    public void actionDownReaction(float mx, float my){
        super.actionDownReaction(mx, my);
        // 용사 리스트들을 출력하는 inventory를 생성한다.
    }

}
