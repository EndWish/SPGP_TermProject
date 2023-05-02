package tukorea2018180009.ac.kr.example.equipmentcollector.Scenes;

import android.graphics.Canvas;

import java.util.ArrayList;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Adventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.ExpenditionAreaInfo.ExpeditionAreaInfo;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.AdventurerUI.AdventurerInventoryButton;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI.BattleProfile;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.ExpeditionUI.ExpeditionSelectWindowOpenButton;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.TriggerButton;
import tukorea2018180009.ac.kr.example.equipmentcollector.UserInfo;

public class BattleScene extends BaseScene {

    ExpeditionAreaInfo currentExpeditonAreaInfo;
    BattlePage battlePage;
    BattleProfile skillCaster;
    int wave = 0;

    ArrayList<BattleProfile> myParty = new ArrayList<>();
    ArrayList<BattleProfile> enemyParty;

    TriggerButton nextBattleButton;

    @Override
    public void init() {
        super.init();

        currentExpeditonAreaInfo = ExpeditionAreaInfo.getCurrentExpeditionAreaInfo();
        battlePage = BattlePage.waitNextBattle;

        // 배경을 생성한다.
        add(new Sprite( new Sprite.Builder(currentExpeditonAreaInfo.getIcon(),
                        0,0,1600, 900)));

        // 다음 웨이브 버튼을 생성한다.
        nextBattleButton = new TriggerButton(new Sprite.Builder(R.mipmap.png_button_right_arrow, 1200, 450, 200, 200).setPivot(Sprite.PivotType.center));
        add(nextBattleButton);

        // [추가]웨이브를 나타내는 텍스트를 생성한다.

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

    }



    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        //UserInfo.getInstance().update();

        switch (battlePage){
            case waitNextBattle:
                if(nextBattleButton.getTrigger()){
                    nextBattleButton.setVisible(false);
                    battlePage = BattlePage.tick;
                    ++wave;
                    // [추가]적을 생성한다. Forest1_ExpeditionAreaInfo를 전역에 저장해두고 사용하자.
                    loadEnemiesFromExpeditionAreaInfo(wave);
                }
                break;

            case tick:
                // [추가]모든 유닛의 틱(스킬 게이지 증가 + 능력치 업데이트 + 버프/디버프 처리)을 수행한다.

                // [추가]스킬게이지가 꽉찬 유닛이 있는지 확인한다.
                    // [추가]스킬 게이지가 꽉찬 유닛 중 가장 게이지가 큰 유닛이 선공을 가지도록 한다.

                break;

            case enemyUseSkill:
                break;

            case pickTarget:
                break;

            case skillAnimation:
                break;
        }

    }

    protected void RemoveEnemyParty(){
        if(enemyParty != null){
            for(BattleProfile battleProfile : enemyParty)
                battleProfile.setDelete();
            enemyParty = null;
        }
    }

    protected void loadEnemiesFromExpeditionAreaInfo(int wave){
        // 기존의 적 파티를 삭제한다.
        RemoveEnemyParty();

        // 새로운 적 파티를 얻어온다.
        enemyParty = currentExpeditonAreaInfo.getEnemies(wave);

        // 초기화 작업을 해준다.
        int row = 0;
        for(BattleProfile battleProfile : enemyParty){
            add(battleProfile); // 자식으로 추가.

            // 위치를 설정해준다.
            battleProfile.setWidth(150);
            battleProfile.setHeight(75 * 3);
            battleProfile.setX(800 + 100 + 180 * row);
            battleProfile.setY(450);
            battleProfile.setImgFlipx(true);
            ++row;
        }

    }

    public enum BattlePage {
        waitNextBattle, tick, enemyUseSkill, pickTarget, skillAnimation
    }
}
