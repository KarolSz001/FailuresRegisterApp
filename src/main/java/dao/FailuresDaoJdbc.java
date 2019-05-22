package dao;

import enums.Area;
import enums.Priority;
import model.Failure;

import java.sql.*;
import java.util.Collection;
import java.util.Optional;

public class FailuresDaoJdbc implements FailureDao {

    private Connection connection;
    private final String databaseName = "failures_db";
    private final String tableName  = "register_failure";
    private final String user = "root";
    private final String password = "admin";
    private final String[] col = {"id", "description" ,"priority","area", "owner"};


    public FailuresDaoJdbc() {
        init();
        createTables();
    }
//jdbc:mysql://localhost/db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
    private void init(){
        try {
//            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/"+ databaseName+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createTables(){
//Long id, String description, Priority priority, Area area, String owner
        Statement statement = null;
        try {
             statement = connection.createStatement();

            String query = "create table if not exists failures_tab ( " +
                     col[0]+ " integer primary key auto_increment, " +
                    col[1] + " varchar(255) not null, " +
                    col[2] + " varchar(50) not null, " +
                    col[3] + " varchar(50) not null, " +
                    col[4] + " varchar(50) not null " +
                    ");";

            statement.execute(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public Failure save(Failure failure) {

        PreparedStatement statement;
        String description  = failure.getDescription();
        String priority = String.valueOf(failure.getPriority());
        String area = String.valueOf(failure.getArea());
        String owner = failure.getOwner();
        try {
            String query = "INSERT INTO " + tableName + " (description, priority, area, owner) values (?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1,description);
            statement.setString(2,priority);
            statement.setString(3,area);
            statement.setString(4,owner);
            statement.execute(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public Collection<Failure> getAllFailures() {
        return null;
    }

    @Override
    public Optional<Failure> getFailureById(Long id) {
        return Optional.empty();
    }

    @Override
    public Collection<Failure> getFailureByAres(Area are) {
        return null;
    }

    @Override
    public Collection<Failure> getFailureByPriority(Priority priority) {
        return null;
    }

    @Override
    public void deleteFailureById(Long id) {

    }

    @Override
    public void deleteFailureByOwner(String owner) {

    }

    @Override
    public Failure update(Failure failure) {
        return null;
    }
}
