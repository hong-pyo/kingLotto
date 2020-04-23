package hong2.kinglotto.helper;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class LottoBallHelper {
    public static ArrayList<Bitmap> lottoBall;
    public static Bitmap plus;

    public static void LottoBall(Resources res, int size) {
        lottoBall = new ArrayList<>();

        for(int i=0; i < 45; i++) {
            int tempId = res.getIdentifier("ball_" + (i+1), "drawable", "hong2.kinglotto");
            Bitmap bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, tempId) ,size,size, false);
            lottoBall.add(bitmap);
        }
        int plusId = res.getIdentifier("plus", "drawable", "hong2.kinglotto");
        Bitmap bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, plusId) ,size,size, false);
        plus = bitmap;
    }

    public static void LottoBallSet(LinearLayout linearLayout, List<Integer> lottoBalls, int bonus) {
        for (int i=0; i<lottoBalls.size(); i++) {
            ImageView imageView = new ImageView(linearLayout.getContext());
            imageView.setImageBitmap(lottoBall.get(lottoBalls.get(i) - 1));
            linearLayout.addView(imageView);
        }

        if (bonus != 0) {
            ImageView plusView = new ImageView(linearLayout.getContext());
            ImageView bonusView = new ImageView(linearLayout.getContext());
            plusView.setImageBitmap(plus);
            linearLayout.addView(plusView);
            bonusView.setImageBitmap(lottoBall.get(bonus - 1));
            linearLayout.addView(bonusView);
        }
    }
}
