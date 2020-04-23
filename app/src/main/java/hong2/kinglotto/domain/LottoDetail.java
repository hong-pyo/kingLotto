package hong2.kinglotto.domain;

import java.io.Serializable;
import java.util.List;

public class LottoDetail implements Serializable {
    //todo Integer 로 할것인지 String 으로 할것인지 통일 필요.
    private List<Integer> winnerNumbers;
    private String winGrade;
    private boolean overWinnerDay;

    public LottoDetail(List<Integer> winnerNumbers, String winGrade, boolean overWinnerDay) {
        this.winnerNumbers = winnerNumbers;
        this.winGrade = winGrade;
        this.overWinnerDay = overWinnerDay;
    }

    public boolean isOverWinnerDay() {
        return overWinnerDay;
    }

    public void setOverWinnerDay(boolean overWinnerDay) {
        this.overWinnerDay = overWinnerDay;
    }

    public List<Integer> getWinnerNumbers() {
        return winnerNumbers;
    }

    public void setWinnerNumbers(List<Integer> winnerNumbers) {
        this.winnerNumbers = winnerNumbers;
    }

    public String getWinGrade() {
        return winGrade;
    }

    public void setWinGrade(String winGrade) {
        this.winGrade = winGrade;
    }
}
