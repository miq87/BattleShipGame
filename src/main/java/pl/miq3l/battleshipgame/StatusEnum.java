package pl.miq3l.battleshipgame;

public enum StatusEnum {
    SEA(0),
    SHIP(1),
    HITTED(2),
    MISSED(-1);
    private final int value;

    StatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
