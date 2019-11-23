public class main {
    public static void main(String[] args)
    {

//        boolean friendShip[][] = {
//                {false, true, true, false},
//                {true, false, false, false},
//                {true, true, false, false},
//                {false, false, true, false}
//         };
//        String names[] = {"Vasya", "Petya", "Masha", "Katya"};
//
//        for(int i = 0; i<friendShip.length; i++)
//        {
//            for (int j = 0; j<4; j++)
//            {
//                if(names[i].equals(names[j])) {continue;}
//                System.out.println(names[i] + " is friend of" + names[j] + ": " + friendShip[i][j]);
//            }
//        }







        String colorRainbow[] = {"Red", "Orange", "Yellow", "Green", "Cyan", "Blue", "Violet"};
        for (int i = colorRainbow.length-1; i>=0; i--)
        {
            System.out.println(colorRainbow[i]);
        }





        //45 07 *** ***
        String numb = "4507000000";

        String n[] = new String[1000000];
        for(int i = 0; i<1000000; i++)
        {
            long series = Long.parseLong(numb) + i;
            n[i]= Long.toString(series);
        }



        for (int j = 0; j<1000000; j++)
        {
            System.out.println(n[j]);
        }


        String chessDesk[][]= {
                {"a1\t", "a2\t", "a3\t", "a4\t", "a5\t", "a6\t", "a7\t", "a8\t"},
                {"b1\t", "b2\t", "b3\t", "b4\t", "b5\t", "b6\t", "b7\t", "b8\t"},
                {"c1\t", "c2\t", "c3\t", "c4\t", "c5\t", "c6\t", "c7\t", "c8\t"},
                {"d1\t", "d2\t", "d3\t", "d4\t", "d5\t", "d6\t", "d7\t", "d8\t"},
                {"e1\t", "e2\t", "e3\t", "e4\t", "e5\t", "e6\t", "e7\t", "e8\t"},
                {"f1\t", "f2\t", "f3\t", "f4\t", "f5\t", "f6\t", "f7\t", "f8\t"},
                {"g1\t", "g2\t", "g3\t", "g4\t", "g5\t", "g6\t", "g7\t", "g8\t"},
                {"h1\t", "h2\t", "h3\t", "h4\t", "h5\t", "h6\t", "h7\t", "h8\t"}
        };
        for (int i =0; i<chessDesk.length; i++)
        {
            for (int j = 0; j<chessDesk.length; j++)
            {
                System.out.print(chessDesk[i][j]);
                if(j == 7) {
                    System.out.println("\n");
                }
            }

        }








    }
}




