package genercTypes;

import model.FashionHouse;

import java.sql.*;

public class FashionHouseService implements GenericDatabaseService<FashionHouse>{

    Connection connection = DriverManager.getConnection(
            "jdbc:mysql://127.0.0.1:3306/javaproject",
            "JavaProject User",
            "java"
    );

    public FashionHouseService() throws SQLException {
    }

    @Override
    public void create(FashionHouse fashionHouse) throws SQLException {
        PreparedStatement stmtFashionHouse = connection.prepareStatement("INSERT INTO fashionhouse(name, debut, worth) VALUES (?, ?, ?)");

        stmtFashionHouse.setString(1, fashionHouse.getName()); //name
        stmtFashionHouse.setInt(2, fashionHouse.getDebut()); //debut
        stmtFashionHouse.setString(3, fashionHouse.getWorth()); //worth
        stmtFashionHouse.executeUpdate();

    }

    @Override
    public void read() throws SQLException {
        String selectQuery = "SELECT * FROM fashionhouse";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(selectQuery);
        System.out.println("The Available Data\n");

        while (resultSet.next()) {
            int id = resultSet.getInt("idfashionhouse");
            String name = resultSet.getString("name");
            int debut = resultSet.getInt("debut");
            String worth = resultSet.getString("worth");

            // print the retrieved data
            System.out.println("ID: " + id + ", Name: " + name + ", Year of debut: " + debut
                    + ", Worth: " + worth);
        }
    }

    @Override
    public String getById(int id) throws SQLException {
        String selectQuery = "SELECT * FROM fashionhouse WHERE idfashionhouse = " + id;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(selectQuery);

        if (resultSet.next()) {
            int idfashionhouse = resultSet.getInt("idfashionhouse");
            String name = resultSet.getString("name");
            int debut = resultSet.getInt("debut");
            String worth = resultSet.getString("worth");

            System.out.println("ID: " + idfashionhouse + ", Name: " + name + ", Year of debut: " + debut
                    + ", Worth: " + worth);
        }
        return null;
    }

    @Override
    public void update(FashionHouse fashionHouse) throws SQLException {
        String selectQuery = "UPDATE fashionhouse SET worth =" + fashionHouse.getWorth() + " WHERE UPPER(name) = UPPER('" + fashionHouse.getName() + "')";
        PreparedStatement statement = connection.prepareStatement(selectQuery);
        statement.execute();

    }

    @Override
    public void delete(FashionHouse fashionHouse) throws SQLException {
        String query = "DELETE FROM fashionhouse WHERE UPPER(name) = UPPER('" + fashionHouse.getName() + "')";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.execute();
    }
}

