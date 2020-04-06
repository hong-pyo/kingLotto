package hong2.kinglotto.linstner;

import android.view.View;
import hong2.kinglotto.lotto.LottoAdapter;

public interface OnLottoItemClickListener {
    public void onItemClick(LottoAdapter.ViewHolder holder, View view, int position);
}
