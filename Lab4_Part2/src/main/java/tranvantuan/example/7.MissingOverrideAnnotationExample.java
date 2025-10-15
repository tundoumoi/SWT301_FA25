package tranvantuan.example;

import java.util.logging.Level;
import java.util.logging.Logger;

class Animal {
    private static final Logger LOGGER = Logger.getLogger(Animal.class.getName());
    void speak() { LOGGER.log(Level.INFO, "Animal speaks"); }
}

class Dog extends Animal {
    private static final Logger LOGGER = Logger.getLogger(Dog.class.getName());
    @Override
    void speak() { LOGGER.log(Level.INFO, "Dog barks"); }

    static void main() {
        Animal a = new Dog();     // <-- tham chiếu Dog qua kiểu Animal
        a.speak();                // sử dụng phương thức để bật logger
    }
}

