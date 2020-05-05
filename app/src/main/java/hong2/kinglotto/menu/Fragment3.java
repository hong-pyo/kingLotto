package hong2.kinglotto.menu;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import hong2.kinglotto.R;

public class Fragment3 extends Fragment {
    private Button logoutButton;
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
        ViewGroup rooeView = (ViewGroup) inflater.inflate(R.layout.fragment3, container, false);
        initUI(rooeView);

        return rooeView;
    }

    private void initUI(ViewGroup rooeView) {
        logoutButton = rooeView.findViewById(R.id.kakao_logout);


    }
}
