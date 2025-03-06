package cleancode.minesweeper.tobe;

public class Cell {

    public static final String FLAG_SIGN = "⚑";
    public static final String LAND_MINE_SIGN = "☼";
    public static final String CLOSED_CELL_SIGN = "□";
    public static final String OPENED_CELL_SIGN = "■";

    private final String sign;
    private int nearByLandMindCount;
    private boolean isLandMine;

    private Cell(String sign, int nearByLandMindCount, boolean isLandMine) {
        this.sign = sign;
        this.nearByLandMindCount = nearByLandMindCount;
        this.isLandMine = isLandMine;
    }

    // 정적 펙토리 메서드
    public static Cell of(String sign, int nearByLandMindCount, boolean isLandMine) {
        return new Cell(sign, nearByLandMindCount, isLandMine);
    }

    // Cell이 가진 속성: 근처 지뢰 숫자, 지뢰 여부
    // Cell의 상태: 깃발 유무, 열렸다/닫혔다, 사용자가 확인함

    public static Cell ofFlag() {
        return of(FLAG_SIGN, 0, false);
    }

    public static Cell ofLandMine() {
        return of(LAND_MINE_SIGN, 0, false);
    }

    public static Cell ofClosed() {
        return of(CLOSED_CELL_SIGN, 0, false);
    }

    public static Cell ofOpened() {
        return of(OPENED_CELL_SIGN, 0, false);
    }

    public static Cell ofNearByLandMindCount(int count) {
        return of(String.valueOf(count), 0,false);
    }

    public String getSign() {
        return sign;
    }

    public boolean isClosed() {
        return CLOSED_CELL_SIGN.equals(this.sign);
    }

    public boolean doesNotClosed() {
        return !isClosed();
    }
}
