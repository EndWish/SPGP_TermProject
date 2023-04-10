package tukorea2018180009.ac.kr.example.equipmentcollector;

import java.util.ArrayList;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Adventurer;
import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.ArthurAdventurer;

public class UserInfo {
    // 싱글톤
    private static UserInfo userInfo = new UserInfo();
    public static UserInfo getInstance() {
        return userInfo;
    }

    // 멤버 변수
    protected ArrayList<Adventurer> adventurers;
    protected int gold;

    //생성자
    UserInfo() {
        Init();
        LoadNewStart();
    }

    public void Init() {
        adventurers = new ArrayList<>();
        gold = 0;
    }

    //멤버 함수
    public void LoadNewStart() {
        gold = 1000;
        adventurers.add(new ArthurAdventurer());
    }

    // getter, setter
    public ArrayList<Adventurer> getAdventurers(){ return adventurers; }

    public int getGold() { return gold; }
    public void addGold(int gold) {this.gold += gold;}
    public void subGold(int gold) {this.gold -= gold;}

}
