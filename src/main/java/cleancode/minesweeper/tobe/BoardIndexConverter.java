package cleancode.minesweeper.tobe;

public class BoardIndexConverter {

    public static final char BASE_CHAR_FOR_COL = 'a';

    public int getSelectedRowIndex(String cellInput) {
        String celInputRow = cellInput.substring(1);
        return convertRowFrom(celInputRow);
    }

    public int getSelectedColIndex(String cellInput) {
        char cellInputCol = cellInput.charAt(0);
        return convertColFrom(cellInputCol);
    }

    // tip: 함수명을 전치사를 포함하여 표현하면 의미 전달이 더 좋은 경우가 있다.
    private int convertRowFrom(String celInputRow) {
        int rowIndex = Integer.parseInt(celInputRow) - 1;
        if (rowIndex < 0) {
            throw new GameException("잘못된 입력입니다.");
        }
        return rowIndex;
    }

    private int convertColFrom(char cellInputCol) {
        int colIndex = cellInputCol - BASE_CHAR_FOR_COL;
        if (colIndex < 0) {
            throw new GameException("잘못된 입력입니다.");
        }
        return colIndex;
    }
}
