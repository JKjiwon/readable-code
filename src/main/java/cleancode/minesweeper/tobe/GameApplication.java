package cleancode.minesweeper.tobe;

// SRP: 프로그램의 진입점
public class GameApplication {

    public static void main(String[] args) {
        Minesweeper minesweeper = new Minesweeper();
        minesweeper.run();
    }

}

