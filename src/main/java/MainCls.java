import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MainCls {
    public static void main(String[] args) {

        MainCls cls = new MainCls();
        cls.method();
    }

    void method() {
        Optional<Map<String, String>> opt = Optional.of(new HashMap<>());
        opt = Optional.ofNullable(null);
        opt = Optional.empty();


        System.out.println(opt.isPresent());
    }

}
