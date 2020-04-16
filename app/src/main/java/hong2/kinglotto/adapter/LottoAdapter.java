package hong2.kinglotto.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import hong2.kinglotto.R;
import hong2.kinglotto.linstner.OnAdapterItemClickListener;
import hong2.kinglotto.domain.Lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoAdapter extends RecyclerView.Adapter<LottoAdapter.ViewHolder> implements OnAdapterItemClickListener {

    private List<Lotto> items = new ArrayList<Lotto>();
    private OnAdapterItemClickListener listener;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.lotto_item_v2, viewGroup, false);

        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Lotto item = items.get(position);
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Lotto item) {
        items.add(item);
    }

    public void setItems(List<Lotto> items) {
        this.items = items;
    }

    public Lotto getItem(int position) {
        return items.get(position);
    }

    public void setOnItemClickListener(OnAdapterItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onItemClick(LottoAdapter.ViewHolder holder, View view, int position) {
        if (listener != null) {
            listener.onItemClick(holder, view, position);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout lottoItemLayout;
        private TextView roundTextView;
        private TextView overWinnerDayTextView;
        private ImageView luckyImage;
        private ImageView weatherImage;
        private TextView winnerDate;
        private ImageView userCustomPicture;

        private TextView winnerNumber1;
        private TextView winnerNumber2;
        private TextView winnerNumber3;
        private TextView winnerNumber4;
        private TextView winnerNumber5;
        private TextView winnerNumber6;

        private TextView bonusNumber;
        private TextView winnerCount;

        public ViewHolder(@NonNull View itemView, final OnAdapterItemClickListener listener) {
            super(itemView);

            lottoItemLayout = itemView.findViewById(R.id.lottoItemLayout);
            roundTextView = itemView.findViewById(R.id.roundTextView);
            overWinnerDayTextView = itemView.findViewById(R.id.overWinnerDayTextView);
            luckyImage = itemView.findViewById(R.id.luckyImage);
            weatherImage = itemView.findViewById(R.id.weatherImage);
            winnerDate = itemView.findViewById(R.id.winnerDate);
            userCustomPicture = itemView.findViewById(R.id.userCustomPicture);
            winnerNumber1 = itemView.findViewById(R.id.winnerNumber1);
            winnerNumber2 = itemView.findViewById(R.id.winnerNumber2);
            winnerNumber3 = itemView.findViewById(R.id.winnerNumber3);
            winnerNumber4 = itemView.findViewById(R.id.winnerNumber4);
            winnerNumber5 = itemView.findViewById(R.id.winnerNumber5);
            winnerNumber6 = itemView.findViewById(R.id.winnerNumber6);

            bonusNumber = itemView.findViewById(R.id.bonusNumber);
            winnerCount = itemView.findViewById(R.id.winnerCount);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null) {
                        listener.onItemClick(ViewHolder.this, v, position);
                    }
                }
            });
        }

        public void setItem(Lotto item) {
            roundTextView.setText(item.getRound());
            winnerDate.setText(item.getWinnerDate().toString());
            if (item.isOverWinnerDay()) {
                overWinnerDayTextView.setText("당첨 번호");
                winnerNumber1.setText(String.valueOf(item.getWinnerNumbers().get(0)));
                winnerNumber1.setVisibility(View.VISIBLE);
                winnerNumber2.setText(String.valueOf(item.getWinnerNumbers().get(1)));
                winnerNumber2.setVisibility(View.VISIBLE);
                winnerNumber3.setText(String.valueOf(item.getWinnerNumbers().get(2)));
                winnerNumber3.setVisibility(View.VISIBLE);
                winnerNumber4.setText(String.valueOf(item.getWinnerNumbers().get(3)));
                winnerNumber4.setVisibility(View.VISIBLE);
                winnerNumber5.setText(String.valueOf(item.getWinnerNumbers().get(4)));
                winnerNumber5.setVisibility(View.VISIBLE);
                winnerNumber6.setText(String.valueOf(item.getWinnerNumbers().get(5)));
                winnerNumber6.setVisibility(View.VISIBLE);
                bonusNumber.setText(String.valueOf(item.getBonusNumber()));
                bonusNumber.setVisibility(View.VISIBLE);
            } else {
                overWinnerDayTextView.setText("미 추첨");
            }
        }
    }
}