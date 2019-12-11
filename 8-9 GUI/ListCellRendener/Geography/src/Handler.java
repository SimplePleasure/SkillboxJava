import javax.swing.*;
import java.util.ArrayList;

public class Handler {

    ArrayList<Countries> list = new ArrayList<>();
    Screen screen;

    Handler(Screen sc) {
        screen = sc;
    }

    public void build() {

        list.add(new Countries("Эстония", "Таллин", "Евро", 45227, 1325000));
        list.add(new Countries("Латвия", "Рига", "Евро", 64589, 1092000));
        list.add(new Countries("Литва", "Вильнюс", "Евро", 65300, 2794000));
        list.add(new Countries("Беларусь", "Минск", "Белорусский рубль",
                207595, 9508000));
        list.add(new Countries("Украина", "Киев", "Гривны", 603628, 42220000));
        list.add(new Countries("Молдова","Кишинёв","Молдавский Лей", 33846,
                3550000));
        list.add(new Countries("Грузия","Тбилиси","Лари", 69700, 3717000));
        list.add(new Countries("Армения","Ереван","Драм", 29743, 2973000));
        list.add(new Countries("Азербайджан","Баку","Манат", 86600, 9898000));
        list.add(new Countries("Абхазия", "Сухум", "Рубль / Лари", 8660000, 24300));
        list.add(new Countries("Южная Осетия", "Цхинвал", "Рубль",
                3900, 53000));
        list.add(new Countries("Казахстан","Нур-Султан","Тенге",
                2725000, 18040000));
        list.add(new Countries("Узбекистан", "Ташкент", "Сум", 449000, 32390000));
        list.add(new Countries("Таджикистан", "Душанбэ", "Сомони", 143000, 8921000));
        list.add(new Countries("Киргизия", "Бишкек", "Сом", 199000, 6200000));
        list.add(new Countries("Туркменистан", "Ашхабад", "Манат", 448000, 5758000));


        DefaultListModel<Countries> listModel = new DefaultListModel<>();
        for (int i=0; i<list.size(); i++) {
            listModel.addElement(list.get(i));
        }

        CountryRendener countryRendener = new CountryRendener(this);

        screen.getList().setModel(listModel);
        screen.getList().setCellRenderer(countryRendener);
    }

    void showInfo(int countryIndex) {

        Countries country = list.get(countryIndex);
        screen.setImages(country.country);
        screen.countryName.setText("\t\t\t"+country.country);
        screen.capital.setText(String.format("Столица: %17s", country.capital));
        screen.currency.setText(String.format("Валюта: %17s", country.currency));
        screen.square.setText(String.format("Площадь: %8d", country.area));
        screen.population.setText(String.format("Население: %8d", country.population));


    }


}
