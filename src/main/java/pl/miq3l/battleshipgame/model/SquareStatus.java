package pl.miq3l.battleshipgame.model;

public enum SquareStatus {
    SEA(0),
    SHIP(1),
    HITTED(2),
    MISSED(-1);
    private final int value;

    SquareStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
