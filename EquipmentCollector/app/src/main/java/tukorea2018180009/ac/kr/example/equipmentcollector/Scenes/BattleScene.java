package tukorea2018180009.ac.kr.example.equipmentcollector.Scenes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Adventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.Equipment.Equipment;
import tukorea2018180009.ac.kr.example.equipmentcollector.ExpenditionAreaInfo.ExpeditionAreaInfo;
import tukorea2018180009.ac.kr.example.equipmentcollector.GameObject;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Skills.Attack;
import tukorea2018180009.ac.kr.example.equipmentcollector.Skills.Skill;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI.BattleProfile;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI.ClearRewardsCheckUI;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.NotificationMessage;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.Text.Text;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.TriggerButton;
import tukorea2018180009.ac.kr.example.equipmentcollector.UserInfo;

public class BattleScene extends BaseScene {

    ExpeditionAreaInfo currentExpeditonAreaInfo;
    BattlePage battlePage;
    BattleProfile skillCaster;
    Skill castSkill;
    int wave = 0;

    ArrayList<Equipment> rewardEquipments = new ArrayList<>();
    int rewardGold = 0;

    ArrayList<GameObject> attacks = new ArrayList<>();  // 공격의 개수를 판별하기 위한 배열

    ArrayList<BattleProfile> myParty = new ArrayList<>();
    ArrayList<BattleProfile> enemyParty;

    TriggerButton nextBattleButton;
    Text waveText;
    NotificationMessage failMessage;
    ClearRewardsCheckUI clearMessage;

    BattlePage nextBattlePageAfterWaiting = BattlePage.tick;
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
        waveText = new Text(1200, 600, "wave: 0/10", 75, 400, Color.BLACK, Paint.Align.CENTER);
        add(waveText);

        // [추가]웨이브를 나타내는 텍스트를 생성한다.

