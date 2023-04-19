package tukorea2018180009.ac.kr.example.equipmentcollector.Equipment;

import android.graphics.Bitmap;

import tukorea2018180009.ac.kr.example.equipmentcollector.BitmapPool;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;

public class PlainSwordEquipment extends Equipment {
    private static final String name = "무난한 검";
    private static final Bitmap icon = BitmapPool.get(R.mipmap.png_equipment_plain_sword);


    
    // 장비의 정보를 리턴해주는 함수
    @Override
    public Grade getGrade() {
        return Grade.Common;
    }
    @Override
    public String getDesc() {
        return "<무난한 검> : 커먼\n 베기 공경력을 " + (upgradeLevel + 3) +" 올려준다.";
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public Bitmap getIcon() {
        return icon;
    }
}
