package cleancode.minesweeper.tobe.io.sign.old;

import cleancode.minesweeper.tobe.cell.CellSnapshot;
import cleancode.minesweeper.tobe.io.sign.CellSignProvidable;
import cleancode.minesweeper.tobe.io.sign.UncheckedCellSignProvider;

import java.util.List;

public class CellSignFinder {
    private static final List<CellSignProvidable> CELL_SIGN_FINDER = List.of(
            new EmptyCellSignProvider(),
            new FlagCellSignProvider(),
            new LandMineCellSignProvider(),
            new NumberCellSignProvider(),
            new UncheckedCellSignProvider());


    public String findCellSignFrom(CellSnapshot snapshot) {
        return CELL_SIGN_FINDER.stream()
                .filter(provider -> provider.supports(snapshot))
                .findFirst()
                .map(provider -> provider.provide(snapshot))
                .orElseThrow(() -> new IllegalArgumentException("확인할 수 없는 셀입니다."));
    }
}
