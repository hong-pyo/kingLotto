package hong2.kinglotto.helper;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

public class LottoBallHelper {
    public static ArrayList<Bitmap> lottoBall;

    public static void LottoBall(Resources res, int size) {
        lottoBall = new ArrayList<>();

        for(int i=0; i < 45; i++) {
            int tempId = res.getIdentifier("ball_" + (i+1), "drawable", "hong2.kinglotto");
            Bitmap bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, tempId) ,size,size, false);
            lottoBall.add(bitmap);
        }
    }
}
