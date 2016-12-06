import Entities.Hospital;
import Exceptions.StaffPostException;
import InputOutput.MySql;
import InputOutput.PropertyManager;
import InputOutput.Swing;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

/** (╯°□°）╯︵ ┻━┻
 * Created by Zhufyak V.V.
 * zhufyakvv@gmail.com
 * github.com/zhufyakvv
 **/

//TODO ADD exceptions
public class Test {
    public static void main(String[] args) throws ParseException, IOException, StaffPostException, SQLException {
        Hospital Texas = new Hospital("Texas Central Entities.Hospital", InputOutput.RawString.importPatientFromFile("patients.txt"), InputOutput.RawString.importStaffFromFile("staff.txt"));

        //MySQL
        InputOutput.PropertyManager propertyManager;
        propertyManager = new PropertyManager();
        MySql mySql = new MySql(propertyManager);
        mySql.connect();
        mySql.getStaff();
        mySql.addStaff(Texas.getStaff(1));
        mySql.getPatients();
        mySql.close();

        Swing swing = new Swing();
        swing.init(Texas);
    }
}
