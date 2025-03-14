package cleancode.minesweeper.tobe.minesweeper.board;

import cleancode.minesweeper.tobe.minesweeper.board.cell.*;
import cleancode.minesweeper.tobe.minesweeper.board.position.CellPosition;
import cleancode.minesweeper.tobe.minesweeper.board.position.CellPositions;
import cleancode.minesweeper.tobe.minesweeper.board.position.RelativePosition;
import cleancode.minesweeper.tobe.minesweeper.gamelevel.GameLevel;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class GameBoard {

    private final Cell[][] board;
    public final int landMineCount;
    private GameStatus gameStatus;

    public GameBoard(GameLevel gameLevel) {
        board = new Cell[gameLevel.getRowSize()][gameLevel.getColSize()];
        landMineCount = gameLevel.getLandMineCount();
        initializeGameStatus();
    }

    public void initializeGame() {
        initializeGameStatus();
        CellPositions cellPositions = CellPositions.from(board);
        initializeEmptyCells(cellPositions);

        List<CellPosition> landMinePositions = cellPositions.extractRandomPositions(landMineCount);
        initializeLandMineCells(landMinePositions);

        List<CellPosition> numberPositionCandidates = cellPositions.subtract(landMinePositions);
        initializeNumberCells(numberPositionCandidates);
    }

    public void openAt(CellPosition cellPosition) {
        if (isLandMineCellAt(cellPosition)) {
            openOneCellAt(cellPosition);
            changeGameStatusToLose();
            return;
        }

        openSurroundedCells(cellPosition);
        checkIfGameIsOver();
    }

    public void flagAt(CellPosition cellPosition) {
        findCell(cellPosition).flag();
        checkIfGameIsOver();
    }

    public boolean isInvalidCellPosition(CellPosition cellPosition) {
        int rowSize = getRowSize();
        int colSize = getColSize();

        return cellPosition.isRowIndexMoreThanOrEqual(rowSize)
                || cellPosition.isColIndexMoreThanOrEqual(colSize);
    }

    public boolean isInProgress() {
        return gameStatus == GameStatus.IN_PROGRESS;
    }

    public boolean isWinStatus() {
        return gameStatus == GameStatus.WIN;
    }

    public boolean isLoseStatus() {
        return gameStatus == GameStatus.LOSE;
    }

    public int getRowSize() {
        return board.length;
    }

    public int getColSize() {
        return board[0].length;
    }

    public CellSnapshot getSnapshot(CellPosition cellPosition) {
        return findCell(cellPosition).getSnapshot();
    }

    private void initializeGameStatus() {
        gameStatus = GameStatus.IN_PROGRESS;
    }

    private void initializeEmptyCells(CellPositions cellPositions) {
        List<CellPosition> allPositions = cellPositions.getPositions();
        for (CellPosition position : allPositions) {
            updateCellAt(position, new EmptyCell());
        }
    }

    private void initializeLandMineCells(List<CellPosition> landMinePositions) {
        for (CellPosition position : landMinePositions) {
            updateCellAt(position, new LandMineCell());
        }
    }

    private void initializeNumberCells(List<CellPosition> numberPositionCandidates) {
        for (CellPosition candidatePosition : numberPositionCandidates) {
            int count = countNearbyLandMines(candidatePosition);
            if (count != 0) {
                updateCellAt(candidatePosition, new NumberCell(count));
            }
        }
    }

    private void updateCellAt(CellPosition position, Cell cell) {
        board[position.getRowIndex()][position.getColIndex()] = cell;
    }

    private int countNearbyLandMines(CellPosition cellPosition) {
        int rowSize = getRowSize();
        int colSize = getColSize();

        return (int) calculateSurroundedPositions(cellPosition, rowSize, colSize).stream()
                .filter(this::isLandMineCellAt)
                .count();
    }

    private List<CellPosition> calculateSurroundedPositions(CellPosition cellPosition, int RowSize, int ColSize) {
        return RelativePosition.SURROUNDED_POSITION.stream()
                .filter(cellPosition::canCalculatePositionBy)
                .map(cellPosition::calculatePositionBy)
                .filter(position -> position.isRowIndexLessThan(RowSize))
                .filter(position -> position.isColIndexLessThan(ColSize))
                .toList();
    }

    private void openOneCellAt(CellPosition cellPosition) {
        findCell(cellPosition).open();
    }

//    private void openSurroundedCellsV1(CellPosition cellPosition) {
//        if (isOpenedCellAt(cellPosition)) {
//            return;
//        }
//        if (isLandMineCellAt(cellPosition)) {
//            return;
//        }
//
//        findCell(cellPosition).open();
//
//        if (isNumberCellAt(cellPosition)) {
//            return;
//        }
//
//        List<CellPosition> surroundedPositions = calculateSurroundedPositions(cellPosition, getRowSize(), getColSize());
//        surroundedPositions.forEach(this::openSurroundedCells);
//    }

    private void openSurroundedCells(CellPosition cellPosition) {
        Deque<CellPosition> deque = new ArrayDeque<>();
        deque.push(cellPosition);

        while (!deque.isEmpty()) {
            openAndPushCellAt(deque);
        }
    }

    private void openAndPushCellAt(Deque<CellPosition> deque) {
        CellPosition currentPosition = deque.pop();
        if (isOpenedCellAt(currentPosition)) {
            return;
        }

        if (isLandMineCellAt(currentPosition)) {
            return;
        }

        findCell(currentPosition).open();

        if (isNumberCellAt(currentPosition)) {
            return;
        }

        List<CellPosition> surroundedPositions = calculateSurroundedPositions(currentPosition, getRowSize(), getColSize());
        surroundedPositions.forEach(deque::push);
    }

    private boolean isLandMineCellAt(CellPosition cellPosition) {
        return findCell(cellPosition).isLandMine();
    }

    private boolean isOpenedCellAt(CellPosition cellPosition) {
        return findCell(cellPosition).isOpened();
    }

    private boolean isNumberCellAt(CellPosition cellPosition) {
        return findCell(cellPosition).hasLandMineCount();
    }

    private void checkIfGameIsOver() {
        if (isAllCellIsChecked()) {
            changeGameStatusToWin();
        }
    }

    private void changeGameStatusToWin() {
        gameStatus = GameStatus.WIN;
    }

    private void changeGameStatusToLose() {
        gameStatus = GameStatus.LOSE;
    }

    private boolean isAllCellIsChecked() {
        Cells cells = Cells.from(board);
        return cells.isAllChecked();
    }

    private Cell findCell(CellPosition cellPosition) {
        return board[cellPosition.getRowIndex()][cellPosition.getColIndex()];
    }
}
