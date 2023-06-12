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
### 1주차 - 리소스 수집 (100%)  
-------------------------
![image](https://user-images.githubusercontent.com/90082921/236818396-e1180115-0b3d-49c5-b612-9792aa89decd.png)
![image](https://user-images.githubusercontent.com/90082921/236818557-a632b84a-27d0-4f94-ba6a-b2f87ebbeb14.png)
![image](https://user-images.githubusercontent.com/90082921/236818608-682de6a8-7e1b-4c0f-9286-4d05fd61c0b1.png)

### 2주차 - 클래스다이어 그램을 작성하고 설계하기 (100%)  
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
### 4주차 - 캐릭터와 장비의 정보창 구현. 강화 및 귀속 시스템 구현 (100%)  
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
### 5주차 - 전투 시스템 구현 (적 생성, 스킬 게이지, 대상 선택) (100%)  
탐험지역 추가.  
![image](https://user-images.githubusercontent.com/90082921/236821630-72b67c13-f043-4073-853b-22d64f299ab1.png)  
현재 탐험지역에서 나올 수 있는 적들을 랜덤으로 생성하도록 구현.  
![image](https://user-images.githubusercontent.com/90082921/236825570-fcd0e0d1-1e90-41ca-8f65-c01cdbf9467b.png)  
시간이 지남에 따라 스킬 게이지가 차고, 가장 먼저 스킬게이지가 찬 캐릭터가 공격 턴을 가지고 공격할 수 있는 적은 노란색 테두리로 표시  
![image](https://user-images.githubusercontent.com/90082921/236821977-b38ef9f8-b848-4b66-b562-8a6664412d85.png)  

-------------------------
### 6주차 - 전투 시스템 구현 (스킬 이팩트, 데미지, 보상 시스템, 버프 디버프 시스템 구현) (100%)  
아군 공격턴(대상 선택) 추가.  
![공격](https://github.com/EndWish/SPGP_TermProject/assets/90082921/0080ccf7-6b81-4172-a92f-e0114d17661a)  
  
적군 공격턴(자동공격) 추가.  
![적의 공격](https://github.com/EndWish/SPGP_TermProject/assets/90082921/800ce33d-7ff6-4623-b2d7-8453094f4417)  
  
전투 승리후 다음 웨이브 이동 추가.  
![다음 웨이브 이동](https://github.com/EndWish/SPGP_TermProject/assets/90082921/15655df2-10c7-48b1-bd04-816a4ab7eac1)  
  
탐험 성공 결과창 추가.  
![탐험 성공](https://github.com/EndWish/SPGP_TermProject/assets/90082921/e53874b4-cf82-4e0c-809c-030609d25cc5)  
  
탐험 실패 결과창 추가.  
![탐험 실패](https://github.com/EndWish/SPGP_TermProject/assets/90082921/26ce885a-7d0e-4602-9db8-d016b2d31870)  
  
-------------------------
### 7주차 - 캐릭터와 무기 추가 (100%)   
![png_icon_adventurer_arthur_256x256](https://github.com/EndWish/SPGP_TermProject/assets/90082921/3b4878ac-c9d5-448b-b52e-222a05875a07)  
![Slash](https://github.com/EndWish/SPGP_TermProject/assets/90082921/f5d09616-221a-4539-81a0-0cf5813cfc72)  
![Rush](https://github.com/EndWish/SPGP_TermProject/assets/90082921/82848b9c-072e-4f44-8485-760f42c96b51)  
  
![png_icon_adventurer_abigail_256x256](https://github.com/EndWish/SPGP_TermProject/assets/90082921/a2e9c473-98b6-49ae-8626-b34eb24b4645)  
![Heal](https://github.com/EndWish/SPGP_TermProject/assets/90082921/783f64a1-2f95-45aa-824f-2dea0f2e27bc)  
![Blessing](https://github.com/EndWish/SPGP_TermProject/assets/90082921/f6736e72-9147-4479-9ef5-879a2b698736)  
  
![png_icon_adventurer_aliyah_256x256](https://github.com/EndWish/SPGP_TermProject/assets/90082921/afcde166-a4ea-4a4d-a4bf-70b53cd4368b)  
![PierceArrow](https://github.com/EndWish/SPGP_TermProject/assets/90082921/a6e5beba-d1ca-4c37-9533-b0c2c523744c)  
![EmpweredArrows](https://github.com/EndWish/SPGP_TermProject/assets/90082921/4fdd7255-f4e6-4a7c-99cc-3bf75f7704a8)  
  
![png_icon_adventurer_barry_256x256](https://github.com/EndWish/SPGP_TermProject/assets/90082921/97b4d8ea-c681-4869-8bb1-99fa1b52fd3e)  
![CreepingToxin](https://github.com/EndWish/SPGP_TermProject/assets/90082921/341ad500-1338-4986-9799-b707dacf7741)  
![PoisonMistReinforce](https://github.com/EndWish/SPGP_TermProject/assets/90082921/8f5ce1a7-d27d-4e2e-99b1-0c5ece06ee17)  
  
![png_icon_adventurer_shamar_256x256](https://github.com/EndWish/SPGP_TermProject/assets/90082921/92268cb1-bbd7-4f8e-bd88-4b71a02ebf47)  
![EnchantedQuiver](https://github.com/EndWish/SPGP_TermProject/assets/90082921/273fae8d-22ec-46a5-ab08-074b44a22c65)  
![ArrowBlaster](https://github.com/EndWish/SPGP_TermProject/assets/90082921/bdb5173d-c6f7-4975-9832-276ed1786b8e)  
  
![png_icon_adventurer_aron_256x256](https://github.com/EndWish/SPGP_TermProject/assets/90082921/3d2f672a-169f-4a61-88f7-296ed65628ac)  
![OrbitalFlame](https://github.com/EndWish/SPGP_TermProject/assets/90082921/1b9998ae-8dac-4021-8e68-cbef7c12fe92)  
![FlameVortex](https://github.com/EndWish/SPGP_TermProject/assets/90082921/fa7461fd-d42b-403f-84f8-d514ff906cf4)  
 
  
  
장비 아이템 추가.  
![png_icon_equipment_belt](https://github.com/EndWish/SPGP_TermProject/assets/90082921/7f57447e-0f2d-465d-a1da-85be2234809d)
![png_icon_equipment_cloak](https://github.com/EndWish/SPGP_TermProject/assets/90082921/6ae28b81-3120-4e3e-80a0-3fd3b108da6a)
![png_icon_equipment_gauntlet](https://github.com/EndWish/SPGP_TermProject/assets/90082921/f926a25d-c6fb-4044-a906-15b0acd888ae)
![png_icon_equipment_hauberk](https://github.com/EndWish/SPGP_TermProject/assets/90082921/bde04914-4b5c-4c5d-8399-5fd1c3af9786)
![png_icon_equipment_headpiece](https://github.com/EndWish/SPGP_TermProject/assets/90082921/b93a2b6a-7d3c-4c82-a013-13d1670f921d)
![png_icon_equipment_mjolnir](https://github.com/EndWish/SPGP_TermProject/assets/90082921/9d18abd8-2562-4f1b-8907-09309a45ad79)
![png_icon_equipment_plain_bow](https://github.com/EndWish/SPGP_TermProject/assets/90082921/bbc20236-237c-49c5-af76-63e0c28ab9ef)
![png_icon_equipment_plain_hammer](https://github.com/EndWish/SPGP_TermProject/assets/90082921/aae692e0-0195-4d9b-ad7d-fab4a13500a1)
![png_icon_equipment_plain_staff](https://github.com/EndWish/SPGP_TermProject/assets/90082921/5a0f0570-00b2-4d7f-9618-bfe288b7fbe1)
![png_icon_equipment_plain_sword](https://github.com/EndWish/SPGP_TermProject/assets/90082921/29a30f58-cf0c-465b-a254-51df8c74efc9)
![png_icon_equipment_plain_wand](https://github.com/EndWish/SPGP_TermProject/assets/90082921/7c04e34c-9c0a-45f3-90d9-24e8fcabadec)
![png_icon_equipment_shield](https://github.com/EndWish/SPGP_TermProject/assets/90082921/16950b2d-ed70-4ff3-8a61-a4615dc9b38c)
![png_icon_equipment_shoulder](https://github.com/EndWish/SPGP_TermProject/assets/90082921/94a1abf4-7b3b-4ac3-bf59-ffb8fe1420a7)
![png_icon_equipment_skull_wand](https://github.com/EndWish/SPGP_TermProject/assets/90082921/eec1886a-42a8-418a-8088-b44282831ef9)

-------------------------
### 8주차 - 몬스터와 탐험지역 추가 (100%)  
![png_profile_monster_ivern_256x384](https://github.com/EndWish/SPGP_TermProject/assets/90082921/c0e8164f-89a5-4fcd-844d-b42ce2632ddf)
![png_profile_monster_lilium_serpens_256x384](https://github.com/EndWish/SPGP_TermProject/assets/90082921/9e9e2986-fe9b-49d7-8ceb-bea99f01b4ad)
![png_profile_monster_baby_hydra_256x384](https://github.com/EndWish/SPGP_TermProject/assets/90082921/3ee1f5fb-92f6-4541-b982-fdcb6b7ae916)
![png_profile_monster_murloc_256x384](https://github.com/EndWish/SPGP_TermProject/assets/90082921/a8cb336a-73a6-4198-8404-fe60afa0e3fb)
![png_profile_monster_shaman_oak_256x384](https://github.com/EndWish/SPGP_TermProject/assets/90082921/c4059dd6-73bf-4c91-a892-c464d275db40)
![png_profile_monster_bluehide_256x384](https://github.com/EndWish/SPGP_TermProject/assets/90082921/b2da53b5-9219-4689-aca8-2cbc9c892f44)
![png_profile_monster_red_lizardman_256x384](https://github.com/EndWish/SPGP_TermProject/assets/90082921/dc4a18cd-c45e-4f89-9c92-249d6a6a8f76)
![png_profile_monster_tree_demon_256x384](https://github.com/EndWish/SPGP_TermProject/assets/90082921/827bc974-bbc7-4e0b-a133-53f9ccf473af)
![png_profile_monster_grizzlyeer_256x384](https://github.com/EndWish/SPGP_TermProject/assets/90082921/5c1eb0ab-810a-4f68-90c0-c06aad547bd0)
![png_profile_monster_hydra_256x384](https://github.com/EndWish/SPGP_TermProject/assets/90082921/43053bcc-4a32-452e-a53c-0f8f5a2f4b52)

![FinalCrash](https://github.com/EndWish/SPGP_TermProject/assets/90082921/76e78392-7cbb-408e-a9f5-e34c01c7cab1)

-------------------------
### 9주차 - 디버그 및 최종 테스트 (100%)  
-------------------------

### 진행률
-------------------------
|주차|진행률|
|:----:|:----:|
|1주차|100%|
|2주차|100%|
|3주차|100%|
|4주차|100%|
|5주차|100%|
|6주차|100%|
|7주차|100%|
|8주차|100%|
|9주차|100%|

## GameObject  
GameObject : update와 draw를 처리하고 자식을 가질 수 있도록 하여 GameObject를 계층적으로 만들 수 있도록 하였다.  
Sprite : GameObject를 상속하고, bitmap 크기, 위치, 스케일, 회전, 피봇위치, 이미지 뒤집기 등의 변수를 통해 이미지를 특정 위치에 출력하며, 애니메이션을 하기위한 변수와 함수도 추가로 들어가 있다.  
SpriteButton : Sprite를 상속하며 버튼을 눌렀지에 대한 처리가 추가로 구현되어 있다.  
Adventurer : 캐릭터(모험가)의 능력치, 스킬, 보유중인 아이템 정보들을 가지고 있다. IIcon을 상속 받아 버튼의 이미지로 가져오기 쉽도록 하였다.  
Equipment : 장비의 레벨과 착용자등의 정보를 가지고 있다. IAbility를 상속받아 특정 상황에서 능력이 발동되도록 구현. IIcon도 상속 받았다.  
Skill : 현재는 이름, 아이콘, 툴팁등을 정보를 가지고 있다. 추후 캐릭터(모험가)를 매개변수로 스킬 이펙트를 생성하는 함수를 추가할 예정이다.  

### 사용된 기술  
- Touch In side  
- Drag  
- Oriented Bounding Box 와 점(마우스)의 충돌 체크 구현  

### 참고한 것들  
- 폰트의 가로 사이즈를 얻어와 공백과 개행문자를 기준으로 문자를 쪼개는 것 (tooltip 만들때 사용)  

### 수업내용에서 차용한 것  
- BitmapPool/BaseScene/GameView  
- 빌더 패턴을 사용하여 객체를 생성하기  
- 화면의 크기를 상수로 만들어 사용하는 것  

### 직접 개발한 것  
- 게임 오브젝트를 계층 구조로 구현  
- 아래에 묻힌 UI의 버튼이 눌리지 않도록 구현  
- 스프라이트 애니메이션 및 회전과 플립 구현  
- 전투 시스템  

### 하고 싶었지만 못 한 것들 & 팔기 위해 보충할 것들  
- 사운드 삽입  
- RecycleBin 구현  
- 콘텐츠 보강 및 추가  
- 장비 아이템이 능력치만 주는것이 아닌 특수한 효과도 주도록 구현  

### 수업에 대한 내용  
- bitmapPool, RecycleBin 과 같은 메모리 관리 기법과 디자인 패턴 등 다른 강의에서 배우지 못한 내용들이 도움이 되었다.  
- 강의 초반에 OOP에 대한 내용을 상기시켜주어, 이번 텀 프로젝트를 하면서 UML를 사용해 미리 설계도 해보고 객체 지향적인 프로그램을 의식하면서 코드를 짜다보니 프로젝트의 크기가 커져도 새로운 것을 추가하거나 수정할때 큰 어려움을 겪지 않은 것은 처음이어서 좋았습니다.  

## 커밋 내용  
![image](https://github.com/EndWish/SPGP_TermProject/assets/90082921/67f43fbd-1b95-4474-909d-e9ab6f278f1b)  
|주차|커밋 횟수|
|:----:|:----:|
|1주차 이전|4|
|1주차|17|
|2주차|6|
|3주차|7|
|4주차|4|
|5주차|11|
|6주차|8|
|7주차|24|
|8주차|15|
|9주차|14|

1차 영상 링크 : https://github.com/EndWish/SPGP_TermProject/blob/bb68b0f6afb3484c3336cbbfdbb2d46b2f20cf4e/README.md  
1차 README.md 링크 : https://github.com/EndWish/SPGP_TermProject/blob/bb68b0f6afb3484c3336cbbfdbb2d46b2f20cf4e/README.md  

2차 영상 링크 : https://github.com/EndWish/SPGP_TermProject/blob/bb68b0f6afb3484c3336cbbfdbb2d46b2f20cf4e/README.md  
2차 README.md 링크 : https://github.com/EndWish/SPGP_TermProject/tree/dfc52a7bb5978507aa7c9e7f47022cd4086541cf  

3차 영상 링크 : https://youtu.be/M7pyT4aukIk  
