import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RoadCamera
{
    public static void main(String[] args) throws IOException
    {
        //Параметры программы

        int maxOncomingSpeed = 40; // km/h
        int speedFineGrade = 20; // km/h
        int finePerGrade = 1000; // RUB
        int criminalSpeed = 180; // km/h

        //=============================================================

        System.out.println("Введите скорость автомобиля:");

        //Скорость автомобиля
        //=================================================== Integer.parseInt не изменить на примитив
        int oncomingSpeed = Integer.parseInt((new BufferedReader(new InputStreamReader(System.in))).readLine());
        boolean isCriminalSpeed = oncomingSpeed >= criminalSpeed;
        System.out.println(isCriminalSpeed);
        if(isCriminalSpeed)
        {
            System.out.println("Вызов полиции...");
        }
        else if(oncomingSpeed > maxOncomingSpeed + speedFineGrade)
        {
                int overSpeed = oncomingSpeed - maxOncomingSpeed;
                int gradesCount = overSpeed / speedFineGrade;
                int fine = finePerGrade * gradesCount;
                System.out.println("Сумма штрафа: " + fine);
        }
        else {
            System.out.println("Скорость не превышена");
        }
    }
}