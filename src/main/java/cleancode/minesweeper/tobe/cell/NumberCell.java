package cleancode.minesweeper.tobe.cell;

public class NumberCell extends Cell {

    private final int nearByLandMindCount;

    public NumberCell(int nearByLandMindCount) {
        this.nearByLandMindCount = nearByLandMindCount;
    }

    @Override
    public boolean isLandMine() {
        return false;
    }

    @Override
    public boolean hasLandMineCount() {
        return true;
    }

    @Override
    public String getSign() {
        if (isOpened) {
            return String.valueOf(nearByLandMindCount);
        }
        if (isFlagged) {
            return FLAG_SIGN;
        }
        return UNCHECKED_SIGN;
    }
}
