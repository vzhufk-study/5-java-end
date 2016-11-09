
import Entities.*;
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
        Hospital Texas = new Hospital("Texas Central Entities.Hospital", InputOutput.RawString.importPatientFromFile("patients.txt"), InputOutput.RawString.importStaffFromFile("staff.txt"));
        Texas.removeStaff(Texas.getStaff(0));

        System.out.println(Texas.getStaff(0).toString());
        System.out.println(Texas.getPatient(0).toString());

        InputOutput.RawString.exportStaffToFile("gg.txt", Texas);
        InputOutput.RawString.exportPatientsToFile("wp.txt", Texas);
        JSonIO json = new JSonIO();
        json.toFile(Texas, "json_kid");
        JAXBIO jax = new JAXBIO();
        jax.toFile(Texas, "jax_kid");
        Hospital Chernivtci = jax.fromFile("jax_kid");
        Hospital sdfTexas = json.fromFile("json_kid");
        Texas.getStaffByName("Con Roman");
    }
}
