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
        super(new Sprite.Builder(R.mipmap.png_black_white_frame_512x256, 100, 100, 1400, 700));
        // 용사 리스트들을 출력하는 inventory를 생성한다.
        selectInventory  = new SelectInventory<Adventurer>(UserInfo.getInstance().getAdventurers(),
                50f, 50f, 600, 600, 4, 100);
        addChild(selectInventory);

        profileImage = new Sprite(new Builder(0, 700.0f, 50f, 20, 30));
        addChild(profileImage);

        statusTexts = new ArrayList<>();
        for(Status.Type type : Status.Type.values()) {
            int index = type.ordinal();
            float offsetx = (int)(index / 8) * 250f;
            float offsety = index % 8 * 50f;

            StatusText statusText = new StatusText(null, type, 930f + offsetx, 50f + offsety, 50f);
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
