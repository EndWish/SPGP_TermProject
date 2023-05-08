package tukorea2018180009.ac.kr.example.equipmentcollector.UI.EquipmentUI;

import android.util.Log;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Adventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.Equipment.Equipment;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.YesNoQueryUI;
import tukorea2018180009.ac.kr.example.equipmentcollector.UserInfo;

public class EquipmentEquipQueryUI  extends YesNoQueryUI {
    static final String TAG = EquipmentEquipQueryUI.class.getSimpleName();

    protected Equipment equipment;
    protected Adventurer wearer;
    protected final float successPercentage, destructionPercentage;
    protected final int cost;

    public EquipmentEquipQueryUI(Equipment equipment, Adventurer wearer) {
        super();
        this.equipment = equipment;
        this.wearer = wearer;

        // 강화 비용 : 착용된 장비의 개수에 비례
        cost = (wearer.getEquipments().size() + 1) * 100;

        // 성공 확률 : 100 * 0.5^(level차이)
        successPercentage = 100f * (float)(Math.pow(0.8, wearer.getEquipments().size()));

        // 파괴 확률 : 실패 확률 * 0.02
        destructionPercentage = (100f - successPercentage) * 0.02f;

        childText.setText( "\"" + this.equipment.getName() + "+" + this.equipment.getUpgradeLevel() + "\"을 \"" + wearer.getName() + "\"에 착용하시겠습니까?" + "\n"
                + "강화 비용 - " + cost + "(비용) / " + UserInfo.getInstance().getGold() + "(소지금)\n\n"
                + "강화 성공 확률 : " + successPercentage + "%\n"
                + "강화 실패 확률 : " + (100f - successPercentage - destructionPercentage) + "%\n"
                + "재료 파괴 확률 : " + destructionPercentage + "%\n");

    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        // Yes 버튼을 눌렀을 경우
        if(yesButton.getTrigger()){
            UserInfo userInfo = UserInfo.getInstance();

            // 돈이 부족할 경우 리턴한다.
            if(userInfo.getGold() < cost){
                Log.d(TAG, "돈이 부족합니다.");
                // 이팩트를 출력한다.
                return;
            }

            // 돈을 지불한다.
            UserInfo.getInstance().subGold(cost);

            // 확률을 계산한다.
            final float percentage = (float)(Math.random() * 100.0);
            // 귀속 성공 : 장비를 착용시킨다.
            if(percentage <= successPercentage){
                wearer.addEquipment(equipment);
                UserInfo.getInstance().getEquipments().remove(equipment);   // 장비를 착용시켰으니 인벤토리에서는 제거한다.
                setDelete();
                Log.d(TAG, "귀속 성공");
                return;
            }
            // 장비 파괴
            else if(percentage <= successPercentage + destructionPercentage) {
                equipment.setDelete();
                setDelete();
                Log.d(TAG, "장비 파괴");
                return;
            }
            // 귀속 실패 : 이펙트를 출력한다.
            else {
                Log.d(TAG, "귀속 실패");
            }
        }

        // No 버튼을 눌렸을 경우
        if(noButton.getTrigger())
            setDelete();
    }

}
