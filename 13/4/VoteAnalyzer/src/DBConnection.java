import java.sql.*;

/**
 * Created by Danya on 24.02.2016.
 */
public class DBConnection {
    private static Connection connection;

    private static String dbName = "learn";
    private static String dbUser = "root";
    private static String dbPass = "12541254";
    private static long totalInsertTime = 0;

//            1)batch approach==================================================
    private static PreparedStatement preparedStatement;                 //======
    private static int butchCounter = 0;                                //======
    private static int butchSize = 20000;                               //======

//            2)buffering approach==============================================
    private static StringBuilder buffer = new StringBuilder();          //======
    private static int bufSize = 256_000;                               //======


    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName + "?user=" +
                                                dbUser + "&password=" + dbPass + "&rewriteBatchedStatements=true");
                connection.createStatement().execute("TRUNCATE TABLE voter_count");
//                connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
//                connection.createStatement().execute("CREATE TABLE voter_count(" +
//                        "id INT NOT NULL AUTO_INCREMENT, " +
//                        "name TINYTEXT NOT NULL, " +
//                        "birthDate DATE NOT NULL, " +
//                        "`count` INT NOT NULL, " +
//                        "PRIMARY KEY(id))");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void countVoter(String name, String birthDay) throws SQLException {


//          1)batch approach================================================================
        if (preparedStatement == null) {
            preparedStatement = getConnection().prepareStatement("INSERT INTO voter_count " +
                    "(name, birthDate, `count`) VALUES (?,?,?)  ON DUPLICATE KEY UPDATE `count`=`count`+1 ");
        }
        birthDay = birthDay.replace('.', '-');
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, birthDay);
        preparedStatement.setInt(3, 1);
        preparedStatement.addBatch();
        if (butchCounter++ == butchSize) executeQuery();

//          2)buffering approach============================================================
//        if (buffer.length() > bufSize) executeQuery();
//        if (buffer.length() > 0) {
//            buffer.append(", ");
//        } else {
//            buffer.append("INSERT INTO voter_count (name, birthDate, `count`) VALUES ");
//        }
//        buffer.append("('" + name + "', '" + birthDay + "', 1)");

    }
    public static void executeQuery() throws SQLException {
        long startInsert = System.currentTimeMillis();

//          1)batch approach================================================================
        System.out.println("executing " + butchCounter + " inserts...");
        preparedStatement.executeBatch();
        preparedStatement.clearBatch();
        butchCounter = 0;

//          2)buffering approach============================================================
//        buffer.append(" ON DUPLICATE KEY UPDATE `count`=`count`+1");
//        getConnection().createStatement().execute(buffer.toString());
//        buffer = new StringBuilder();

        long insertTime = System.currentTimeMillis()-startInsert;
        totalInsertTime += insertTime;
    }

    public static void printVoterCounts() throws SQLException {
        long startSearch = System.currentTimeMillis();
        String sql = "SELECT name, birthDate, `count` FROM voter_count WHERE `count` > 1";
        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
        while (rs.next()) {
            System.out.println("\t" + rs.getString("name") + " (" +
                    rs.getString("birthDate") + ") - " + rs.getInt("count"));
        }
        System.out.println("Time to insert: " + totalInsertTime);
        System.out.println("Time to search: " + (System.currentTimeMillis()-startSearch));
    }
}
