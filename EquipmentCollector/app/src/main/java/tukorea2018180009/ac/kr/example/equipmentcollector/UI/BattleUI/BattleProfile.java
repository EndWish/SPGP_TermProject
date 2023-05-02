package tukorea2018180009.ac.kr.example.equipmentcollector.UI.BattleUI;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Adventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Status;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.SpriteButton;

public class BattleProfile extends SpriteButton {
    Adventurer adventurer;

    public BattleProfile(Adventurer adventurer, float cx, float cy, float width) {
        super(new Builder(adventurer.getProfile(), cx, cy, width, width * 1.5f)
                .setPivot(PivotType.center));


        this.adventurer = adventurer;

        // 능력치 초기화 작업을 한다.
        adventurer.initForBattle();
    }

    public Adventurer getAdventurer() {
        return adventurer;
    }
    public void setAdventurer(Adventurer adventurer) {
        this.adventurer = adventurer;
    }

}
