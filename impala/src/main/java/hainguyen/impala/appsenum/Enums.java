package hainguyen.impala.appsenum;

/**
 * Created by nguyenminhhai on 22/5/16.
 */
public class Enums {
    public enum EmailErrorType {
        EMPTY(0),
        INVALID(1);

        private final int id;

        EmailErrorType(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }
}
