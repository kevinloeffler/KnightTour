import java.util.ArrayList;

public class Field {

    int id;
    int x;
    int y;

    int value;                  // Denotes the amount other Fields that could move onto this Field
    boolean visited;            // This field has been passed by the Knight and can be ignored in the future
    ArrayList<Field> forbidden; // Stores all Fields that have been tried from here and didn't work

    public Field(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.value = 0;
        this.visited = false;
        this.forbidden = new ArrayList<>();
    }

    public ArrayList<Field> getForbidden() {
        return forbidden;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }

}
