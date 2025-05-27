package genercTypes;

import model.Critic;

import java.sql.*;

public class CriticService implements GenericDatabaseService<Critic>{

    Connection connection = DriverManager.getConnection(
            "jdbc:mysql://127.0.0.1:3306/javaproject",
            "JavaProject User",
            "java"
    );

    public CriticService() throws SQLException {
    }

    @Override
    public void create(Critic critic) throws SQLException {
        PreparedStatement stmtCritic = connection.prepareStatement("INSERT INTO critic(name, email, experience, salary, ranking) VALUES (?, ?, ?, ?, ?)");

        stmtCritic.setString(1, critic.getName()); //name
        stmtCritic.setString(2, critic.getEmail()); //email
        stmtCritic.setInt(3, critic.getExperince()); //experience
        stmtCritic.setInt(4, critic.getSalary()); //salary
        stmtCritic.setInt(5, critic.getRanking()); //ranking
        stmtCritic.executeUpdate();

    }

    @Override
    public void read() throws SQLException {
        String selectQuery = "SELECT * FROM critic";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(selectQuery);
        System.out.println("The Available Data\n");

        while (resultSet.next()) {
            int id = resultSet.getInt("idcritic");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            int experience = resultSet.getInt("experience");
            int salary = resultSet.getInt("salary");
            int ranking = resultSet.getInt("ranking");

            // print the retrieved data
            System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email
                    + ", Experience: " + experience + " years, Salary: " + salary + ", Ranking: " + ranking);
        }
    }

    @Override
    public String getById(int id) throws SQLException {
        String selectQuery = "SELECT * FROM critic WHERE idcritic = " + id;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(selectQuery);

        if (resultSet.next()) {
            int idcritic = resultSet.getInt("idcritic");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            int experience = resultSet.getInt("experience");
            int salary = resultSet.getInt("salary");
            int ranking = resultSet.getInt("ranking");

            // print the retrieved data
            System.out.println("ID: " + idcritic + ", Name: " + name + ", Email: " + email
                    + ", Experience: " + experience + " years, Salary: " + salary + ", Ranking: " + ranking);
        }
        return null;
    }

    @Override
    public void update(Critic critic) throws SQLException {
        String selectQuery = "UPDATE critic SET salary =" + critic.getSalary() + " WHERE UPPER(name) = UPPER('" + critic.getName() + "')";
        PreparedStatement statement = connection.prepareStatement(selectQuery);
        statement.execute();

    }

    @Override
    public void delete(Critic critic) throws SQLException {
        String query = "DELETE FROM editor WHERE UPPER(name) = UPPER('" + critic.getName() + "')";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.execute();
    }
}


