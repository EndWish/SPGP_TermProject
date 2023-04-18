package tukorea2018180009.ac.kr.example.equipmentcollector.UI;

import tukorea2018180009.ac.kr.example.equipmentcollector.Scenes.BaseScene;
import tukorea2018180009.ac.kr.example.equipmentcollector.Skills.Skill;

public class SkillButton extends SpriteButton {
    Skill skill;
    Tooltip tooltip;

    public SkillButton(Skill skill, float cx, float cy, float width) {
        super(new Builder(skill.getIcon(), cx, cy, width, width));
        this.skill = skill;


    }

    @Override
    public void clickDown() {
        super.clickDown();
        // 아직 툴팁이 생성되지 않았다면 생성한다.
        if(tooltip == null){
            tooltip = new Tooltip(this.skill.getDesc(), 1500, 50, 50);
            BaseScene.getTopScene().add(tooltip);
        }
        tooltip.setVisible(true);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        // 만약 툴팁이 보이고 있는데 이 스킬버튼에서 손을 땠을 경우 툴팁을 안보이도록 한다.
        if(tooltip != null && tooltip.isVisible() && BaseScene.getTopScene().getClickedButton() != this)
            tooltip.setVisible(false);
    }
}
