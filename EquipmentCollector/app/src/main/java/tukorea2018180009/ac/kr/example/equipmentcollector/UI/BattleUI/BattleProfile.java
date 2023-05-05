package tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Adventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Status;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.SpriteButton;

public class BattleProfile extends SpriteButton {
    Adventurer adventurer;
    boolean ally;
    boolean attackableTarget;

    Sprite attackableTargetFrame;

    public BattleProfile(Adventurer adventurer, float cx, float cy, float width) {
        super(new Builder(adventurer.getProfile(), cx, cy, width, width * 1.5f)
                .setPivot(PivotType.center));

        this.adventurer = adventurer;
        this.ally = true;
        this.attackableTarget = false;

        // 능력치 초기화 작업을 한다.
        adventurer.initForBattle();

        // 공격대상이 될수 있을 경우를 표시하기 위한 스프라이트를 추가한다.
        attackableTargetFrame = new Sprite(new Builder(R.mipmap.png_black_white_frame_256x512, 0, 0, this.width, this.height)
                .setPivot(getPivotType()));
        attackableTargetFrame.setVisible(false);
        addChild(attackableTargetFrame);

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
    }
    @Override
    public void setHeight(float height) {
        super.setHeight(height);
        attackableTargetFrame.setHeight(height);
    }

    public boolean isAttackableTarget() {
        return attackableTarget;
    }
    public void setAttackableTarget(boolean attackableTarget) {
        this.attackableTarget = attackableTarget;
        attackableTargetFrame.setVisible(attackableTarget);
    }
}
