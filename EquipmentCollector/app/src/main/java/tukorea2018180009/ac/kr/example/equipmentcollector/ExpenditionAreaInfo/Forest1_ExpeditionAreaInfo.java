package tukorea2018180009.ac.kr.example.equipmentcollector.ExpenditionAreaInfo;

import android.graphics.Bitmap;

import tukorea2018180009.ac.kr.example.equipmentcollector.Memory.BitmapPool;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;

public class Forest1_ExpeditionAreaInfo extends ExpeditionAreaInfo {

    // 생성자
    Forest1_ExpeditionAreaInfo() {
        this.areaType = AreaType.Forest1;
    }

    @Override
    public Bitmap getIcon() {
        return BitmapPool.get(R.mipmap.jpg_forest_background);
    }

    @Override
    public String getName() {
        return "Forest1";
    }
}
