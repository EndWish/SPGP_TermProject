# SPGP_TermProject
장비 수집가(equipment collector)

## 게임 컨셉
유사 게임 : 서머너즈 워 천공의 아레나  
![image](https://user-images.githubusercontent.com/90082921/230753300-e9ca050a-86ab-4651-9bff-f286a68df105.png)  

    - 턴제 수집형 RPG 게임  
    - 캐릭터로 팀을 꾸려 던전을 탐험  
    - 던전 탐험은 웨이브 형식의 전투로 이루어져 있으며 각 전투는 턴제형태 이다.  
    - 플레이어는 공격턴을 잡은 캐릭터의 공격 대상을 지정하면 된다.  
    - 탐허을 통해 장비를 수집하고, 수집한 장비를 업그레이드 및 귀속시켜 캐릭터를 강화  
    - 위와 같은 과정을 반복하여 더 높은 난이도의 던전에 도전하는 게임  
    
## 개발 범위
![image](https://user-images.githubusercontent.com/90082921/230753326-9f01eaf5-2c08-4ab5-9301-29508fd74d55.png)  
    캐릭터 6개  
    무기 10종  
    탐험 지역 3곳  
    몬스터 10종 이상  

## 예상 게임 실행 흐름  
![image](https://user-images.githubusercontent.com/90082921/230753377-66f0864d-a012-4c36-b230-d6a702fb9111.png)
1.  플레이어는 로비 창에서 장비버튼과 캐릭터버튼을 클릭하여 보유하고 있는 캐릭터와 장비를 확인하고, 재화를 이용하여 장비를 강화하거나 캐릭터에게 귀속시켜 캐릭터를 강화합니다..  
![image](https://user-images.githubusercontent.com/90082921/230753449-c125fea7-9684-45d4-9f0b-fe29a35de335.png)  
2.  4명이하의 팀을 꾸려 탐험할 던전을 선택합니다.
![image](https://user-images.githubusercontent.com/90082921/230753487-4fdc1b0a-35d0-48bc-8b0d-58b6b7e6345f.png)  
2.  스킬 게이지가 찬 캐릭터는 턴을 가지게 되고 대상을 선택하여 공격을 할 수 있습니다.  
3.  모든 적의 체력을 0으로 만들어 처치하면 전투를 승리하게 되고 보상이 누적됩니다.  
4.  한번의 탐험은 총 10번의 전투로 이루어 지고, 탐험이 끝날때 까지는 체력이 유지됩니다.  
5.  전투승리후 다음 전투를 하기전에 후퇴를 할 수 있습니다. 후퇴를 하게 되면 해당 웨이브까지의 누적 보상을 얻을 수 있습니다.  
6.  만약 전투도중 아군 캐릭터가 전멸할 경우 탐험은 끝나고 보상을 얻을 수 없게 됩니다.  
7.  마지막 전투까지 승리하게 되면 탐험이 끝나고 다음 탐험지역이 활성화 됩니다. 1번 과정으로 돌아가 반복하게 됩니다.  

## 개발 일정
### 1주차 - 리소스 수집 (90%)  
-------------------------
![image](https://user-images.githubusercontent.com/90082921/236818396-e1180115-0b3d-49c5-b612-9792aa89decd.png)
![image](https://user-images.githubusercontent.com/90082921/236818557-a632b84a-27d0-4f94-ba6a-b2f87ebbeb14.png)
![image](https://user-images.githubusercontent.com/90082921/236818608-682de6a8-7e1b-4c0f-9286-4d05fd61c0b1.png)

### 2주차 - 클래스다이어 그램을 작성하고 설계하기(80%)  
링크 : https://drive.google.com/file/d/1hy8AKG66e9_fdKM2iuScWqOGM9-6YYXr/view?usp=sharing  
![image](https://user-images.githubusercontent.com/90082921/230753916-d48dd56a-f54e-455e-a9d6-032b73a03740.png)  
-------------------------
### 3주차 - 타이틀 화면 및 메인화면에 필요한 버튼 구성, 캐릭터와 장비 인벤토리 창 구현 (100%)  
SpriteButton클래스를 추가하여 버튼시스템을 구현  
![image](https://user-images.githubusercontent.com/90082921/236818920-d03a90ec-9985-4f79-8543-cc050b52740f.png)  
소유중인 캐릭터들을 보여주는 인벤토리창 구현  
![image](https://user-images.githubusercontent.com/90082921/236819729-f9d89a83-09c1-4fd0-9429-195a1e9be3ad.png)  
보유중인 장비를 보여주는 인벤토리창 구현  
![image](https://user-images.githubusercontent.com/90082921/236819859-e1ee082a-640f-4b82-ab75-a826288b4ecc.png)  
인벤토리창에 페이지를 넘길 수 있는 좌우 화살표 버튼을 만들고 페이지수를 표시할 수 있도록 텍스트를 출력  

-------------------------
### 4주차 - 캐릭터와 장비의 정보창 구현. 강화 및 귀속 시스템 구현 (90%)  
캐릭터의 능력치나 장비하고 있는 무기, 스킬 등을 보여주는 정보창을 구현  
![image](https://user-images.githubusercontent.com/90082921/236820103-e1365fc6-02bb-4cf5-8c80-5ae555a58802.png)  
장비의 정보를 보여주는 창을 구현  
![image](https://user-images.githubusercontent.com/90082921/236820685-c167b4ca-c345-4647-adbc-78349322de43.png)  

터치인 사이드 기능 구현.  
드래그 기능을 구현하여 파티를 구성할 수 있도록 제작  
가장위에 있는 창의 버튼만 눌리도록 구현(아래쪽에 묻혀있는 창의 버튼이 눌리지 않도록 구현.)  

무기강화와 무기귀속 기능을 구현  
![image](https://user-images.githubusercontent.com/90082921/236821239-0f980917-5c1c-4ef8-9995-b69425ee1c09.png)  

무기강화와 무기귀속 시도시 성공혹은 실패 이팩트가 발생하도록 구현  

-------------------------
### 5주차 - 전투 시스템 구현 (적 생성, 스킬 게이지, 대상 선택) (50%)
탐험지역 추가.  
![image](https://user-images.githubusercontent.com/90082921/236821630-72b67c13-f043-4073-853b-22d64f299ab1.png)  
현재 탐험지역에서 나올 수 있는 적들을 랜덤으로 생성하도록 구현.  
![image](https://user-images.githubusercontent.com/90082921/236825570-fcd0e0d1-1e90-41ca-8f65-c01cdbf9467b.png)  
시간이 지남에 따라 스킬 게이지가 차고, 가장 먼저 스킬게이지가 찬 캐릭터가 공격 턴을 가지고 공격할 수 있는 적은 노란색 테두리로 표시  
![image](https://user-images.githubusercontent.com/90082921/236821977-b38ef9f8-b848-4b66-b562-8a6664412d85.png)  

-------------------------
### 6주차 - 전투 시스템 구현 (스킬 이팩트, 데미지, 보상 시스템, 버프 디버프 시스템 구현)  
-------------------------
### 7주차 - 캐릭터와 무기 추가  
-------------------------
### 8주차 - 몬스터와 탐험지역 추가  
-------------------------
### 9주차 - 디버그 및 최종 테스트  
-------------------------

1차 발표 링크 : https://youtu.be/sNvB-zUBuz4  
