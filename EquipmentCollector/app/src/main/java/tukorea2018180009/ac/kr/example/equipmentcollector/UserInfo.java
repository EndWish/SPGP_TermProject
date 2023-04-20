package tukorea2018180009.ac.kr.example.equipmentcollector;

import java.util.ArrayList;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Adventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.ArthurAdventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.Equipment.Equipment;
import tukorea2018180009.ac.kr.example.equipmentcollector.Equipment.PlainSwordEquipment;

public class UserInfo {
    // 싱글톤
    private static UserInfo userInfo = new UserInfo();
    public static UserInfo getInstance() {
        return userInfo;
    }

    // 멤버 변수
    protected ArrayList<Adventurer> adventurers;
    protected ArrayList<Equipment> equipments;
    protected int gold;

    //생성자
    UserInfo() {
        Init();
        LoadNewStart();
    }

    public void Init() {
        adventurers = new ArrayList<>();
        equipments = new ArrayList<>();
        gold = 0;
    }

    //멤버 함수
    public void LoadNewStart() {
        gold = 1000;
        for(int i = 0; i < 50; ++i){
            adventurers.add(new ArthurAdventurer());
        }
        for(int i = 0; i < 50; ++i){
            equipments.add(new PlainSwordEquipment());
        }
    }

    public void update() {
        adventurers.removeIf(adventurer -> adventurer.isDeleted());
        equipments.removeIf(equipment -> equipment.isDeleted());

    }

    // getter, setter
    public ArrayList<Adventurer> getAdventurers(){ return adventurers; }
    public ArrayList<Equipment> getEquipments(){ return equipments; }

    public int getGold() { return gold; }
    public void addGold(int gold) {this.gold += gold;}
    public void subGold(int gold) {this.gold -= gold;}

}
