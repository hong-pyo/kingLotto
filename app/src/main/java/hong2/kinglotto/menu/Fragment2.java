package hong2.kinglotto.menu;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import hong2.kinglotto.R;
import hong2.kinglotto.helper.LottoBallHelper;

import java.util.Random;
import java.util.TreeSet;

public class Fragment2 extends Fragment {
    private LinearLayout extractView;
    private Button extarctButton;

    private Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (context != null) {
            context = null;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment2, container, false);
        initUI(rootView);
        return rootView;
    }

    private void initUI(ViewGroup rootView) {
        LottoBallHelper.LottoBall(getResources(), 150);

        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        extractView = rootView.findViewById(R.id.extractView);
        extractView.setLayoutParams(params);
        extractView.setOrientation(LinearLayout.HORIZONTAL);
        extarctButton = rootView.findViewById(R.id.extractButton);

        extarctButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add View 로 뷰를 추가하기 떄문에 이전뷰는 삭제. (이전에 뽑은 애들) API 쓰면 필요없을듯..? 잘해결해보자.
                if(extractView.getChildCount() > 0) {
                    extractView.removeAllViews();
                }

                TreeSet<Integer> set = new TreeSet<>();
                while (set.size() < 6) {
                    int random = new Random().nextInt(45) + 1;
                    set.add(random);
                }

                for (Integer i : set) {
                    // 이미지 생성
                    ImageView imageView = new ImageView(extractView.getContext());
                    // 셋팅
                    imageView.setImageBitmap(LottoBallHelper.lottoBall.get(i-1));
                    extractView.addView(imageView);
                }
            }
        });
    }
}
