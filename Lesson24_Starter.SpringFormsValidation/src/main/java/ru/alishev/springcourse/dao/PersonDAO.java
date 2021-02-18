package ru.alishev.springcourse.dao;

import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Neil Alishev
 */
@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;

    //ЭТИ ДАННЫЕ ЛУЧШЕ УКАЗЫВАТЬ В properties
    private static final String URL = "jdbc:postgresql://localhost:5432/first_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "12345";

    //Create DB connection
    private static Connection connection;

    static {
        //no for last version of Java - it is automated
        try {
            Class.forName( "org.postgresql.Driver" );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection( URL, USERNAME, PASSWORD );
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
    }


    public List<Person> index() {
        List<Person> people = new ArrayList<>();

        //Object with SQL query to DB
        try {
            Statement statement = connection.createStatement();
            String SQLquery = "SELECT * FROM Person";
            ResultSet resultSet = statement.executeQuery( SQLquery );

            while (resultSet.next()) {
                Person person = new Person();

                person.setId(resultSet.getInt( "id" ));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt( "age" ));
                person.setEmail(resultSet.getString("email"));

                people.add( person );
            }
        } catch ( SQLException throwables ) {
            throwables.printStackTrace();
        }

        return people;
    }

    public Person show(int id) {
        //return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
        return null;
    }

    public void save(Person person) {
        try {
            Statement statement = connection.createStatement();
            String SQLquery = "SELECT * FROM Person";
            ResultSet resultSet = statement.executeUpdate( SQLquery );
            
        } catch ( SQLException throwables ) {
            throwables.printStackTrace();
        }
    }

    public void update(int id, Person updatedPerson) {
//        Person personToBeUpdated = show(id);
//
//        personToBeUpdated.setName(updatedPerson.getName());
//        personToBeUpdated.setAge(updatedPerson.getAge());
//        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    public void delete(int id) {
        //people.removeIf(p -> p.getId() == id);

    }
}
