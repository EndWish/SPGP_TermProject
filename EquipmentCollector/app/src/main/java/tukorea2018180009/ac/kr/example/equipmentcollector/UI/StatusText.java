package tukorea2018180009.ac.kr.example.equipmentcollector.UI;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import tukorea2018180009.ac.kr.example.equipmentcollector.Adventurers.Status;
import tukorea2018180009.ac.kr.example.equipmentcollector.Sprite;
import tukorea2018180009.ac.kr.example.equipmentcollector.Text;

public class StatusText extends Sprite {

    Status.Type statusType;
    Status status;
    Text childText;

    public StatusText(Status status, Status.Type type, float cx, float cy, float width) {
        super(new Builder( Status.image[type.ordinal()], cx, cy, width, width));
        this.statusType = type;
        this.status = status;
        String text = this.status != null ? String.format("%.1f", status.get(type)) : "";
        childText = new Text(width * 1.1f, 0, text, width, width * 40.f, Color.BLACK, Paint.Align.LEFT);
        addChild(childText);
    }

    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
        String text = this.status != null ? String.format("%.1f", status.get(statusType)) : "";
        childText.setText(text);
    }

    @Override
    protected void draw(Canvas canvas) {
        if(status != null)
            super.draw(canvas);
    }
}
