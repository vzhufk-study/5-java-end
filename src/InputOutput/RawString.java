package InputOutput;

import Entities.Hospital;
import Entities.Patient;
import Entities.Staff;

import java.io.*;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * (╯°□°）╯︵ ┻━┻
 * Created by Zhufyak V.V.
 * zhufyakvv@gmail.com
 * github.com/zhufyakvv
 * 02.11.2016
 **/
public class RawString {
    // Format:
    // Entities.Staff: staff\n
    // Patients: patient: diagnosis, diagnosis, ... , diagnosis\n
    public static LinkedList<Staff> importStaffFromFile(String fileName){
        Hospital current = new Hospital();
        File file = new File(fileName);
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            String text;
            while ((text = reader.readLine()) != null) {
                current.addStaff(text);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (LinkedList<Staff>) current.getStaff();
    }
    public static LinkedList<Patient> importPatientFromFile(String fileName){
        Hospital current = new Hospital();
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String text;
            while ((text = reader.readLine()) != null) {
                current.addPatient(text);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }
        }
        return (LinkedList<Patient>) current.getPatients();
    }
    public static void exportStaffToFile(String fileName, Hospital h) throws IOException {
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "utf-8"));
        for (ListIterator<Staff> i = h.getStaff().listIterator(); i.hasNext();) {
            writer.write(i.next().toString());
            writer.write(System.getProperty("line.separator"));
        }
        writer.close();
    }
    public static void exportPatientsToFile(String fileName, Hospital h) throws IOException {
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "utf-8"));
        for (ListIterator<Patient> i = h.getPatients().listIterator(); i.hasNext();) {
            writer.write(i.next().toString());
            writer.write(System.getProperty("line.separator"));
        }
        writer.close();
    }

}
