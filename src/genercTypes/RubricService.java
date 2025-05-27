package genercTypes;

import model.Rubric;

import java.sql.*;

public class RubricService implements GenericDatabaseService<Rubric>{

    Connection connection = DriverManager.getConnection(
            "jdbc:mysql://127.0.0.1:3306/javaproject",
            "JavaProject User",
            "java"
    );

    public RubricService() throws SQLException {
    }

    @Override
    public void create(Rubric rubric) throws SQLException {
        PreparedStatement stmtRubric = connection.prepareStatement("INSERT INTO rubric(title, article, magazineId, fashionDesignerId, fashionHouseId, targetAudience) VALUES (?, ?, ?, ?, ?, ?)");

        stmtRubric.setString(1, rubric.getTitle()); //title
        stmtRubric.setString(2, rubric.getArticle()); //article

        String query = "SELECT idfashionmagazine FROM fashionmagazine WHERE UPPER(name) = UPPER('" + rubric.getPublisher().getName() + "')";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        Integer magazineId = 0;
        if (resultSet.next())
            magazineId = resultSet.getInt("idfashionmagazine");
        stmtRubric.setInt(3, magazineId); //magazineId

        String query1 = "SELECT idfashionhouse FROM fashionhouse WHERE UPPER(name) = UPPER('" + rubric.getReference().getName() + "')";
        Statement statement1 = connection.createStatement();
        ResultSet resultSet1 = statement1.executeQuery(query1);
        Integer fashionHouseId = 0;
        if (resultSet1.next())
            fashionHouseId = resultSet1.getInt("idfashionhouse");
        stmtRubric.setInt(5, fashionHouseId); //fashionHouseId

        String query2 = "SELECT idfashiondesigner FROM fashiondesigner WHERE UPPER(name) = UPPER('" + rubric.getFashionDesigner().getName() + "')";
        Statement statement2 = connection.createStatement();
        ResultSet resultSet2 = statement2.executeQuery(query2);
        Integer fashionDesignerId = 0;
        if (resultSet2.next())
            fashionDesignerId = resultSet2.getInt("idfashiondesigner");
        stmtRubric.setInt(4, fashionDesignerId); //fashionDesignerId

        stmtRubric.setString(6, rubric.getTargetAudience()); //targetAudience

        stmtRubric.executeUpdate();


    }

    @Override
    public void read() throws SQLException {
        String selectQuery = "SELECT * FROM rubric";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(selectQuery);
        System.out.println("The Available Data\n");

        while (resultSet.next()) {
            int id = resultSet.getInt("idrubric");
            String title = resultSet.getString("title");
            String article = resultSet.getString("article");
            int magazineId = resultSet.getInt("magazineId");
            int fashionHouseId = resultSet.getInt("fashionHouseId");
            int fashionDesignerId = resultSet.getInt("fashionDesignerId");

            // print the retrieved data
            System.out.println("ID: " + id + ", Name: " + title + ", Article: " + article
                    + ", magazineId: " + magazineId + ", fashionHouseId: " + fashionHouseId + ", fashionDesignerId: " + fashionDesignerId );
        }
    }

    @Override
    public String getById(int id) throws SQLException {
        String selectQuery = "SELECT * FROM rubric WHERE idrubric = " + id;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(selectQuery);

        if (resultSet.next()) {
            int idrubric = resultSet.getInt("idrubric");
            String title = resultSet.getString("title");
            String article = resultSet.getString("article");
            int magazineId = resultSet.getInt("magazineId");
            int fashionHouseId = resultSet.getInt("fashionHouseId");
            int fashionDesignerId = resultSet.getInt("fashionDesignerId");

            // print the retrieved data
            System.out.println("ID: " + idrubric + ", Name: " + title + ", Article: " + article
                    + ", magazineId: " + magazineId + ", fashionHouseId: " + fashionHouseId + ", fashionDesignerId: " + fashionDesignerId );
        }
        return null;
    }

    @Override
    public void update(Rubric rubric) throws SQLException {
        String selectQuery = "UPDATE rubric SET article =" + rubric.getArticle() + " WHERE UPPER(title) = UPPER('" + rubric.getTitle() + "')";
        PreparedStatement statement = connection.prepareStatement(selectQuery);
        statement.execute();

    }

    @Override
    public void delete(Rubric rubric) throws SQLException {
        String query = "DELETE FROM rubric WHERE UPPER(title) = UPPER('" + rubric.getTitle() + "')";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.execute();
    }
}

