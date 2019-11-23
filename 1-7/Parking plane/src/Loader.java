import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Loader {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<String> planeParking = new ArrayList<>();
        for (int i = 0; ; i++) {
            System.out.println("Please, enter boart number:");
            String planeBoardNumber = reader.readLine();
            planeParking.add(planeBoardNumber);
            if (planeParking.size() == 5) {
                break;
            }
        }


        System.out.println("Parking is full. To let parking out - type: exit all/exit last");
        String letOut = reader.readLine();
        if (letOut.equals("exit all")) {
            for (int i = planeParking.size() - 1; i >= 0; i--) {
                System.out.println("Plane " + planeParking.get(i) + " exit.");
                planeParking.remove(i);
            }
        } else if (letOut.equals("exit last")) {
            System.out.println("Plane " + planeParking.get(planeParking.size() - 1) + " exit.");
            planeParking.remove(planeParking.size() - 1);
        }

    }
}
