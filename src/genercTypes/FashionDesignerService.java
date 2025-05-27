package genercTypes;

import model.*;

import java.sql.*;

public class FashionDesignerService implements GenericDatabaseService<FashionDesigner>{

    Connection connection = DriverManager.getConnection(
            "jdbc:mysql://127.0.0.1:3306/javaproject",
            "JavaProject User",
            "java"
    );

    public FashionDesignerService() throws SQLException {
    }

    @Override
    public void create(FashionDesigner fashionDesigner) throws SQLException {
        PreparedStatement stmtFashionDesigner = connection.prepareStatement("INSERT INTO fashiondesigner(name, email, experience, salary, affiliates) VALUES (?, ?, ?, ?, ?)");

        stmtFashionDesigner.setString(1, fashionDesigner.getName()); //name
        stmtFashionDesigner.setString(2, fashionDesigner.getEmail()); //email
        stmtFashionDesigner.setInt(3, fashionDesigner.getExperince()); //experience
        stmtFashionDesigner.setInt(4, fashionDesigner.getSalary()); //salary
        StringBuilder sb = new StringBuilder(fashionDesigner.getAffiliates().getFirst().getName());
        for (FashionHouse affiliate : fashionDesigner.getAffiliates()) {
            if ( !affiliate.getName().equalsIgnoreCase(fashionDesigner.getAffiliates().getFirst().getName()) )
                sb.append(", ").append(affiliate.getName());
        }
        stmtFashionDesigner.setString(5, sb.toString()); //affiliates
        stmtFashionDesigner.executeUpdate();


    }

    @Override
    public void read() throws SQLException {
        String selectQuery = "SELECT * FROM fashiondesigner";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(selectQuery);
        System.out.println("The Available Data\n");

        while (resultSet.next()) {
            int id = resultSet.getInt("idfashionDesigner");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            int experience = resultSet.getInt("experience");
            int salary = resultSet.getInt("salary");
            String affiliates = resultSet.getString("affiliates");

            // print the retrieved data
            System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email
                    + ", Experience: " + experience + " years, Salary: " + salary + ", Affiliates: " + affiliates);
        }
    }

    @Override
    public String getById(int id) throws SQLException {
        String selectQuery = "SELECT * FROM fashiondesigner WHERE idfashionDesigner = " + id;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(selectQuery);

        if (resultSet.next()) {
            int idFD = resultSet.getInt("idfashionDesigner");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            int experience = resultSet.getInt("experience");
            int salary = resultSet.getInt("salary");
            String affiliates = resultSet.getString("affiliates");

            // print the retrieved data
            System.out.println("ID: " + idFD + ", Name: " + name + ", Email: " + email
                    + ", Experience: " + experience + " years, Salary: " + salary + ", Affiliates: " + affiliates);
        }
        return null;
    }

    @Override
    public void update(FashionDesigner obj) throws SQLException {
        String selectQuery = "UPDATE fashiondesigner SET salary =" + obj.getSalary() + " WHERE UPPER(name) = UPPER('" + obj.getName() + "')";
        PreparedStatement statement = connection.prepareStatement(selectQuery);
        statement.execute();

    }

    @Override
    public void delete(FashionDesigner obj) throws SQLException {
        String query = "DELETE FROM fashiondesigner WHERE UPPER(name) = UPPER('" + obj.getName() + "')";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.execute();
    }
}


