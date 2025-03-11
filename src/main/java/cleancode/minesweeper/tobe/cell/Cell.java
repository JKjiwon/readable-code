package cleancode.minesweeper.tobe.cell;

public interface Cell {

    void flag();

    void open();

    boolean isChecked();

    boolean isOpened();

    boolean isLandMine();

    boolean hasLandMineCount();

    CellSnapshot getSnapshot();
}
