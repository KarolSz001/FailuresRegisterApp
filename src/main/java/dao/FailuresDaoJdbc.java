package dao;

import enums.Area;
import enums.Priority;
import model.Failure;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FailuresDaoJdbc implements FailureDao {

    private Connection connection;
    private final String databaseName = "failures_db";
    private final String tableName  = "failures_tab";
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

            String query = "create table if not exists "+ tableName + "( " +
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
            String query = "INSERT INTO " + tableName + " (description, priority, area, owner ) values(?, ?, ?, ?)";

            statement = connection.prepareStatement(query);

            statement.setString(1,description);
            statement.setString(2,priority);
            statement.setString(3,area);
            statement.setString(4,owner);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return failure;
    }

    @Override
    public Collection<Failure> getAllFailures() {
        List<Failure> failures = new ArrayList<>();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            String query = "SELECT (id, description, priority, area, owner) FROM" + tableName;
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                Long id = resultSet.getLong("id");
                String description = resultSet.getString("description");
                Priority priority = Priority.valueOf(resultSet.getString("priority"));
                Area area = Area.valueOf(resultSet.getString("area"));
                String owner = resultSet.getString("owner");

                Failure failure = new Failure(id,description,priority,area,owner);
                failures.add(failure);
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return failures;
    }

    @Override
    public Optional<Failure> getFailureById(Long id) {
        return getAllFailures().stream().filter(failure -> failure.getId().equals(id)).findFirst();
    }

    @Override
    public Collection<Failure> getFailureByAres(Area area) {
        return getAllFailures().stream().filter(failure -> failure.getArea().equals(area)).collect(Collectors.toList());
    }

    @Override
    public Collection<Failure> getFailureByPriority(Priority priority) {
        return getAllFailures().stream().filter(failure -> failure.getArea().equals(priority)).collect(Collectors.toList());
    }

    @Override
    public void deleteFailureById(Long id) {
        List<Failure> failures =(List<Failure>) getAllFailures();
        failures.removeIf(failure -> failure.getId().equals(id));
    }

    @Override
    public void deleteFailureByOwner(String owner) {
        List<Failure> failures =(List<Failure>) getAllFailures();
        failures.removeIf(failure -> failure.getOwner().equals(owner));
    }

    @Override
    public Failure update(Failure failure) {
        deleteFailureById(failure.getId());
        List<Failure> failures = (List<Failure>) getAllFailures();
        failures.add(failure);
        return failure;
    }
}
