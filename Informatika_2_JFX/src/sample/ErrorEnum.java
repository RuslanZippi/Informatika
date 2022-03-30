package sample;

public enum ErrorEnum {
    TWO("0-1"),
    THREE("0-2"),
    FOUR("0-3"),
    FIFE("0-4"),
    SIX("0-5"),
    SEVEN("0-6"),
    EIGHT("0-7"),
    NINE("0-8"),
    TEN("0-9"),
    ELEVEN("0-9 и букву A английского алфавита"),
    TWELVE("0-9 и буквы A,B английского алфавита"),
    THIRTEEN("0-9 и буквы A,B,C английского алфавита"),
    FOURTEEN("0-9 и буквы A,B,C,D английского алфавита"),
    FIFTEEN("0-9 и буквы A,B,C,D,E английского алфавита"),
    SIXTEEN("0-9 и буквы A,B,C,D,E,F английского алфавита");

    private final String url;

    ErrorEnum(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
