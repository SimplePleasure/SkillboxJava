import java.util.Random;

public class New {
    public static void main (String[] args)
    {


        Integer moneyAmount = 20000;
        Boolean hasPerviousVisa = true;
        Boolean hasConviction = false;

        if((moneyAmount > 45000 || hasPerviousVisa ) && !hasConviction)
        {
            System.out.println("Visa should be granted");
        }
        else
        {
            System.out.println("Visa cannot be granted");
        }
    }
}
