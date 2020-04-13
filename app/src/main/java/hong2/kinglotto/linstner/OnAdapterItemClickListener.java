package hong2.kinglotto.linstner;

import android.view.View;
import hong2.kinglotto.adapter.LottoAdapter;

public interface OnAdapterItemClickListener {
    public void onItemClick(LottoAdapter.ViewHolder holder, View view, int position);
}
