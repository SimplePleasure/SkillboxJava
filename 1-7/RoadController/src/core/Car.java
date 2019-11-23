package core;

public class Car
{
    private String number;
    private int height;
    private double weight;
    private boolean hasVehicle;
    private boolean isSpecial;

    // К значению car присваиваются переменные
    public Car(String carNumber, int carHeight, double carWeight, boolean carHasVehicle)
    {
        number = carNumber;  // Переменная номер автомобиля
        height = carHeight;  // Переменная высота авто
        weight = carWeight;  // Переменная вес авто
        hasVehicle = carHasVehicle;  //
        isSpecial = false;  // Автомобиль не относится к спецтранспорту
    }

    //=========================================================================

    public void setIsSpecial()
    {
        isSpecial = true;
    }

    //=========================================================================

    public String getNumber()
    {
        return number;
    }  //

    public int getHeight()
    {
        return height;
    }

    public boolean isSpecial()
    {
        return isSpecial;
    }

    public boolean hasVehicle()
    {
        return hasVehicle;
    }

    //=========================================================================

    double getWeight()
    {
        return weight;
    }

    //=========================================================================

    public String toString()
    {
        String special = isSpecial ? "СПЕЦТРАНСПОРТ " : "";
        return "\n=========================================\n" +
            special + "Автомобиль с номером " + number +
            ":\n\tВысота: " + height + " мм\n\tМасса: " + weight + " кг";
    }
}