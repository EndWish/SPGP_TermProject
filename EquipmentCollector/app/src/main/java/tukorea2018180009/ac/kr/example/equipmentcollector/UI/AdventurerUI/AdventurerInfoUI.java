package tukorea2018180009.ac.kr.example.equipmentcollector.UI.AdventurerUI;

import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Adventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Status;
import tukorea2018180009.ac.kr.example.equipmentcollector.Equipment.Equipment;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Scenes.BaseScene;
import tukorea2018180009.ac.kr.example.equipmentcollector.Skills.Skill;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.Text.Text;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.EquipmentUI.EquipmentButton;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.SkillButton;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.StatusText;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.TriggerButton;

public class AdventurerInfoUI extends Sprite {
    Adventurer adventurer;

    TriggerButton closeButton;

    Sprite profileImage;
    Text nameText;

    ArrayList<StatusText> statusTexts;

    int equipmentPageIndex = 0;
    ArrayList<EquipmentButton> equipmentButtons;
    TriggerButton leftEquipmentButton, rightEquipmentButton;

    public AdventurerInfoUI(Adventurer adventurer) {
        super(new Sprite.Builder(R.mipmap.png_black_white_frame_512x256, 25, 25, 1550, 850));
        // 버튼 레이어를 하나 상승시킨다.
        BaseScene.getTopScene().addButtonLayer();

        // 나타낼 모험가 설정
        this.adventurer = adventurer;

        // 프로필 사진 초기화
        profileImage = new Sprite(new Builder(adventurer.getProfile(), 25, 25, 300, 450));
        addChild(profileImage);

        // 이름 텍스트
        nameText = new Text(162.5f, 475f, "< " + adventurer.getName() + " >", 50f, 500f, Color.BLACK, Paint.Align.CENTER);
        addChild(nameText);

        // 능력치 텍스트들 생성
        statusTexts = new ArrayList<>();
        Status.Type[] statusTypes = Status.Type.values();
        for(int i = 0; i < 4; ++i){
            StatusText statusText = new StatusText(adventurer.getTotalStatus(), statusTypes[i], 350f, 50f + i * 75f, 70f);
            statusTexts.add(statusText);
            addChild(statusText);
        }
        for(int i = 0; i < 6; ++i){
            StatusText statusText = new StatusText(adventurer.getTotalStatus(), statusTypes[4 + i], 750f, 50f + i * 75f, 70f);
            statusTexts.add(statusText);
            addChild(statusText);
        }
        for(int i = 0; i < 6; ++i){
            StatusText statusText = new StatusText(adventurer.getTotalStatus(), statusTypes[10 + i], 1150f, 50f + i * 75f, 70f);
            statusTexts.add(statusText);
            addChild(statusText);
        }
        
        // 스킬 버튼 생성
        int skillIndex = 0;
        for(Skill skill : adventurer.getSkills()){
            SkillButton skillButton = new SkillButton(skill, 25 + 225 * skillIndex, 600, 200);
            ++skillIndex;
            addChild(skillButton);
        }

        // 장비 버튼 생성
        equipmentButtons = new ArrayList<>();
        for(int i = 0; i < 6; ++i){
            EquipmentButton equipmentButton = new EquipmentButton(null,
                    900 + 150 * (i % 3), 525 + 150 * (i / 3), 125);
            equipmentButtons.add(equipmentButton);
            addChild(equipmentButton);
        }
        updateEquipmentButtons();

        leftEquipmentButton = new TriggerButton(new Builder(R.mipmap.png_button_left_arrow,
                900 - 75, 662.5f, 150, 150)
                .setPivot(PivotType.center));
        rightEquipmentButton = new TriggerButton(new Builder(R.mipmap.png_button_right_arrow,
                1325 + 75, 662.5f, 150, 150)
                .setPivot(PivotType.center));
        addChild(leftEquipmentButton);
        addChild(rightEquipmentButton);

        // 닫기 버튼 생성
        closeButton = new TriggerButton(new Builder(R.mipmap.png_button_x, width, 0, 50, 50)
                .setPivot(PivotType.rightTop));
        addChild(closeButton);

    }

    // 소멸자
    @Override
    public void setDelete() {
        // 버튼 레이어를 감소시킨다.
        BaseScene.getTopScene().subButtonLayer();

        closeButton = null;
        profileImage = null;
        super.setDelete();
    }

    // update
    @Override
    public void update(float deltaTime) {
        if(adventurer == null || adventurer.isDeleted()){
            setDelete();
            return;
        }
        super.update(deltaTime);

        updateEquipmentButtons();

        // 장비 페이지 넘기는 버튼을 눌렀을 경우
        if(leftEquipmentButton.getTrigger())
            subEquipmentPageIndex();
        if(rightEquipmentButton.getTrigger())
            addEquipmentPageIndex();

        // 닫기 버튼을 눌렀을 경우
        if(closeButton.getTrigger())
            setDelete();
    }

    // 장비 버튼 갱신
    protected void updateEquipmentButtons() {
        ArrayList<Equipment> equipments = adventurer.getEquipments();

        int startIndex = 6 * equipmentPageIndex;
        for(EquipmentButton equipmentButton : equipmentButtons){
            if(startIndex < equipments.size())
                equipmentButton.setEquipment(equipments.get(startIndex));
            else
                equipmentButton.setEquipment(null);
            ++startIndex;
        }
    }

    // getter, setter
    public int getEquipmentPageIndex() {
        return equipmentPageIndex;
    }
    public int getMaxEquipmentPageIndex(){
        return adventurer.getEquipments().size() / 6;
    }
    public void addEquipmentPageIndex() {
        if(equipmentPageIndex < getMaxEquipmentPageIndex())
            ++equipmentPageIndex;
        updateEquipmentButtons();
    }
    public void subEquipmentPageIndex() {
        if(0 < equipmentPageIndex)
            --equipmentPageIndex;
        updateEquipmentButtons();
    }


}
