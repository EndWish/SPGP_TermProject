package tukorea2018180009.ac.kr.example.equipmentcollector;

import android.graphics.Canvas;
import android.text.TextPaint;
import android.util.Log;

import java.sql.Array;
import java.util.ArrayList;

public class Text extends GameObject {
    private static final String TAG = Text.class.getSimpleName();
    static final float fontSize = 20;

    private static int findSplitIndex(String text, int start, TextPaint paint, float fontWidth) {
        // start부터 시작하여 width를 넘는 문자열의 인덱스를 반환합니다.
        for (int i = start; i < text.length(); i++) {
            if (paint.measureText(text.substring(start, i + 1)) > fontWidth) {
                return i;
            }
        }
        return -1; // 넓이를 넘는 문자열이 없으면 -1을 반환합니다.
    }

    protected ArrayList<String> texts;
    protected TextPaint paint;
    float textSize, width;

    public Text(float cx, float cy, String text, float textSize, float width, int color) {
        super(cx, cy);

        this.paint = new TextPaint();
        this.texts = new ArrayList<String>();

        this.width = width;
        this.paint.setColor(color);
        setTextSize(textSize);
        setText(text);
    }

    public void setText(String text){
        texts.clear();
        // text가 null이거나 공백인 경우 빈 배열을 반환합니다.
        if (text == null || text.trim().isEmpty()) {
            Log.d(TAG, "text가 없습니다.");
            return;
        }

        float fontWidth = width * fontSize;
        String[] lines = text.split("\\r?\\n"); // 개행문자를 기준으로 문자열을 나눕니다.

        for (String line : lines) {
            if (paint.measureText(line) <= fontWidth) {
                texts.add(line); // fontWidth 작으면 그대로 추가합니다.
            } else {
                // fontWidth 크면 문자열을 잘라서 배열에 추가합니다.
                int start = 0;
                int end = 0;
                while (start < line.length()) {
                    end = findSplitIndex(line, start, paint, fontWidth);
                    if (end == -1) {
                        // 남은 문자열이 fontWidth 작으면 그대로 추가합니다.
                        texts.add(line.substring(start));
                        break;
                    } else {
                        // fontWidth 넘지 않는 문자열을 추가합니다.
                        texts.add(line.substring(start, end));
                        start = end;
                    }
                }
            }
        }
    }
    public void setTextSize(float textSize){
        this.textSize = textSize;
        this.paint.setTextSize(this.textSize * fontSize);
    }

    @Override
    protected void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.scale(1.f/fontSize, 1.f/fontSize);
        for(int i = 0; i < texts.size(); ++i){
            String text = texts.get(i);
            canvas.drawText(text, x * fontSize, (y + textSize * (1 + i)) * fontSize, paint);
        }
        canvas.scale(fontSize, fontSize);
    }
}
