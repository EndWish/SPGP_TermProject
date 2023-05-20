package tukorea2018180009.ac.kr.example.equipmentcollector;

import java.util.ArrayList;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.AbigailAdventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Adventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.AliyahAdventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.AronAdventurer;
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
    protected Adventurer party[] = new Adventurer[4];
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
        gold = 1000000;
        adventurers.add(new ArthurAdventurer());
        adventurers.add(new ArthurAdventurer());
        adventurers.add(new AbigailAdventurer());
        adventurers.add(new AbigailAdventurer());
        adventurers.add(new AliyahAdventurer());
        adventurers.add(new AliyahAdventurer());
        adventurers.add(new AronAdventurer());
        adventurers.add(new AronAdventurer());

        for(int i = 0; i < 50; ++i){
            equipments.add(new PlainSwordEquipment());
        }
    }

    public void update() {
        adventurers.removeIf(adventurer -> adventurer.isDeleted());
        equipments.removeIf(equipment -> equipment.isDeleted());
        for(Adventurer adventurer : party){
            if(adventurer != null && adventurer.isDeleted())
                adventurer = null;
        }
    }

    // 파티 설정
    public void setPartyAdventerer(int index, Adventurer addAdventurer) {
        // 내가 소유중인 모험가가 아닐경우 바로 리턴한다.
        if(!adventurers.contains(addAdventurer))
            return;

        // 중복될 경우 그 모험가를 삭제한다.
        for(int i = 0; i < party.length; ++i){
            if(party[i] == addAdventurer)
                party[i] = null;
        }


        // 모험가를 배치한다.
        party[index] = addAdventurer;
    }
    public Adventurer getPartyAdventerer(int index) {
        return party[index];
    }
    public int getNumOfAdventererInParty() {
        int result = 0;
        for(int i = 0; i < 4; ++i){
            if(party[i] != null)
                ++result;
        }
        return result;
    }

    // getter, setter
    public ArrayList<Adventurer> getAdventurers(){ return adventurers; }
    public ArrayList<Equipment> getEquipments(){ return equipments; }

    public int getGold() { return gold; }
    public void addGold(int gold) {this.gold += gold;}
    public void subGold(int gold) {this.gold -= gold;}


}
