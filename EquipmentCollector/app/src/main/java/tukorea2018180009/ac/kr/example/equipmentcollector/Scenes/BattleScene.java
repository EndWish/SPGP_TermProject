package tukorea2018180009.ac.kr.example.equipmentcollector.Scenes;

import android.graphics.Canvas;

import java.util.ArrayList;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Adventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.AdventurerUI.AdventurerInventoryButton;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI.BattleProfile;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.ExpeditionUI.ExpeditionSelectWindowOpenButton;
import tukorea2018180009.ac.kr.example.equipmentcollector.UserInfo;

public class BattleScene extends BaseScene {
    ArrayList<BattleProfile> myParty = new ArrayList<>();
    ArrayList<BattleProfile> enemyParty = new ArrayList<>();

    @Override
    public void init() {
        super.init();

        // 파티에 있는 모험가를 myParty배열에 추가한다.
       for(int i = 3; i >= 0; --i){
           Adventurer adventurer = UserInfo.getInstance().getPartyAdventerer(i);
           if(adventurer != null) {
               BattleProfile battleProfile = new BattleProfile(adventurer, 0, 0, 0);
               myParty.add(battleProfile);
               add(battleProfile);
           }
       }

        // myParty의 객체들의 위치를 초기화 해준다.
        int row = 0;
        for(BattleProfile battleProfile : myParty){
            battleProfile.setWidth(150);
            battleProfile.setHeight(75 * 3);
            battleProfile.setX(800 - 100 - 180 * row);
            battleProfile.setY(450);
            ++row;
        }

        // 다음 웨이브 버튼을 생성한다.
        

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
