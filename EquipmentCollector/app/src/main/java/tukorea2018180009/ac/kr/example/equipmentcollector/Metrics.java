package tukorea2018180009.ac.kr.example.equipmentcollector;

import android.renderscript.Matrix3f;

import java.util.AbstractMap;

public class Metrics {
    public static float scale = 1.0f;
    public static float game_width = 1600.0f;
    public static float game_height = 900.0f;
    public static int x_offset = 0, y_offset = 0;

    public static void setGameSize(float width, float height) {
        game_width = width;
        game_height = height;
    }

    public static float toGameX(float x) {
        return (x - x_offset) / scale;
    }
    public static float toGameY(float y) {
        return (y - y_offset) / scale;
    }

    // GameObject관련 계산들
    public static float distance2(GameObject a, GameObject b){
        return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
    }
    public static float distance(GameObject a, GameObject b){
        return (float)Math.sqrt(distance2(a, b));
    }

}
