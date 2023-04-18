package tukorea2018180009.ac.kr.example.equipmentcollector;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.Log;

import java.sql.Array;
import java.util.ArrayList;

public class Text extends GameObject {
    private static final String TAG = Text.class.getSimpleName();

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

    public Text(float cx, float cy, String text, float textSize, float width, int color, Paint.Align align) {
        super(cx, cy);

        this.paint = new TextPaint();
        this.texts = new ArrayList<String>();

        this.width = width;
        this.paint.setColor(color);
        setTextSize(textSize);
        setText(text);

        paint.setTextAlign(align);
    }

    @Override
    protected void draw(Canvas canvas) {
        super.draw(canvas);
        for(int i = 0; i < texts.size(); ++i){
            String text = texts.get(i);
            canvas.drawText(text, 0, textSize * (0.8f + i), paint);
        }
    }

    // 텍스트가 몇 줄인지 얻는 함수
    public int getNLine(){
        return texts.size();
    }

    // getter, setter
    public void setText(String text){
        texts.clear();
        // text가 null이거나 공백인 경우 빈 배열을 반환합니다.
        if (text == null || text.trim().isEmpty()) {
            Log.d(TAG, "text가 없습니다.");
            return;
        }

        float fontWidth = width;
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
        this.paint.setTextSize(this.textSize);

        // !! 글자 크기가 바뀌면 다시 라인을 나누어 줘야 한다.(이 기능이 필요해 질때 구현하자.)
    }



}
