package cleancode.minesweeper.tobe;

public class Cell {

    public static final String FLAG_SIGN = "⚑";
    public static final String LAND_MINE_SIGN = "☼";
    public static final String UNCHECKED_SIGN = "□";
    public static final String EMPTY_SIGN = "■";

    private int nearByLandMindCount;
    private boolean isLandMine;
    private boolean isFlagged;
    private boolean isOpened;


    private Cell(int nearByLandMindCount, boolean isLandMine, boolean isFlagged, boolean isOpened) {
        this.nearByLandMindCount = nearByLandMindCount;
        this.isLandMine = isLandMine;
        this.isFlagged = isFlagged;
        this.isOpened = isOpened;
    }

    // 정적 펙토리 메서드
    public static Cell of(int nearByLandMindCount, boolean isLandMine, boolean isFlagged, boolean isOpened) {
        return new Cell(nearByLandMindCount, isLandMine, isFlagged, isOpened);
    }

    public static Cell create() {
        return of(0, false, false, false);
    }

    public void turnOnLandMine() {
        this.isLandMine = true;
    }

    public void updateNearbyLandMindCount(int count) {
        this.nearByLandMindCount = count;
    }

    public void flag() {
        isFlagged = true;
    }

    public void open() {
        this.isOpened = true;
    }

    public boolean isChecked() {
        return isFlagged || isOpened;
    }

    public boolean isLandMine() {
        return isLandMine;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public boolean hasLandMineCount() {
        return this.nearByLandMindCount != 0;
    }

    public String getSign() {
        if (isOpened) {
            if (isLandMine) {
                return LAND_MINE_SIGN;
            }
            if (hasLandMineCount()) {
                return String.valueOf(nearByLandMindCount);
            }
            return EMPTY_SIGN;
        }

        if (isFlagged) {
            return FLAG_SIGN;
        }

        return UNCHECKED_SIGN;
    }
}
