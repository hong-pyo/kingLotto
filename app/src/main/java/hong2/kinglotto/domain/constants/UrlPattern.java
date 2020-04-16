package hong2.kinglotto.domain.constants;

public enum  UrlPattern {
    LOGIN("/login", "로그인 정보"),
    TODAY_LOTTO("/today_lotto", "오늘의 로또?")
    ;

    private String url;
    private String decription;

    UrlPattern(String url, String decription) {
        this.url = url;
        this.decription = decription;
    }
}
