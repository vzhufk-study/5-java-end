import Classes.Hospital;

import java.io.IOException;
import java.text.ParseException;

/** (╯°□°）╯︵ ┻━┻
 * Created by Zhufyak V.V.░░░░░░░░░░░░░▄▄▄▄▄▄▄░░░░░░░░░
 * zhufyakvv@gmail.com    ░░░░░░░░░▄▀▀▀░░░░░░░▀▄░░░░░░░
 * github.com/zhufyakvv   ░░░░░░░▄▀░░░░░░░░░░░░▀▄░░░░░░
 * 21.09.2016             ░░░░░░░▄▀░░░░░░░░░░░░▀▄░░░░░░
                          ░░░░░░▄▀░░░░░░░░░░▄▀▀▄▀▄░░░░░
                          ░░░░░░▄▀░░░░░░░░░░▄▀▀▄▀▄░░░░░
                          ░░░░▄▀░░░░░░░░░░▄▀░░██▄▀▄░░░░
                          ░░░▄▀░░▄▀▀▀▄░░░░█░░░▀▀░█▀▄░░░
                          ░░░█░░█▄▄░░░█░░░▀▄░░░░░▐░█░░░
                          ░░▐▌░░█▀▀░░▄▀░░░░░▀▄▄▄▄▀░░█░░
                          ░░▐▌░░█░░░▄▀░░░░░░░░░░░░░░█░░
                          ░░▐▌░░░▀▀▀░░░░░░░░░░░░░░░░▐▌░
                          ░░▐▌░░░░░░░░░░░░░░░▄░░░░░░▐▌░
                          ░░▐▌░░░░░░░░░▄░░░░░█░░░░░░▐▌░
                          ░░░█░░░░░░░░░▀█▄░░▄█░░░░░░▐▌░
                          ░░░▐▌░░░░░░░░░░▀▀▀▀░░░░░░░▐▌░
                          ░░░░█░░░░░░░░░░░░░░░░░░░░░█░░
                          ░░░░▐▌▀▄░░░░░░░░░░░░░░░░░▐▌░░
                          ░░░░░█░░▀░░░░░░░░░░░░░░░░▀░░░
 **/

//TODO ADD exceptions
public class Test {
    public static void main(String[] args) throws ParseException, IOException {
        Hospital Texas = new Hospital("Texas Central Classes.Hospital");
        Texas.importStaffFromFile("staff.txt");
        Texas.importPatientFromFile("patients.txt");
        Texas.removeStaff(Texas.getStaff(0));

        System.out.println(Texas.getStaff(0).toString());
        System.out.println(Texas.getPatient(0).toString());

        Texas.exportStaffToFile("gg.txt");
        Texas.exportPatientsToFile("wp.txt");

        //TODO add some information. Try to work with it.
    }
}
