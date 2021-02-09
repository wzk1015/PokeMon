import types.Type;
import types.TypeTable;
import utils.IO;

public class Main {
    public static void main(String[] args) {
        IO.println(TypeTable.ratio(Type.water, Type.fire),
                TypeTable.ratio(Type.electr, Type.grass),
                TypeTable.ratio(Type.psychc, Type.dark));
    }
}
