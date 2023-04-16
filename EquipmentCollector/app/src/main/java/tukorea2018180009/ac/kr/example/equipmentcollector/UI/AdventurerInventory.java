package tukorea2018180009.ac.kr.example.equipmentcollector.UI;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.Log;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Adventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.Text;
import tukorea2018180009.ac.kr.example.equipmentcollector.UserInfo;

public class AdventurerInventory extends Sprite {
    SelectInventory<Adventurer> selectInventory;
    Adventurer adventurer;
    Sprite profileImage;

    public AdventurerInventory() {
        super(new Sprite.Builder(R.mipmap.png_black_white_frame_512x256, 1, 1, 14, 7));
        // 용사 리스트들을 출력하는 inventory를 생성한다.
        selectInventory  = new SelectInventory<Adventurer>(UserInfo.getInstance().getAdventurers(),
                0.5f, 0.5f, 6, 6, 4);
        addChild(selectInventory);

        profileImage = new Sprite(new Builder(0, 9.5f, 0.5f, 2, 3));
        addChild(profileImage);

    }

    @Override
    public void setDelete() {
        selectInventory = null;
        adventurer = null;
        profileImage = null;
        super.setDelete();
    }

    @Override
    public void update(float deltaTime) {
        // 선택된 모험가가 달라질 경우
        if(adventurer != selectInventory.getSelectedIcon()){
            adventurer = selectInventory.getSelectedIcon();

            // 선택된 모험가의 정보를 출력한다.
            profileImage.setBitmap(adventurer.getProfile());
            Log.d("?", "?");
        }
        super.update(deltaTime);
    }

}
