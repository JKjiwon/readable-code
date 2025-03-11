package cleancode.minesweeper.tobe.io.sign.old;

import cleancode.minesweeper.tobe.cell.CellSnapshot;
import cleancode.minesweeper.tobe.cell.CellSnapshotStatus;
import cleancode.minesweeper.tobe.io.sign.CellSignProvidable;

public class NumberCellSignProvider implements CellSignProvidable {

    @Override
    public boolean supports(CellSnapshot cellSnapshot) {
        return cellSnapshot.isSameStatus(CellSnapshotStatus.NUMBER);
    }

    @Override
    public String provide(CellSnapshot cellSnapshot) {
        return String.valueOf(cellSnapshot.getNearbyLandMineCount());
    }
}
