package hong2.kinglotto.lotto;


import java.time.LocalDate;
import java.util.List;


public class Lotto {
    private int id;
    private String round;
    private String weather;
    private String picture;
    private LocalDate winnerDate;
    private List<Integer> winnerNumbers;
    private String winnerCount;

    public Lotto(int id, String round, String weather, String picture, LocalDate winnerDate, List<Integer> winnerNumbers, String winnerCount) {
        this.id = id;
        this.round = round;
        this.weather = weather;
        this.picture = picture;
        this.winnerDate = winnerDate;
        this.winnerNumbers = winnerNumbers;
        this.winnerCount = winnerCount;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public LocalDate getWinnerDate() {
        return winnerDate;
    }

    public void setWinnerDate(LocalDate winnerDate) {
        this.winnerDate = winnerDate;
    }

    public List<Integer> getWinnerNumbers() {
        return winnerNumbers;
    }

    public void setWinnerNumbers(List<Integer> winnerNumbers) {
        this.winnerNumbers = winnerNumbers;
    }

    public String getWinnerCount() {
        return winnerCount;
    }

    public void setWinnerCount(String winnerCount) {
        this.winnerCount = winnerCount;
    }
}