        // 파티에 있는 모험가를 myParty배열에 추가한다.
       for(int i = 3; i >= 0; --i){
           Adventurer adventurer = UserInfo.getInstance().getPartyAdventerer(i);
           if(adventurer != null) {
               BattleProfile battleProfile = new BattleProfile(adventurer, 0, 0, 150);
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

        // 삭제된 오브젝트들의 연결을 끊는다.
        if(failMessage != null && failMessage.isDeleted())
            failMessage = null;
        if(clearMessage != null && clearMessage.isDeleted())
            clearMessage = null;
        if(myParty != null)
            myParty.removeIf(battleProfile -> battleProfile == null || battleProfile.isDeleted());
        if(enemyParty != null){
            // 적이 죽어서 삭제될경우 보상을 추가한다.
            for(BattleProfile battleProfile : enemyParty){
                if(battleProfile != null && battleProfile.isDeleted()){
                    rewardEquipments.addAll(battleProfile.getAdventurer().getRewardEquipments());
                    rewardGold += battleProfile.getAdventurer().getRewardGold();
                }
            }
            enemyParty.removeIf(battleProfile -> battleProfile == null || battleProfile.isDeleted());
        }

        switch (battlePage){

            case waitNextBattle:
                if(nextBattleButton.getTrigger()){
                    nextBattleButton.setVisible(false);
                    nextBattleButton.setClickable(false);
                    waveText.setVisible(false);
                    battlePage = BattlePage.tick;
                    ++wave;
                    // 적을 생성한다. Forest1_ExpeditionAreaInfo를 전역에 저장해두고 사용하자.
                    loadEnemiesFromExpeditionAreaInfo(wave);
                }
                break;

            case tick:
                // 현재 웨이브의 적을 모두 처치했는지 확인한다.
                if(enemyParty.isEmpty()){
                    // 현재 웨이브가 마지막 웨이브가 아닐경우 waitNextBattle 배틀 페이지로 이동한다.
                    if(wave < 10){
                        battlePage = BattlePage.waitNextBattle;
                        nextBattleButton.setVisible(true);
                        nextBattleButton.setClickable(true);
                        waveText.setText("wave: " + wave + "/10");
                        waveText.setVisible(true);
                        break;
                    }
                    // [추가]마지막 웨이브까지 적을 처치했을 경우 보상탭으로 가도록 한다.
                    else{
                        battlePage = BattlePage.wait;
                        nextBattlePageAfterWaiting = BattlePage.clear;
                        waitPageTimer = 0.5f;
                    }
                    return;
                }

                // 나의 파티가 전멸했을 경우
                if(myParty.isEmpty()){
                    battlePage = BattlePage.wait;
                    nextBattlePageAfterWaiting = BattlePage.fail;
                    waitPageTimer = 0.5f;
                    return;
                }

                // 스킬게이지가 꽉찬 유닛이 있는지 확인한다.
                skillCaster = findSkillCaster();

                // 스킬 게이지가 꽉찬 유닛(중에서 게이지가 가장 큰 유닛)이 있을 경우
                if(skillCaster != null) {
                    skillCaster.setSkillCasting(true);
                    castSkill = skillCaster.getAdventurer().getMaxGaugedSkill();
                    castSkill.setGauge(0);  // 사용하게될 스킬의 게이지를 0으로 만든다.
                    // 아군일 경우 pickTarget 부분으로 넘어간다.
                    if(skillCaster.isAlly()){
                        ArrayList<BattleProfile> targets = castSkill.getAttackableTarget(enemyParty, myParty);
                        // 공격할 수 있는 적이 없을 경우 잠시 대기(Wait 배틀 페이지로 이동)했다가 넘어가도록 한다.
                        if(targets.size() == 0){
                            battlePage = BattlePage.wait;
                            nextBattlePageAfterWaiting = BattlePage.tick;
                            waitPageTimer = 1.0f;
                        }
                        // 공격할 수 있는 대상이 존재할 경우
                        else{
                            // 어떤 적을 공격할 수 있는지 표시한다.
                            for(BattleProfile target : targets){
                                target.setAttackableTarget(true);
                                target.getTrigger();    // 이전에 눌렸던 버퍼를 해제해켜 준다.
                            }
                            // 다음 배틀페이지로 넘어간다
                            battlePage = BattlePage.pickTarget;
                        }
                    }
                    // 적일경우 enemyUseSkill 부분으로 넘어간다.
                    else{
                        ArrayList<BattleProfile> targets = castSkill.getAttackableTarget(myParty, enemyParty);
                        // 공격할 수 있는 적이 없을 경우 잠시 대기(Wait 배틀 페이지로 이동)했다가 넘어가도록 한다.
                        if(targets.size() == 0){
                            battlePage = BattlePage.wait;
                            nextBattlePageAfterWaiting = BattlePage.tick;
                            waitPageTimer = 1.0f;
                        }
                        // 공격할 수 있는 대상이 존재할 경우
                        else{
                            // 어떤 적을 공격할 수 있는지 표시한다.
                            for(BattleProfile target : targets){
                                target.setAttackableTarget(true);
                                target.getTrigger();    // 이전에 눌렸던 버퍼를 해제해켜 준다.
                            }
                            // 잠시 대기시간을 가진후 다음 배틀페이지로 넘어간다
                            battlePage = BattlePage.wait;
                            nextBattlePageAfterWaiting = BattlePage.enemyUseSkill;
                            waitPageTimer = 0.5f;
                        }

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
                // 선택할 수 있는 대상만 추려서 targetParty에 넣는다.
                ArrayList<BattleProfile> targetParty = new ArrayList<>();
                targetParty.addAll(myParty);
                targetParty.addAll(enemyParty);
                targetParty.removeIf(battleProfile -> !battleProfile.isAttackableTarget());

                // 선택 대상중 랜덤으로 한명을 선택해서 공격한다.
                int selectIndex = (int)(Math.random() * targetParty.size());
                addAttacks( castSkill.createAttacks(skillCaster, targetParty.get(selectIndex)) );

                // 스킬 캐스터를 해제 시킨다.
                skillCaster.setSkillCasting(false);
                skillCaster = null;
                castSkill = null;

                // 공격할 수 있는 대상들을 전부 해제시킨다.
                for(BattleProfile battleProfile : targetParty)
                    battleProfile.setAttackableTarget(false);

                // 배틀 페이지를 변경시킨다.
                battlePage = BattlePage.skillAnimation;

                break;

            case pickTarget:
                boolean process = false;    // 여러명을 동시에 누른 경우 한명만 처리하도록 하기 위한 변수
                ArrayList<BattleProfile> allParty = new ArrayList<>();
                allParty.addAll(myParty);
                allParty.addAll(enemyParty);

                for (BattleProfile battleProfile : allParty){
                    if(battleProfile.getTrigger() && battleProfile.isAttackableTarget() && !process){
                        addAttacks( castSkill.createAttacks(skillCaster, battleProfile) );
                        process = true;
                    }
                }
                if(process){
                    // 스킬 캐스터를 해제 시킨다.
                    skillCaster.setSkillCasting(false);
                    skillCaster = null;
                    castSkill = null;
                    // 공격할 수 있는 대상들을 전부 해제시킨다.
                    for(BattleProfile battleProfile : allParty)
                        battleProfile.setAttackableTarget(false);

                    // 배틀 페이지를 변경시킨다.
                    battlePage = BattlePage.skillAnimation;
                }
                break;

            case skillAnimation:
                attacks.removeIf(attack -> attack == null || attack.isDeleted());
                if(attacks.size() == 0){
                    battlePage = BattlePage.tick;
                }
                break;

            case wait:
                waitPageTimer -= deltaTime;
                // 기다리는 시간이 지났을 경우 다시 TickPage로 돌아간다.
                if(waitPageTimer <= 0) {
                    //
                    if(nextBattlePageAfterWaiting == BattlePage.fail){
                        failMessage = new NotificationMessage("탐험에 실패하였습니다.\n모험가와 장비를 강화하여 다시 도전하십시오.");
                        addPost(failMessage);
                    }
                    else if(nextBattlePageAfterWaiting == BattlePage.clear){
                        clearMessage = new ClearRewardsCheckUI(rewardEquipments, rewardGold);
                        addPost(clearMessage);
                    }

                    battlePage = nextBattlePageAfterWaiting;
                    //[추가]공격할 수 있는 대상이 없어서 wiatPage에 왔다면 문구로 알려준다.


                }
                break;

            case fail:
                if(failMessage == null) {
                    popScene();
                }
                break;

            case clear:
                if(clearMessage == null) {
                    UserInfo.getInstance().addGold(rewardGold);
                    UserInfo.getInstance().getEquipments().addAll(rewardEquipments);
                    popScene();
                }
                break;
        }

    }
    protected BattleProfile findSkillCaster(){
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
        return skillCaster;
    }

    public void addAttacks(ArrayList<Attack> attacks){
        for(Attack gameObject : attacks){
            addAttack(gameObject);
        }
    }
    public void addAttack(Attack attack){
        if(attack != null) {
            addPost(attack);
            attacks.add(attack);
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
            battleProfile.setX(800 + 100 + 180 * row);
            battleProfile.setY(450);
            battleProfile.setImgFlipx(true);
            battleProfile.setAlly(false);
            ++row;
        }

    }

    public int getBattleProfileIndex(BattleProfile target, ArrayList<BattleProfile> party) {
        // 해당 배틀 프로필이 파티에서 몇번째 인덱스인지를 반환해준다.
        int i = 0;
        for(BattleProfile battleProfile : party){
            if(battleProfile == target)
                return i;
            ++i;
        }
        // 그 파티에 없을 경우 -1을 반환한다.
        return -1;
    }

    // getter, setter
    public ArrayList<BattleProfile> getMyParty() {
        return myParty;
    }
    public ArrayList<BattleProfile> getEnemyParty() {
        return enemyParty;
    }

    public enum BattlePage {
        waitNextBattle, tick, enemyUseSkill, pickTarget, skillAnimation, wait, clear, fail,
    }
}
