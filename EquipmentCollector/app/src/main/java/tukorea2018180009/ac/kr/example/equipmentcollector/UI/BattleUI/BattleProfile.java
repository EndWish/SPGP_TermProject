package tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Adventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Status;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.SpriteButton;

public class BattleProfile extends SpriteButton {
    Adventurer adventurer;
    float hp;
    // 게이지 추가.

    public BattleProfile(Adventurer adventurer, float cx, float cy, float width) {
        super(new Builder(adventurer.getProfile(), cx, cy, width, width * 1.5f)
                .setPivotCenter());

        this.adventurer = adventurer;

        // 능력치 초기화 작업을 한다.
        adventurer.initForBattle();
    }

}
