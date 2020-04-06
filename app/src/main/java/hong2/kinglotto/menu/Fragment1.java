package hong2.kinglotto.menu;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import hong2.kinglotto.R;
import hong2.kinglotto.linstner.OnLottoItemClickListener;
import hong2.kinglotto.linstner.OnTabItemSelectedListener;
import hong2.kinglotto.lotto.Lotto;
import hong2.kinglotto.lotto.LottoAdapter;
import lib.kingja.switchbutton.SwitchMultiButton;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Fragment1 extends Fragment {

    protected RecyclerView recyclerView;
    private LottoAdapter lottoAdapter;

    private Context context;
    private OnTabItemSelectedListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;

        if (context instanceof OnTabItemSelectedListener) {
            listener = (OnTabItemSelectedListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (context != null) {
            context = null;
            listener = null;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment1, container, false);
        initUI(rootView);
        return rootView;
    }

    private void initUI(ViewGroup rootView) {
        Button todayLottoButton = rootView.findViewById(R.id.todayLotto);
        todayLottoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onTabSelected(1);
                }
            }
        });

        SwitchMultiButton switchMultiButton = rootView.findViewById(R.id.switchButton);
        switchMultiButton.setOnSwitchListener(new SwitchMultiButton.OnSwitchListener() {
            @Override
            public void onSwitch(int position, String tabText) {
                Toast.makeText(getContext(), tabText, Toast.LENGTH_SHORT).show();
                //lottoAdapter.switchLayout(position); 나중에 switch 를 구현할때 사용 책에있음 (JUST DO IT)
                lottoAdapter.notifyDataSetChanged();
            }
        });

        recyclerView = rootView.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        lottoAdapter = new LottoAdapter();
        // api 호출로 data 가져와야함. / pagable
        List<Integer> winnerNumbers = new ArrayList<>();
        winnerNumbers.add(1);
        winnerNumbers.add(2);
        winnerNumbers.add(3);
        winnerNumbers.add(4);
        winnerNumbers.add(5);
        winnerNumbers.add(6);


        lottoAdapter.addItem(new Lotto(0, "3회", null, null, LocalDate.of(2020,2,9), winnerNumbers, "6개 당첨"));
        lottoAdapter.addItem(new Lotto(0, "2회", null, null, LocalDate.of(2020,2,9), winnerNumbers, "6개 당첨"));
        lottoAdapter.addItem(new Lotto(0, "1회", null, null, LocalDate.of(2020,2,2), winnerNumbers, "3개 당첨"));

        recyclerView.setAdapter(lottoAdapter);
        lottoAdapter.setOnItemClickListener(new OnLottoItemClickListener() {
            @Override
            public void onItemClick(LottoAdapter.ViewHolder holder, View view, int position) {
                Lotto item = lottoAdapter.getItem(position);
                Toast.makeText(getContext(), item.getRound() + "\n" + item.getWinnerNumbers(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
