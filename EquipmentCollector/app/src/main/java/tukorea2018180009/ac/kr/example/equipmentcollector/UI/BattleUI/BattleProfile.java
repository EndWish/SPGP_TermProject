package tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.ArrayList;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Adventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Status;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Skills.Skill;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.GaugeSprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.SpriteButton;

public class BattleProfile extends SpriteButton {
    Adventurer adventurer;
    boolean ally;
    boolean attackableTarget;

    Sprite attackableTargetFrame;
    GaugeSprite hpGauge;
    ArrayList<GaugeSprite> skillGauges;
    ArrayList<Skill> skills;

    // 생성자
    public BattleProfile(Adventurer adventurer, float cx, float cy, float width) {
        super(new Builder(adventurer.getProfile(), cx, cy, width, width * 1.5f)
                .setPivot(PivotType.center));

        this.adventurer = adventurer;
        skills = adventurer.getSkills();
        this.ally = true;
        this.attackableTarget = false;

        // 능력치 초기화 작업을 한다.
        adventurer.initForBattle();

        // 공격대상이 될수 있을 경우를 표시하기 위한 스프라이트를 추가한다.
        attackableTargetFrame = new Sprite(new Builder(R.mipmap.png_attackable_target_yellow_frame_256x384, 0, 0, this.width, this.height)
                .setPivot(getPivotType()));
        attackableTargetFrame.setVisible(false);
        addChild(attackableTargetFrame);

        /// 게이지 스프라이트를 추가한다.
        // hp 게이지 추가
        hpGauge = new GaugeSprite(new Builder(null, 0, height * 0.6f, this.width, this.height * 0.125f)
                .setPivot(PivotType.center), Color.RED, 1.f);
        addChild(hpGauge);
        // skill 게이지 추가.
        skillGauges = new ArrayList<>();
        for(int i = 0; i < skills.size(); ++i){
            // 스킬 등급에 따라 색상 결정하기
            int fillColor = Color.WHITE;
            switch (skills.get(i).getGrade()){
                case essential: fillColor = Color.WHITE; break;
                case normal: fillColor = Color.YELLOW; break;
                case ultimate: fillColor = Color.MAGENTA; break;
            }
            GaugeSprite skillGauge = new GaugeSprite(new Builder(null, 0, height * (0.8f + i * 0.12f), this.width, this.height * 0.05f)
                    .setPivot(PivotType.center), fillColor, 1.f);

            skillGauges.add(skillGauge);
            addChild(skillGauge);
        }

    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        Status totalStatus = adventurer.getTotalStatus();

        // 게이지 업데이트
        hpGauge.setRatio(adventurer.getHp() / totalStatus.get(Status.Type.hpm));
        for(int i = 0; i < skillGauges.size(); ++i){
            skillGauges.get(i).setRatio(skills.get(i).getGauge() / 100.f);
        }
    }

    // getter, setter
    public Adventurer getAdventurer() {
        return adventurer;
    }
    public void setAdventurer(Adventurer adventurer) {
        this.adventurer = adventurer;
    }
    public boolean isAlly() {
        return ally;
    }
    public void setAlly(boolean ally) {
        this.ally = ally;
    }
    @Override
    public void setWidth(float width) {
        super.setWidth(width);
        attackableTargetFrame.setWidth(width);
        hpGauge.setWidth(width);
        for(GaugeSprite skillGauge : skillGauges){
            skillGauge.setWidth(width);
        }
    }
    @Override
    public void setHeight(float height) {
        super.setHeight(height);
        attackableTargetFrame.setHeight(height);
        hpGauge.setY(height * 0.6f);
        hpGauge.setHeight(height * 0.125f);

        int i = 0;
        for(GaugeSprite skillGauge : skillGauges){
            skillGauge.setY(height * (0.8f + i * 0.12f));
            skillGauge.setHeight(height * 0.05f);
            ++i;
        }
    }

    public boolean isAttackableTarget() {
        return attackableTarget;
    }
    public void setAttackableTarget(boolean attackableTarget) {
        this.attackableTarget = attackableTarget;
        attackableTargetFrame.setVisible(attackableTarget);
    }
}
