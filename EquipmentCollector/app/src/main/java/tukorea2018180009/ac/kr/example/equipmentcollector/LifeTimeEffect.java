package tukorea2018180009.ac.kr.example.equipmentcollector;

public class LifeTimeEffect extends Sprite {
    float lifeTime;

    public LifeTimeEffect(float lifeTime, Builder builder) {
        super(builder);
        this.lifeTime = lifeTime;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        lifeTime -= deltaTime;
        if(lifeTime <= 0)
            setDelete();
    }
}
