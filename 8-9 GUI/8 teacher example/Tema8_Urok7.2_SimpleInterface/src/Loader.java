//Создать простой работающий интерфейс из трёх экранов без оформления и
// изображений (только элементы форм): ввод телефона,
// ввод кода подтверждения и отображение списка контактов
import javax.swing.*;

public class Loader {
    public static void main(String[] args) {
        JFrame frame = new CangeForms();
        frame.setVisible(true);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

    }
}
