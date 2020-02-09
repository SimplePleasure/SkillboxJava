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


    private static PreparedStatement preparedStatement;
    private static int butchSize = 20000;
    private static int butchCounter = 0;



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
//                        "`count` TINYINT NOT NULL, " +
//                        "PRIMARY KEY(id))");
                if (preparedStatement == null) {
                    preparedStatement = connection.prepareStatement("INSERT INTO voter_count " +
                            "(name, birthDate) VALUES (?,?)");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void countVoter(String name, String birthDay) throws SQLException{


        birthDay = birthDay.replace('.', '-');
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, birthDay);
        preparedStatement.addBatch();
        if (butchCounter++ == butchSize) executeQuery();
    }

    public static void executeQuery() {
        long startInsert = System.currentTimeMillis();

        try {
            getConnection().setAutoCommit(false);
            preparedStatement.executeBatch();
            preparedStatement.clearBatch();
            butchCounter = 0;
            getConnection().commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
                e.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        long insertTime = System.currentTimeMillis()-startInsert;
        totalInsertTime += insertTime;
    }

    public static void printVoterCounts()  {

        long startSearch = System.currentTimeMillis();
        try {
            getConnection().prepareStatement("CREATE INDEX name_birthDay USING BTREE ON learn.voter_count (name (50),birthDate);").execute();
            String sql = "SELECT name, birthDate, COUNT(*) 'count' FROM learn.voter_count vc GROUP BY name, birthDate HAVING count > ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 1);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                System.out.println("\t" + rs.getString("name") + " (" +
                        rs.getString("birthDate") + ") - " + rs.getInt("count"));
            }
            getConnection().prepareStatement("ALTER TABLE learn.voter_count DROP INDEX name_birthDay").execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Time to insert: " + totalInsertTime);
        System.out.println("Time to search: " + (System.currentTimeMillis()-startSearch));
    }
}
