import core.*;
import core.Camera;

public class RoadController
{ // Создание переменных
    //Double passengerCarMaxWeight
    public static double passengerCarMaxWeight = 3500.0; // kg
    //Integer passangerCarMaxHeight
    public static int passengerCarMaxHeight = 2000; // mm
    //Integer controllerMaxHeight
    public static int controllerMaxHeight = 3500; // mm

    //Integer passengerCarPrice
    public static int passengerCarPrice = 100; // RUB
    //Integer cargoCarPrice
    public static int cargoCarPrice = 250; // RUB
    //Integer vehicleAdditionalPrice
    public static int vehicleAdditionalPrice = 200; // RUB

    //Integer maxOncomingSpeed
    public static int maxOncomingSpeed = 59; // km/h
    //Integer speedFineGrade
    public static int speedFineGrade = 20; // km/h
    //Integer finePerGrade
    public static int finePerGrade = 500; // RUB
    //Integer criminalSpeed
    public static int criminalSpeed = 160; // km/h

    public static void main(String[] args) // Создание метода Main
    {
        //Integer i
        for(int i = 0; i < 10; i++)  // Создание цикла
        {
            Car car = Camera.getNextCar();
            System.out.println(car);
            System.out.println("Скорость: " + Camera.getCarSpeed(car) + " км/ч");



            /**
             * Проверка на наличие номера в списке номеров нарушителей
             */
            //Boolean policeCalled
            boolean policeCalled = false;
            //Как я понял, для объекта String нету примитива
            for(String criminalNumber : Police.getCriminalNumbers())
            {
                //String carNumber
                String carNumber = car.getNumber();
                if(carNumber.equals(criminalNumber)) {
                    Police.call("автомобиль нарушителя с номером " + carNumber);
                    blockWay("не двигайтесь с места! За вами уже выехали!");
                    break;
                }
            }

            if(Police.wasCalled()) {
                continue;
            }

            /**
             * Пропускаем автомобили спецтранспорта
             */
            if(car.isSpecial()) {
                openWay();
                continue;
            }

            /**
             * Проверяем высоту и массу автомобиля, вычисляем стоимость проезда
             */
            //Integer carHeight
            int carHeight = car.getHeight();
            //Integer price
            int price = 0;
            if(carHeight > controllerMaxHeight)
            {
                blockWay("высота вашего ТС превышает высоту пропускного пункта!");
                continue;
            }
            else if(carHeight > passengerCarMaxHeight)
            {
                //Double weight
                double weight = WeightMeter.getWeight(car);
                //Грузовой автомобиль
                if(weight > passengerCarMaxWeight)
                {
                    price = cargoCarPrice;
                    if(car.hasVehicle()) {
                        price = price + vehicleAdditionalPrice;
                    }
                }
                //Легковой автомобиль
                else {
                    price = passengerCarPrice;
                }
            }
            else {
                price = passengerCarPrice;
            }

            /**
             * Проверка скорости подъезда и выставление штрафа
             */
            //Integer carSpeed
            int carSpeed = Camera.getCarSpeed(car);
            if(carSpeed > criminalSpeed)
            {
                Police.call("cкорость автомобиля - " + carSpeed + " км/ч, номер - " + car.getNumber());
                blockWay("вы значительно превысили скорость. Ожидайте полицию!");
                continue;
            }
            else if(carSpeed > maxOncomingSpeed)
            {
                //Integer overSpeed
                int overSpeed = carSpeed - maxOncomingSpeed;
                //Integer totalFine
                int totalFine = finePerGrade * (1 + overSpeed / speedFineGrade);
                System.out.println("Вы превысили скорость! Штраф: " + totalFine + " руб.");
                price = price + totalFine;
            }

            /**
             * Отображение суммы к оплате
             */
            System.out.println("Общая сумма к оплате: " + price + " руб.");
        }

    }

    /**
     * Открытие шлагбаума
     */
    public static void openWay()
    {
        System.out.println("Шлагбаум открывается... Счастливого пути!");
    }

    public static void blockWay(String reason)
    {
        System.out.println("Проезд невозможен: " + reason);
    }
}