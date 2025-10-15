package tranvantuan.example;

import java.util.logging.Level;
import java.util.logging.Logger;

class ViolationOfEncapsulationExample {

    private static final Logger LOGGER = Logger.getLogger(ViolationOfEncapsulationExample.class.getName());
    static void main() {
        User user = new User("Alice", 30);
        user.display();
        LOGGER.log(Level.INFO, "Access via accessors -> name={0}, age={1}",
                new Object[]{user.name(), user.age()});
    }
    record User(String name, int age) {

            void display() {
                LOGGER.log(Level.INFO, "User[name={0}, age={1}]", new Object[]{name, age});
            }
        }
}
