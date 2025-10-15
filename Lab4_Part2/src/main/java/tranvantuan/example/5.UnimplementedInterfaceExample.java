package tranvantuan.example;

import java.util.logging.Logger;

interface Drawable {
    void draw();
}

class Circle implements Drawable {

    Logger logger = Logger.getLogger(Circle.class.getName());
    @Override
    public void draw() {
        logger.info(()-> "Drawing a circle");
    }

    static void main() {
        Drawable drawable = new Circle();
        drawable.draw();
    }
}
