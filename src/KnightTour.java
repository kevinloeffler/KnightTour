import java.util.ArrayList;

public class KnightTour {

    // Create global objects
    static ChessBoard board = new ChessBoard();
    static Knight knight;

    public static void main(String[] args) {

        // Initialize Board
        Field start = board.f21;
        Knight knight = new Knight(start);
        board.startingValues();
        board.placeholder.value = 100;

        board.visitedFields();
        board.openFields();

        ArrayList<Field> x = knight.getMoves(start);
        System.out.println(x.toString());


    }
}
