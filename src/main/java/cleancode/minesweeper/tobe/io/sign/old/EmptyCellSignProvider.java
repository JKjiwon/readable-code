package cleancode.minesweeper.tobe.io.sign.old;

import cleancode.minesweeper.tobe.cell.CellSnapshot;
import cleancode.minesweeper.tobe.cell.CellSnapshotStatus;
import cleancode.minesweeper.tobe.io.sign.CellSignProvidable;

public class EmptyCellSignProvider implements CellSignProvidable {

    private static final String EMPTY_SIGN = "■";

    @Override
    public boolean supports(CellSnapshot cellSnapshot) {
        return cellSnapshot.isSameStatus(CellSnapshotStatus.EMPTY);
    }

    @Override
    public String provide(CellSnapshot cellSnapshot) {
        return EMPTY_SIGN;
    }
}
