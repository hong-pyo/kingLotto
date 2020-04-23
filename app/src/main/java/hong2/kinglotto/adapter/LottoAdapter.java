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
import hong2.kinglotto.helper.LottoBallHelper;
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
        private LinearLayout fragment1WinnerView;

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
            fragment1WinnerView = itemView.findViewById(R.id.fragment1_winner_view);
            LottoBallHelper.LottoBall(itemView.getResources(), 100);

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
                for(int i=0; i<item.getWinnerNumbers().size(); i++) {
                    ImageView imageView = new ImageView(fragment1WinnerView.getContext());
                    imageView.setImageBitmap(LottoBallHelper.lottoBall.get(item.getWinnerNumbers().get(i) - 1));
                    fragment1WinnerView.addView(imageView);
                }
                bonusNumber.setText(String.valueOf(item.getBonusNumber()));
                bonusNumber.setVisibility(View.VISIBLE);
            } else {
                overWinnerDayTextView.setText("미 추첨");
            }
        }
    }
}
