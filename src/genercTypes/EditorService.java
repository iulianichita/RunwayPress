package genercTypes;

import model.Editor;

import java.sql.*;

public class EditorService implements GenericDatabaseService<Editor>{

    Connection connection = DriverManager.getConnection(
            "jdbc:mysql://127.0.0.1:3306/javaproject",
            "JavaProject User",
            "java"
    );

    public EditorService() throws SQLException {
    }

    @Override
    public void create(Editor editor) throws SQLException {
        String selectQuery = "SELECT MAX(ideditor) FROM editor";
        Statement s = connection.createStatement();
        ResultSet r = s.executeQuery(selectQuery);
        Integer lastId = 100;
        while (r.next())
            lastId = r.getInt("MAX(ideditor)");
        PreparedStatement stmtEditor = connection.prepareStatement("INSERT INTO editor(name, email, experience, salary, ideditor) VALUES (?, ?, ?, ?, ?)");

        stmtEditor.setString(1, editor.getName()); //name
        stmtEditor.setString(2, editor.getEmail()); //email
        stmtEditor.setInt(3, editor.getExperince()); //experience
        stmtEditor.setInt(4, editor.getSalary()); //salary
        stmtEditor.setInt(5, lastId+1); //idEditor
        stmtEditor.executeUpdate();

    }

    @Override
    public void read() throws SQLException {
        String selectQuery = "SELECT * FROM editor";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(selectQuery);
        System.out.println("The Available Data\n");

        while (resultSet.next()) {
            int id = resultSet.getInt("ideditor");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            int experience = resultSet.getInt("experience");
            int salary = resultSet.getInt("salary");

            // print the retrieved data
            System.out.println("\n[ID: " + id + "\nName: " + name + "\nEmail: " + email
                    + "\nExperience: " + experience + " years\nSalary: " + salary + "]\n" );
        }
    }

    @Override
    public String getById(int id) throws SQLException {
        String selectQuery = "SELECT * FROM editor WHERE ideditor = " + id;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(selectQuery);

        if (resultSet.next()) {
            int ideditor = resultSet.getInt("ideditor");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            int experience = resultSet.getInt("experience");
            int salary = resultSet.getInt("salary");

            // print the retrieved data
            System.out.println("ID: " + ideditor + ", Name: " + name + ", Email: " + email
                    + ", Experience: " + experience + " years, Salary: " + salary );
        }
        return null;
    }

    @Override
    public void update(Editor editor) throws SQLException {
        String selectQuery = "UPDATE editor SET salary =" + editor.getSalary() + " WHERE UPPER(name) = UPPER('" + editor.getName() + "')";
        PreparedStatement statement = connection.prepareStatement(selectQuery);
        statement.execute();

    }

    @Override
    public void delete(Editor editor) throws SQLException {
        String query = "DELETE FROM editor WHERE UPPER(name) = UPPER('" + editor.getName() + "')";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.execute();
    }
}


