package genercTypes;

import model.Magazine;

import java.sql.*;

public class MagazineService implements GenericDatabaseService<Magazine>{

    Connection connection = DriverManager.getConnection(
            "jdbc:mysql://127.0.0.1:3306/javaproject",
            "JavaProject User",
            "java"
    );

    public MagazineService() throws SQLException {
    }

    @Override
    public void create(Magazine magazine) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO fashionmagazine(name, noRubrics, countries) VALUES (?, ?, ?)");

        stmt.setString(1, magazine.getName());
        stmt.setInt(2, magazine.getNoRubrics());
        StringBuilder sb = new StringBuilder( magazine.getCountries().getFirst() );
        for (String country : magazine.getCountries()){
            if (  !country.equalsIgnoreCase( magazine.getCountries().getFirst() )  )
                sb.append(", ").append(country);
        }
        stmt.setString(3, sb.toString());

        stmt.executeUpdate();
    }

    @Override
    public void read() throws SQLException {
        String selectQuery = "SELECT * FROM fashionmagazines";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(selectQuery);
        System.out.println("The Available Data\n");

        while (resultSet.next()) {
            int id = resultSet.getInt("idfashionmagazine");
            String name = resultSet.getString("name");
            int noRubrics = resultSet.getInt("noRubrics");
            String countries = resultSet.getString("countries");

            // print the retrieved data
            System.out.println("ID: " + id + ", Name: " + name + ", No Rubrics: " + noRubrics
                    + ", Countries: " + countries);
        }
    }

    @Override
    public String getById(int id) throws SQLException {
        String selectQuery = "SELECT * FROM fashionmagazines WHERE idfashionmagazine = " + id;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(selectQuery);

        if (resultSet.next()) {
            int idfashionmagazine = resultSet.getInt("idfashionmagazine");
            String name = resultSet.getString("name");
            int noRubrics = resultSet.getInt("noRubrics");
            String countries = resultSet.getString("countries");

            System.out.println("ID: " + id + ", Name: " + name + ", No Rubrics: " + noRubrics
                    + ", Countries: " + countries);
        }
        return null;
    }

    @Override
    public void update(Magazine magazine) throws SQLException {
        String selectQuery = "UPDATE fashionmagazines SET noRubrics =" + magazine.getNoRubrics() + " WHERE UPPER(name) = UPPER('" + magazine.getName() + "')";
        PreparedStatement statement = connection.prepareStatement(selectQuery);
        statement.execute();

    }

    @Override
    public void delete(Magazine magazine) throws SQLException {
        String query = "DELETE FROM fashionmagazine WHERE UPPER(name) = UPPER('" + magazine.getName() + "')";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.execute();
    }
}
