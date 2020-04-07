package hong2.kinglotto.menu;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import hong2.kinglotto.R;
import hong2.kinglotto.lotto.Lotto;

public class DetailListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // todo 여기에서 detail view 만들어주기.


        View view = inflater.inflate(R.layout.detail_list_fragment, null);
        if (getArguments() != null) {
            Lotto lotto = (Lotto) getArguments().get("lotto");
        }
        return view;
    }
}
