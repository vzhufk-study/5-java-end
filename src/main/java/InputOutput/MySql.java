package InputOutput;

import Entities.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;

import static Parsers.DataParser.parseGregorianToMySqlDateString;

public class MySql {
    public PropertyManager propertyManager;
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public MySql(){
        propertyManager = new PropertyManager();
    }
    public MySql(PropertyManager propertyManager){
        this.propertyManager = propertyManager;
    }
    public void connect(){
        try {
            connection = DriverManager.getConnection(propertyManager.getUrl(), propertyManager.getUser(), propertyManager.getPassword());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void query(String queryText){
        try {
            this.statement = connection.createStatement();
            this.resultSet = this.statement.executeQuery(queryText);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public LinkedList<Staff> getStaff(){
        this.query("select * from staff");
        LinkedList result = new LinkedList<Staff>();
        try {
            while (resultSet.next()){
                Staff current = new Staff(resultSet.getString("name"), resultSet.getDate("date_of_birth"), Gender.valueOf(resultSet.getString("sex")), resultSet.getString("address"), resultSet.getFloat("salary"), resultSet.getDate("date_of_hiring"), Post.valueOf(resultSet.getString("post")));
                result.add(current);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public LinkedList<Patient> getPatients(){
        this.query("select * from patient");
        ArrayList result = new ArrayList<Patient>();
        try {
            while (resultSet.next()){
                int i = resultSet.getInt("id");
                Patient current = new Patient(resultSet.getString("name"), resultSet.getDate("date_of_birth"), Gender.valueOf(resultSet.getString("sex")), resultSet.getString("address"), new LinkedList<Diagnosis>());
                result.add(i, current);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //sort by id?
        this.query("select * from diagnosis");
        try{
            while(resultSet.next()){
                int i = resultSet.getInt("patient");
                Diagnosis diagnosis = new Diagnosis(resultSet.getString("disease"), resultSet.getDate("date_of_diagnosis"), resultSet.getString("cure"), resultSet.getString("responsible"));
                Patient current = (Patient) result.get(i);
                current.addDiagnosis(diagnosis);
                result.set(i, current);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return new LinkedList<Patient>(result);
    }

    public void close(){
        try {
            this.connection.close();
            this.statement.close();
            this.resultSet.close();
        } catch(SQLException se) { se.printStackTrace(); }
    }

    public void addStaff(Staff current) throws SQLException {
        String query = "INSERT INTO staff\n" +
                " VALUES ('" + current.getName()+ "', " + parseGregorianToMySqlDateString(current.getDateOfBirth()) + ", '" + current.getSex().toString() + "', '" + current.getAddress() + "', " + current.getSalary() + ", " + parseGregorianToMySqlDateString(current.getDateOfHiring()) + ", '" + current.getPost().toString() + "');";
        this.statement.executeUpdate(query);
    }

    //TODO INSERTS
    public void addPatient(Patient current) throws SQLException {
        String query = "INSERT INTO patient\n" +
                " VALUES ('" + current.getName()+ "', " + parseGregorianToMySqlDateString(current.getDateOfBirth()) + ", '" + current.getSex().toString() + "', '" + current.getAddress() + "');";

        this.statement.executeUpdate(query);

        query = "SELECT patient.id FROM patient \n" +
                "WHERE patient.name = '" + current.getName() + "';";
        this.statement.execute(query);
        int currentId = this.resultSet.getInt("id");
        current.getDiagnosis().forEach(item-> {
                    String q = "INSERT INTO diagnosis\n" +
                            " VALUES ('" + item.getDisease() + "', " + parseGregorianToMySqlDateString(item.getDateOfDiagnosis()) + ", '" + item.getCure() + "', '" + item.getResponsible() + "', " + currentId + ");";
                    try {
                        this.statement.execute(q);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
        );

    }

}
