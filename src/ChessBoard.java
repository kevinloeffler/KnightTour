import java.io.FileDescriptor;
import java.util.ArrayList;
import java.util.Collections;

public class ChessBoard {

    // Row 8
    Field f0 = new Field(0, 0, 0);
    Field f1 = new Field(1, 1, 0);
    Field f2 = new Field(2, 2, 0);
    Field f3 = new Field(3, 3, 0);
    Field f4 = new Field(4, 4, 0);
    Field f5 = new Field(5, 5, 0);
    Field f6 = new Field(6, 6, 0);
    Field f7 = new Field(7, 7, 0);

    // Row 7
    Field f8 = new Field(8, 0, 1);
    Field f9 = new Field(9, 1, 1);
    Field f10 = new Field(10, 2, 1);
    Field f11 = new Field(11, 3, 1);
    Field f12 = new Field(12, 4, 1);
    Field f13 = new Field(13, 5, 1);
    Field f14 = new Field(14, 6, 1);
    Field f15 = new Field(15, 7, 1);

    // Row 6
    Field f16 = new Field(16, 0, 2);
    Field f17 = new Field(17, 1, 2);
    Field f18 = new Field(18, 2, 2);
    Field f19 = new Field(19, 3, 2);
    Field f20 = new Field(20, 4, 2);
    Field f21 = new Field(21, 5, 2);
    Field f22 = new Field(22, 6, 2);
    Field f23 = new Field(23, 7, 2);

    // Row 5
    Field f24 = new Field(24, 0, 3);
    Field f25 = new Field(25, 1, 3);
    Field f26 = new Field(26, 2, 3);
    Field f27 = new Field(27, 3, 3);
    Field f28 = new Field(28, 4, 3);
    Field f29 = new Field(29, 5, 3);
    Field f30 = new Field(30, 6, 3);
    Field f31 = new Field(31, 7, 3);

    // Row 4
    Field f32 = new Field(32, 0, 4);
    Field f33 = new Field(33, 1, 4);
    Field f34 = new Field(34, 2, 4);
    Field f35 = new Field(35, 3, 4);
    Field f36 = new Field(36, 4, 4);
    Field f37 = new Field(37, 5, 4);
    Field f38 = new Field(38, 6, 4);
    Field f39 = new Field(39, 7, 4);

    // Row 3
    Field f40 = new Field(40, 0, 5);
    Field f41 = new Field(41, 1, 5);
    Field f42 = new Field(42, 2, 5);
    Field f43 = new Field(43, 3, 5);
    Field f44 = new Field(44, 4, 5);
    Field f45 = new Field(45, 5, 5);
    Field f46 = new Field(46, 6, 5);
    Field f47 = new Field(47, 7, 5);

    // Row 2
    Field f48 = new Field(48, 0, 6);
    Field f49 = new Field(49, 1, 6);
    Field f50 = new Field(50, 2, 6);
    Field f51 = new Field(51, 3, 6);
    Field f52 = new Field(52, 4, 6);
    Field f53 = new Field(53, 5, 6);
    Field f54 = new Field(54, 6, 6);
    Field f55 = new Field(55, 7, 6);

    // Row 1
    Field f56 = new Field(56, 0, 7);
    Field f57 = new Field(57, 1, 7);
    Field f58 = new Field(58, 2, 7);
    Field f59 = new Field(59, 3, 7);
    Field f60 = new Field(60, 4, 7);
    Field f61 = new Field(61, 5, 7);
    Field f62 = new Field(62, 6, 7);
    Field f63 = new Field(63, 7, 7);

    // Placeholder Field
    Field placeholder = new Field(-1, -1, -1);

    // Array with all Fields for iteration purposes
    Field[] fields = {f0, f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16, f17, f18, f19, f20, f21, f22, f23, f24, f25, f26, f27, f28, f29, f30, f31, f32, f33, f34, f35, f36, f37, f38, f39, f40, f41, f42, f43, f44, f45, f46, f47, f48, f49, f50, f51, f52, f53, f54, f55, f56, f57, f58, f59, f60, f61, f62, f63};

    void visitedFields() {
        System.out.print("Visited Fields: ");
        for (Field f : fields) {
            if (f.visited) {
                System.out.print(f.id + ", ");
            }
        }
        System.out.println();
    }

    void openFields() {
        System.out.print("Open Fields: ");
        for (Field f : fields) {
            if (!f.visited) {
                System.out.print(f.id + ", ");
            }
        }
        System.out.println();
    }

    // Helper function for possibleMove()
    private Field makeMove(Field n, int id, int x, int y) {
        n.id += id;
        n.x += x;
        n.y += y;
        return n;
    }

    // Helper function for moves()
    private Field evalMove(Field n, int id, int x, int y) {
        // Local copy
        Field startField = new Field(n.id, n.x, n.y);
        // The Field after the move
        Field newField = makeMove(startField, id, x, y);
        // Check if the new Field is still on the board
        if (newField.x >= 0 && newField.x <= 7 && newField.y >= 0 && newField.y <= 7) {
            return newField;
        } else {
            return null;
        }
    }

    // outputs a list of all possible moves from a start field
    ArrayList<Field> allFieldsInRange(Field start) {
        ArrayList<Field> output = new ArrayList<>();
        // 8 possible moves
        output.add(evalMove(start, -10, -2, -1));
        output.add(evalMove(start, -17, -1, -2));
        output.add(evalMove(start, -15, 1, -2));
        output.add(evalMove(start, -6, 2, -1));
        output.add(evalMove(start, 10, 2, 1));
        output.add(evalMove(start, 17, 1, 2));
        output.add(evalMove(start, 15, -1, 2));
        output.add(evalMove(start, 6, -2, 1));
        // remove all null returns
        output.removeAll(Collections.singletonList(null));
        return output;
    }

    void startingValues() {
        for (Field field : fields) {
            ArrayList<Field> amountOfMoves = allFieldsInRange(field);
            field.value = amountOfMoves.size();
        }
    }

    // Print Method
    public String toString(ArrayList<Field> list) {
        StringBuilder output = new StringBuilder();
        for (Field item : list) {
            output.append(item.toString());
        }
        return output.toString();
    }

}
