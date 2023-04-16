package tukorea2018180009.ac.kr.example.equipmentcollector.UI;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.Log;

import java.util.ArrayList;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Adventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Status;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.Text;
import tukorea2018180009.ac.kr.example.equipmentcollector.UserInfo;

public class AdventurerInventory extends Sprite {
    SelectInventory<Adventurer> selectInventory;
    Adventurer adventurer;
    
    // 화면에 보여줄 정보들
    Sprite profileImage;
    ArrayList<StatusText> statusTexts;

    public AdventurerInventory() {
        super(new Sprite.Builder(R.mipmap.png_black_white_frame_512x256, 1, 1, 14, 7));
        // 용사 리스트들을 출력하는 inventory를 생성한다.
        selectInventory  = new SelectInventory<Adventurer>(UserInfo.getInstance().getAdventurers(),
                0.5f, 0.5f, 6, 6, 4);
        addChild(selectInventory);

        profileImage = new Sprite(new Builder(0, 7.0f, 0.5f, 2, 3));
        addChild(profileImage);

        statusTexts = new ArrayList<>();
        for(Status.Type type : Status.Type.values()) {
            int index = type.ordinal();
            float offsetx = (int)(index / 8) * 2.5f;
            float offsety = index % 8 * 0.5f;

            StatusText statusText = new StatusText(null, type, 9.3f + offsetx, 0.5f + offsety, 0.5f);
            statusTexts.add(statusText);
            addChild(statusText);
        }
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

            for(StatusText statusText: statusTexts){
                statusText.setStatus(adventurer.getTotalStatus());
            }
        }
        super.update(deltaTime);
    }

}
