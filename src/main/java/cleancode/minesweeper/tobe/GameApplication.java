package cleancode.minesweeper.tobe;

// SRP: 프로그램의 진입점
public class GameApplication {

    public static void main(String[] args) {
        GameLevel gameLevel = new Beginner();
        Minesweeper minesweeper = new Minesweeper(gameLevel);
        minesweeper.run();
    }

}

