import jdk.nashorn.internal.objects.annotations.Function;

public class Loader {
    public static void main (String[] args)
    {
        Integer dimaAge = 30;
        Integer mishaAge = 20;
        Integer vasyaAge = 20;

        Integer oldest = 0;
        Integer middle = 0;
        Integer youngest = 0;


        if ((dimaAge != mishaAge) && (dimaAge != vasyaAge) && ( dimaAge > mishaAge && dimaAge > vasyaAge))
        {
            oldest = dimaAge;
            if ( mishaAge != vasyaAge && mishaAge > vasyaAge)
            {
                middle = mishaAge;
                youngest = vasyaAge;
            }
            else if (vasyaAge != mishaAge && vasyaAge > mishaAge)
            {
                middle = vasyaAge;
                youngest = mishaAge;
            }
            else
            {
                youngest = mishaAge;
            }

        }
        //=============================================================================================
        else if ( dimaAge == mishaAge && dimaAge > vasyaAge )
        {
            oldest = dimaAge;
            youngest = vasyaAge;
        }
        else if (dimaAge == vasyaAge && dimaAge > mishaAge)
        {
            oldest = dimaAge;
            youngest = mishaAge;
        }
        else if ( dimaAge == mishaAge && dimaAge == vasyaAge)
        {
            oldest = dimaAge;
            middle = dimaAge;
            youngest = dimaAge;
        }
        // ============================================================================================

        if ( vasyaAge != dimaAge && vasyaAge != mishaAge && vasyaAge > dimaAge && vasyaAge > mishaAge)
        {
            oldest = vasyaAge;
            if ( dimaAge != mishaAge && dimaAge > mishaAge)
            {
                middle = dimaAge;
                youngest = mishaAge;
            }
            else if ( mishaAge != dimaAge && mishaAge > dimaAge)
            {
                middle = mishaAge;
                youngest = dimaAge;
            }
            else
            {
                youngest = mishaAge;
            }
        }
        else if ( vasyaAge == mishaAge && vasyaAge > dimaAge)
        {
            oldest = vasyaAge;
            youngest = dimaAge;
        }
        //===========================================================================
        if ( mishaAge != dimaAge && mishaAge != vasyaAge && mishaAge> dimaAge && mishaAge > vasyaAge)
        {
            oldest = mishaAge;
            if ( dimaAge != vasyaAge && dimaAge > vasyaAge)
            {
                middle = dimaAge;
                youngest = vasyaAge;
            }
            else if (vasyaAge != dimaAge && vasyaAge > dimaAge)
            {
                middle = vasyaAge;
                youngest = dimaAge;
            }
            else
            {
                youngest = dimaAge;
            }
        }
        Boolean o = oldest > 0;
        Boolean m = middle > 0;
        Boolean y = youngest > 0;

        if (o) {
        System.out.println ("oldest: " + oldest);}
        if (m) {
        System.out.println ("middle: " + middle);}
        if (y) {
        System.out.println ("youngest: " +youngest);}




    }
}
