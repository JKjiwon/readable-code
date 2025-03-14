package cleancode.minesweeper.tobe.minesweeper;

import cleancode.minesweeper.tobe.game.GameInitializable;
import cleancode.minesweeper.tobe.game.GameRunnable;
import cleancode.minesweeper.tobe.minesweeper.board.GameBoard;
import cleancode.minesweeper.tobe.minesweeper.board.position.CellPosition;
import cleancode.minesweeper.tobe.minesweeper.config.GameConfig;
import cleancode.minesweeper.tobe.minesweeper.exception.GameException;
import cleancode.minesweeper.tobe.minesweeper.io.InputHandler;
import cleancode.minesweeper.tobe.minesweeper.io.OutputHandler;
import cleancode.minesweeper.tobe.minesweeper.user.UserAction;

public class Minesweeper implements GameInitializable, GameRunnable {

    private final GameBoard gameBoard;
    // Minesweeper 고수준 -> ConsoleInputHandler 고수준 :: 잘못됨
    // Minesweeper 고수준 -> InputHandler 저수준 :: 변경
    // 콘솔이 아니라 다른 방법으로 입출력을 받고 싶다면? InputHandler, OutputHandler 를 구현한 객체를 외부에서 주입하면된다.
    // Minesweeper 입장에서는 input/output 방식을 고려하지 않아도 된다.
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    //    private int gameStatus = 0; // 0: 게임 중, 1: 승리, -1: 패배

    public Minesweeper(GameConfig gameConfig) {
        this.gameBoard = new GameBoard(gameConfig.getGameLevel());
        this.inputHandler = gameConfig.getInputHandler();
        this.outputHandler = gameConfig.getOutputHandler();
    }

    @Override
    public void initialize() {
        gameBoard.initializeGame();
    }

    @Override
    public void run() {
        outputHandler.showGameStartComments();
        while (gameBoard.isInProgress()) {
            try {
                outputHandler.showBoard(gameBoard);

                CellPosition cellPosition = getCellIPositionFromUser();
                UserAction userAction = getUserActionInputFromUser();
                actOnCell(cellPosition, userAction);
            } catch (GameException e) {
                outputHandler.showExceptionMessage(e);
            } catch (Exception e) {
                outputHandler.showSimpleMessage("프로그램에 문제가 생겼습니다.");
            }
        }

        outputHandler.showBoard(gameBoard);
        if (gameBoard.isWinStatus()) {
            outputHandler.showGameWinningComment();
        }
        if (gameBoard.isLoseStatus()) {
            outputHandler.showGameLosingComment();
        }
    }

    private CellPosition getCellIPositionFromUser() {
        outputHandler.showCommentForSelectingCell();
        CellPosition cellPosition = inputHandler.getCellPositionFromUser();
        if (gameBoard.isInvalidCellPosition(cellPosition)) {
            throw new GameException("잘못된 좌표를 선택하셨습니다");
        }
        return cellPosition;
    }

    private UserAction getUserActionInputFromUser() {
        outputHandler.showCommentForUserAction();
        return inputHandler.getUserActionFromUser();
    }

    private void actOnCell(CellPosition cellPosition, UserAction userAction) {
        if (userAction.isFlag()) {
            gameBoard.flagAt(cellPosition);
            return;
        }

        if (userAction.isOpen()) {
            gameBoard.openAt(cellPosition);
            return;
        }

        throw new GameException("잘못된 번호를 선택하셨습니다.");
    }
}
