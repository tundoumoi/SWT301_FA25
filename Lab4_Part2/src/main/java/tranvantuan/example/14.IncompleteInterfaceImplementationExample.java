package tranvantuan.example;

import java.util.logging.Level;
import java.util.logging.Logger;

class IncompleteInterfaceImplementationExample {

    private static final Logger LOGGER =
            Logger.getLogger(IncompleteInterfaceImplementationExample.class.getName());

    static void main() {
        Shape s = new Square(5.0);
        s.draw();                 // dùng draw() -> hết "never used"
        s.resize(1.5);            // dùng resize() -> hết "never used"
        s.draw();                 // vẽ lại sau khi resize
    }

    interface Shape {
        void draw();
        void resize(double factor);
    }

    static final class Square implements Shape {
        private double side;

        Square(double side) {
            if (side <= 0) throw new IllegalArgumentException("Side must be > 0");
            this.side = side;
        }

        @Override
        public void draw() {
            LOGGER.log(Level.INFO, "Drawing square with side = {0}", side);
        }

        @Override
        public void resize(double factor) {
            if (factor <= 0) {
                LOGGER.log(Level.WARNING, "Invalid resize factor: {0}. Must be > 0.", factor);
                return;
            }
            double old = side;
            side = side * factor;
            LOGGER.log(Level.INFO, "Resized square: {0} -> {1} (factor={2})",
                    new Object[]{old, side, factor});
        }
    }
}
