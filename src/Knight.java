import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Knight {
    Field currentPosition;
    ArrayList<Field> history = new ArrayList<>();

    public Knight(Field start) {
        this.currentPosition = start;
        history.add(start);
        // TODO: add start field to history and change value of fields around
    }

    Field getFieldByID(int id) {
        return Arrays.stream(KnightTour.board.fields)
                .filter(field -> field.id == id)
                .findAny()
                .orElse(null);
    }

    // outputs a list of all possible moves from a start field
    ArrayList<Field> getMoves(Field start) {
        ArrayList<Field> output = new ArrayList<>();
        // 8 possible moves
        output.add(findValidMoves(start, -10, -2, -1));
        output.add(findValidMoves(start, -17, -1, -2));
        output.add(findValidMoves(start, -15, 1, -2));
        output.add(findValidMoves(start, -6, 2, -1));
        output.add(findValidMoves(start, 10, 2, 1));
        output.add(findValidMoves(start, 17, 1, 2));
        output.add(findValidMoves(start, 15, -1, 2));
        output.add(findValidMoves(start, 6, -2, 1));
        // remove all null returns
        output.removeAll(Collections.singletonList(null));
        return output;
    }

    // Helper function for getMoves()
    private Field findValidMoves(Field start, int id, int x, int y) {
        // Local temporary copy
        Field localCopy = new Field(start.id, start.x, start.y);
        if (!checkIfMoveIsValid(localCopy, x, y)) {
            return null;
        }





        int absoluteID = start.id + id;
        // The Field after the move
        Field newField = getFieldByID(absoluteID);
        // Check Move
        checkIfMoveIsValid(start, x, y);
        // Local copy
        if (newField == null) {
            return null; // ID out of bounds
        }
        // Check if the new Field is still on the board and not yet visited
        if (newField.x >= 0 && newField.x <= 7 && newField.y >= 0 && newField.y <= 7 && !newField.visited) {
            return newField; // return valid field
        } else {
            return null; // Field out of bounds
        }
    }

    Field evaluateMoves(ArrayList<Field> possibleFields) {
        Field bestField = KnightTour.board.placeholder;
        for (Field field : possibleFields) {
            if (field.value < bestField.value) {
                bestField = field;
            }
        }
        if (bestField != KnightTour.board.placeholder) { // Check if the placeholder is overwritten
            return bestField; // if yes -> return the field with the lowest value
        } else {
            return null; // if not -> no possible fields to move to
        }
    }

    // Check if move goes over the board
    boolean checkIfMoveIsValid(Field start, int x, int y) {
        int newX = start.x + x;
        int newY = start.y + y;
        return newX >= 0 && newX <= 7 && newY >= 0 && newY <= 7;
    }

    void move(Field target) {
        target.visited = true;
        KnightTour.knight.currentPosition = target;
        KnightTour.knight.history.add(target);
        ArrayList<Field> affectedFields = new ArrayList<>(KnightTour.board.allFieldsInRange(target));
        try {
            for (Field field : affectedFields) {
                field.value -= 1;
            }
        } catch (NullPointerException e) {
            // NullPointerException may occur when there are no more Fields left
            // Error is automatically handled on the next loop on Step 1.
        }
    }

    boolean finished() {
        for (Field field : KnightTour.board.fields) {
            if (!field.visited) {
                return false;
            }
        }
        return true;
    }


}

/*
    MOVE
    1. Get all possible moves
        0: No more possible moves from here -> REVERT
        1+: -> Step 2
    2. Filter forbidden Fields
    3. Evaluate best move (find lowest Value)
    4. Make move
        A. Update current position
        B. Set visited of current position to 'true'
        B. Update the value of all fields around
        C. Add move to history
    5. Did we Finish?
        Yes: Yay
        No: -> Step 1

    REVERT
    1. Get last move in history
    2. Make reverse
        A. Delete move from history
        B. Set visited of current position to 'false'
        B. Update the value of all fields around
        C. Update current position
        D. Add previous Field to forbidden of the current position
    3. MOVE
 */
