package hong2.kinglotto.menu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import hong2.kinglotto.R;
import hong2.kinglotto.adapter.LottoAdapter;
import hong2.kinglotto.adapter.LottoDetailAdapter;
import hong2.kinglotto.domain.Lotto;
import hong2.kinglotto.domain.LottoDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DetailListFragment extends Fragment {

    private RecyclerView detailListRecyclerView;
    private RecyclerView lottoPurcharseView;
    private LottoDetailAdapter lottoDetailAdapter;
    private LottoDetailAdapter lottoPurcharseAdapter;
    private Button qrCodeScanButton;
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
        // todo 여기에서 detail view 만들어주기.
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.detail_list_fragment, container, false);

        if (getArguments() != null) {
            Lotto lotto = (Lotto) getArguments().get("lotto");
            initUI(rootView, lotto);
        }


        return rootView;
    }

    private void initUI(ViewGroup viewGroup, Lotto lotto) {
        detailListRecyclerView = viewGroup.findViewById(R.id.detailListRecyclerView);
        lottoPurcharseView = viewGroup.findViewById(R.id.purcharseListRecyclerView);
        qrCodeScanButton = viewGroup.findViewById(R.id.qrCodeScan);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        detailListRecyclerView.setLayoutManager(layoutManager);
        lottoPurcharseView.setLayoutManager(layoutManager2);

        lottoDetailAdapter = new LottoDetailAdapter();
        lottoPurcharseAdapter = new LottoDetailAdapter();

        qrCodeScanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QrCodeCaptureActiviry.class);
                startActivity(intent);
            }
        });

        List<Integer> winnerNumbers = new ArrayList<>();
        while (winnerNumbers.size() < 6) {
            Random random = new Random();
            int randomNumbers = random.nextInt(45) + 1;
            if (winnerNumbers.contains(randomNumbers)) {
                continue;
            }
            winnerNumbers.add(randomNumbers);
        }

        List<Integer> winnerNumbers2 = new ArrayList<>();
        while (winnerNumbers2.size() < 6) {
            Random random = new Random();
            int randomNumbers = random.nextInt(45) + 1;
            if (winnerNumbers2.contains(randomNumbers)) {
                continue;
            }
            winnerNumbers2.add(randomNumbers);
        }

        List<Integer> winnerNumbers3 = new ArrayList<>();
        while (winnerNumbers3.size() < 6) {
            Random random = new Random();
            int randomNumbers = random.nextInt(45) + 1;
            if (winnerNumbers3.contains(randomNumbers)) {
                continue;
            }
            winnerNumbers3.add(randomNumbers);
        }
        lottoDetailAdapter.addItem(new LottoDetail(winnerNumbers, "3등", lotto.isOverWinnerDay()));
        lottoDetailAdapter.addItem(new LottoDetail(winnerNumbers2, "1등", lotto.isOverWinnerDay()));
        lottoDetailAdapter.addItem(new LottoDetail(winnerNumbers3, "2등", lotto.isOverWinnerDay()));

        lottoPurcharseAdapter.addItem(new LottoDetail(winnerNumbers3, "2등", lotto.isOverWinnerDay()));
        detailListRecyclerView.setAdapter(lottoDetailAdapter);
        lottoPurcharseView.setAdapter(lottoPurcharseAdapter);
    }
}
