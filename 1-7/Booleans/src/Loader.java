public class Loader
{
    public static void main(String[] args)
    {
        Integer milkAmount = 500; // ml
        Integer powderAmount = 1000; // g
        Integer eggsCount = 12; // items
        Integer sugarAmount = 500; // g
        Integer oilAmount = 500; // ml
        Integer appleCount = 19;


        //Блинчики
        String pancakes = "Pancakes";
        Integer pancakesPowder = 400;
        Integer pancakesSugar = 10;
        Integer pancakesMilk = 1000;
        Integer pancakesOil = 30;
        if (powderAmount >= pancakesPowder && sugarAmount >= pancakesSugar && milkAmount >= pancakesMilk && oilAmount >= pancakesOil )
        {
            // Находим коэффициент имеющихся продуктов к необходимым ингридиентам
            // Понимаю, что переменные очень криво назвал, но не стал уже исправлять.
            Integer pP = powderAmount / pancakesPowder;
            Integer sP = sugarAmount / pancakesSugar;
            Integer mP = milkAmount / pancakesMilk;
            Integer oP = oilAmount / pancakesOil;
/**
 * У меня здесь возник вопрос. Пришлось объявить quantityPancakes равной нулю, если значение не присвоено выдаёт ошибку
 * Error:(45, 65) java: variable quantityPancakes might not have been initialized
 * В других рецептах подобным переменным не присвоены значения и код работает. Почему так получается?
 */
            Integer quantityPancakes = 0;
            if ( pP <= sP && pP <= mP && pP <= oP)
            {
                quantityPancakes = pP;
            }
            if ( sP <= pP && sP <= mP && sP <= oP)
            {
                quantityPancakes = sP;
            }
            if ( mP <= pP && mP <= sP && mP <= oP)
            {
                quantityPancakes = mP;
            }
            if ( oP <= pP && oP <= sP && oP <= mP)
            {
                quantityPancakes = oP;
            }
            System.out.println( ":) "+pancakes + " can be cooked: " + quantityPancakes);
        }
        else
        {
            System.out.println("): Not enough ingridients to cook " + pancakes);
        }


        //Омлет
        String omlette = "Omlette";
        Integer omletMilk = 300;
        Integer omletPowder = 5;
        Integer omletEggs = 5;
        if(milkAmount >= omletMilk && powderAmount >= omletPowder && eggsCount >= omletEggs)
        {
            Integer oM = milkAmount / omletMilk;
            Integer oP = powderAmount / omletPowder;
            Integer oE = eggsCount / omletEggs;

            Integer quantityOmlet;
            if (oM <= oP && oM <=oE)
            {
                quantityOmlet = oM;
            }
            if (oP <= oM && oP <= oE)
            {
                quantityOmlet = oP;
            }
            else
            {
                quantityOmlet = oE;
            }
         System.out.println( ":) " + omlette + " can be cooked: " + quantityOmlet);
        }
        else
        {
            System.out.println("): Not enough ingridients to cook " + omlette);
        }




        //Яблочный пирог
        String applePie = "Apple pie";
        Integer applePieApples = 3;
        Integer applePieMilk = 100;
        Integer applePiePowder = 300;
        Integer applePieEggs = 4;
        if ( appleCount >= applePieApples && milkAmount >= applePieMilk && powderAmount >= applePiePowder && eggsCount >= applePieEggs)
        {
            Integer aPA = appleCount / applePieApples;
            Integer aPM = milkAmount / applePieMilk;
            Integer aPP = powderAmount / applePiePowder;
            Integer aPE = eggsCount / applePieEggs;

            Integer quantityApplePie;
            if ( aPA <= aPM && aPA <= aPP && aPA <= aPE)
            {
                quantityApplePie = aPA;
            }
            if ( aPM <= aPA && aPM <= aPP && aPM <= aPE)
            {
                quantityApplePie = aPM;
            }
            if ( aPP <= aPA && aPP <= aPM && aPP <= aPE)
            {
                quantityApplePie = aPP;
            }
            else
            {
                quantityApplePie = aPE;
            }
         System.out.println( ":) " + applePie + " can be cooked: " + quantityApplePie);
        }
        else
        {
            System.out.println("): Not enough ingridients to cook " + applePie);
        }
    }
}