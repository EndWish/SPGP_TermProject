package tukorea2018180009.ac.kr.example.equipmentcollector.Scenes;

import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Adventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.ExpenditionAreaInfo.ExpeditionAreaInfo;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Skills.Skill;
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
    Skill castSkill;
    int wave = 0;

    ArrayList<BattleProfile> myParty = new ArrayList<>();
    ArrayList<BattleProfile> enemyParty;

    TriggerButton nextBattleButton;

    float waitPageTimer = 0;

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

        // [추가]죽은 유닛이 있는지 확인해서 삭제한다.

        switch (battlePage){
            case waitNextBattle:
                if(nextBattleButton.getTrigger()){
                    nextBattleButton.setVisible(false);
                    battlePage = BattlePage.tick;
                    ++wave;
                    // 적을 생성한다. Forest1_ExpeditionAreaInfo를 전역에 저장해두고 사용하자.
                    loadEnemiesFromExpeditionAreaInfo(wave);
                }
                break;

            case tick:
                Log.d("update", "tick");
                // 스킬게이지가 꽉찬 유닛이 있는지 확인한다.
                skillCaster = null;
                float maxGauge = 100;

                for(BattleProfile battleProfile : enemyParty){
                    float gauge = battleProfile.getAdventurer().getMaxSkillGauge();
                    if(maxGauge <= gauge){
                        skillCaster = battleProfile;
                    }
                }
                for(BattleProfile battleProfile : myParty){
                    float gauge = battleProfile.getAdventurer().getMaxSkillGauge();
                    if(maxGauge <= gauge){
                        skillCaster = battleProfile;
                    }
                }

                // 스킬 게이지가 꽉찬 유닛(중에서 게이지가 가장 큰 유닛)이 있을 경우
                if(skillCaster != null) {
                    castSkill = skillCaster.getAdventurer().getMaxGaugedSkill();
                    castSkill.setGauge(0);  // 사용하게될 스킬의 게이지를 0으로 만든다.
                    // [추가] 아군일 경우 pickTarget 부분으로 넘어간다.
                    if(skillCaster.isAlly()){
                        ArrayList<BattleProfile> targets = castSkill.getAttackableTarget(enemyParty);
                        // 공격할 수 있는 적이 없을 경우 잠시 대기(Wait 배틀 페이지로 이동)했다가 넘어가도록 한다.
                        if(targets.size() == 0){
                            battlePage = BattlePage.wait;
                            waitPageTimer = 1.0f;
                        }
                        // 공격할 수 있는 대상이 존재할 경우 (어떤 적을 공격할 수 있는지 표시하고 다음 배틀페이지로 넘어간다.)
                        else{
                            battlePage = BattlePage.pickTarget;
                            for(BattleProfile target : targets){
                                target.setAttackableTarget(true);
                            }
                        }
                    }
                    // [추가] 적일경우 enemyUseSkill 부분으로 넘어간다.
                    else{
                        battlePage = BattlePage.enemyUseSkill;
                    }
                }
                // 스킬 게이지가 꽉찬 유닛이 없을 경우 (스킬 게이지가 꽉찬 유닛이 여러명일 경우 틱을 진행하면 안되기 때문)
                else{
                    // 모든 유닛의 틱(스킬 게이지 증가 + 능력치 업데이트 + 버프/디버프 처리)을 수행한다.
                    for(BattleProfile battleProfile : myParty)
                        battleProfile.getAdventurer().advanceTick();
                    for(BattleProfile battleProfile : enemyParty)
                        battleProfile.getAdventurer().advanceTick();
                }

                break;

            case enemyUseSkill:
                Log.d("update", "enemyUseSkill");
                break;

            case pickTarget:
                Log.d("update", "pickTarget");
                break;

            case skillAnimation:
                Log.d("update", "skillAnimation");
                break;

            case wait:
                Log.d("update", "wait");
                waitPageTimer -= deltaTime;
                // 기다르는 시간이 지났을 경우 다시 TickPage로 돌아간다.
                if(waitPageTimer <= 0) {
                    battlePage = BattlePage.tick;
                    //[추가]공격할 수 있는 대상이 없어서 wiatPage에 왔다면 문구로 알려준다.
                }
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
            battleProfile.setAlly(false);
            ++row;
        }

    }

    public enum BattlePage {
        waitNextBattle, tick, enemyUseSkill, pickTarget, skillAnimation, wait,
    }
}
