package hong2.kinglotto.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import hong2.kinglotto.R;
import hong2.kinglotto.domain.LottoDetail;
import hong2.kinglotto.helper.LottoBallHelper;
import hong2.kinglotto.linstner.OnAdapterItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class LottoDetailAdapter extends RecyclerView.Adapter<LottoDetailAdapter.ViewHolder> implements OnAdapterItemClickListener {

    private List<LottoDetail> items = new ArrayList<>();
    private OnAdapterItemClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.detail_item, parent, false);
        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LottoDetail item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(LottoDetail item) {
        items.add(item);
    }

    public void setItems(List<LottoDetail> items) {
        this.items = items;
    }

    public LottoDetail getItem(int position) {
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
        private RelativeLayout detailLayout;
        private LinearLayout layout;
        private TextView gradeView;

        public ViewHolder(@NonNull View itemView, final OnAdapterItemClickListener listener) {
            super(itemView);
            detailLayout = itemView.findViewById(R.id.detailLayout);
            layout = itemView.findViewById(R.id.detail_winner_view);
            gradeView = itemView.findViewById(R.id.winGradeTextView);
            LottoBallHelper.LottoBall(itemView.getResources(), 100);
        }

        public void setItem(LottoDetail lottoDetail) {
            LottoBallHelper.LottoBallSet(layout, lottoDetail.getWinnerNumbers(), 0);
            gradeView.setText(!lottoDetail.isOverWinnerDay() ? "?!?" : lottoDetail.getWinGrade());
        }
    }
}
