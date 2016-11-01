
import Classes.*;
import Exceptions.StaffPostException;
import InputOutput.JAXBIO;
import InputOutput.JSonIO;

import java.io.IOException;
import java.text.ParseException;

/** (╯°□°）╯︵ ┻━┻
 * Created by Zhufyak V.V.
 * zhufyakvv@gmail.com
 * github.com/zhufyakvv
 **/

//TODO ADD exceptions
public class Test {
    public static void main(String[] args) throws ParseException, IOException, StaffPostException {
        Hospital Texas = new Hospital("Texas Central Classes.Hospital");
        Texas.importStaffFromFile("staff.txt");
        Texas.importPatientFromFile("patients.txt");
        Texas.removeStaff(Texas.getStaff(0));

        System.out.println(Texas.getStaff(0).toString());
        //TODO WTF?
        //System.out.println(Texas.getPatient(0).toString());

        Texas.exportStaffToFile("gg.txt");
        Texas.exportPatientsToFile("wp.txt");
        JSonIO json = new JSonIO();
        json.toFile(Texas, "json_kid");
        JAXBIO jax = new JAXBIO();
        jax.toFile(Texas, "jax_kid");
    }
}
