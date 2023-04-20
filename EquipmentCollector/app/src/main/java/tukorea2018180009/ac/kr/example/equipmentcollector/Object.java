package tukorea2018180009.ac.kr.example.equipmentcollector;

public class Object {
    protected boolean deleted;

    public Object(){
        deleted = false;
    }

    public void update(float deltaTime) {

    }

    public void setDelete(){
        deleted = true;
    }

    public boolean isDeleted(){
        return deleted;
    }
}
