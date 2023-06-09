package tukorea2018180009.ac.kr.example.equipmentcollector.UI.EquipmentUI;

import android.util.Log;

import tukorea2018180009.ac.kr.example.equipmentcollector.Equipment.Equipment;
import tukorea2018180009.ac.kr.example.equipmentcollector.LifeTimeEffect;
import tukorea2018180009.ac.kr.example.equipmentcollector.R;
import tukorea2018180009.ac.kr.example.equipmentcollector.Scenes.BaseScene;
import tukorea2018180009.ac.kr.example.equipmentcollector.UI.YesNoQueryUI;
import tukorea2018180009.ac.kr.example.equipmentcollector.UserInfo;

public class EquipmentUpgradeQueryUI extends YesNoQueryUI {
    static final String TAG = EquipmentUpgradeQueryUI.class.getSimpleName();
    
    protected Equipment equipment, ingredient;
    protected final float successPercentage, destructionPercentage;
    protected final int cost;

    public EquipmentUpgradeQueryUI(Equipment equipment, Equipment ingredient) {
        super();
        this.equipment = equipment;
        this.ingredient = ingredient;

        // 강화 비용
        cost = equipment.getUpgradeCost();

        // 성공 확률 : 100 * 0.5^(level차이)
        successPercentage = 100f * (float)(Math.pow(0.5, Math.max(0, equipment.getUpgradeLevel() - ingredient.getUpgradeLevel())));

        // 파괴 확률 : 실패 확률 * 0.02
        destructionPercentage = (100f - successPercentage) * 0.02f;

        childText.setText( "\"" + this.ingredient.getName() + "+" + this.ingredient.getUpgradeLevel() + "\"을 재료로 사용해서 강화 하시겠습니까?\n"
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
            // 강화 성공 : 재료를 삭제하고, 강화 레벨을 1증가시킨다. 이 창을 닫는다. 이펙트를 출력한다.
            if(percentage <= successPercentage){
                ingredient.setDelete();
                equipment.addUpgradeLevel(1);
                setDelete();
                Log.d(TAG, "강화 성공");
                // 이팩트 생성
                BaseScene.getTopScene().addPost(
                        new LifeTimeEffect(1f, new Builder(R.mipmap.png_sheet_effect_success, 800, 450, 800, 300)
                                .setPivot(PivotType.center)
                                .setAnimation(5, 22, 1, false)));
                
                return;
            }
            // 재료 파괴 : 재료를 삭제하고, 이 창을 닫는다. 이펙트를 출력한다.
            else if(percentage <= successPercentage + destructionPercentage) {
                ingredient.setDelete();
                setDelete();
                Log.d(TAG, "재료 파괴");
                // 이팩트 생성
                BaseScene.getTopScene().addPost(
                        new LifeTimeEffect(1f, new Builder(R.mipmap.png_sheet_effect_destroy, 800, 450, 800, 300)
                                .setPivot(PivotType.center)
                                .setAnimation(5, 22, 1, false)));

                return;
            }
            // 강화 실패 : 이펙트를 출력한다.
            else {
                Log.d(TAG, "강화 실패");
                // 이팩트 생성
                BaseScene.getTopScene().addPost(
                        new LifeTimeEffect(1f, new Builder(R.mipmap.png_sheet_effect_fail, 800, 450, 800, 300)
                                .setPivot(PivotType.center)
                                .setAnimation(5, 22, 1, false)));
            }
        }

        // No 버튼을 눌렸을 경우
        if(noButton.getTrigger())
            setDelete();
    }
}
