package Classes;

import Exceptions.StaffPostException;

import java.io.*;
import java.text.Collator;
import java.text.ParseException;
import java.util.*;

/**
 * Created by zhufy on 14.09.2016.
 */

public class Hospital {
    private String name;
    private List<Patient> patients;
    private List<Staff> staff;

    public Hospital(String name) {
        this.name = name;
        this.patients = new LinkedList<>();
        this.staff = new LinkedList<>();
    }

    public Hospital(Hospital hospital) {
        this.name = hospital.name;
        this.patients = new LinkedList<>(hospital.getPatients());
        this.staff = new LinkedList<>(hospital.getStaff());
    }

    public void addPatient(Patient patient) {
        this.patients.add(patient);
    }
    public void addPatient(String text) throws ParseException {

        Patient current = new Patient(text.split(":")[0]);
        if (text.split(":")[0].length() + 1 != text.length()) {
            for (int i = 1; i < text.split(":").length; ++i) {
                current.addDiagnosis(new Diagnosis(text.split(":")[i]));
            }
        }
        addPatient(current);
    }

    public void addStaff(Staff employer) {
        this.staff.add(employer);
    }
    public void addStaff(String employer) throws ParseException {
        this.staff.add(new Staff(employer.split(": ")[0], employer.split(": ")[1]));
    }
    // Format:
    // Classes.Staff: staff\n
    // Patients: patient: diagnosis, diagnosis, ... , diagnosis\n
    public void importStaffFromFile(String fileName){
        File file = new File(fileName);
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            String text;
            while ((text = reader.readLine()) != null) {
                addStaff(text);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public void importPatientFromFile(String fileName){
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String text;
            while ((text = reader.readLine()) != null) {
                addPatient(text);
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
    }
    public void exportStaffToFile(String fileName) throws IOException {
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "utf-8"));
        for (ListIterator<Staff> i = getStaff().listIterator(); i.hasNext();) {
            writer.write(i.next().toString());
            writer.write(System.getProperty("line.separator"));
        }
        writer.close();
    }
    public void exportPatientsToFile(String fileName) throws IOException {
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "utf-8"));
        for (ListIterator<Patient> i = getPatients().listIterator(); i.hasNext();) {
            writer.write(i.next().toString());
            writer.write(System.getProperty("line.separator"));
        }
        writer.close();
    }

    //Get all patients in alphabetical order
    public Patient getPatient(int index){
        ListIterator<Patient> current= patients.listIterator();
        for (int i = 0; i < index && current.hasNext(); ++i, current.next());
        return current.next();
    }
    public List<Patient> getPatients() {
        List<Patient> result = patients;
        //Lines to sort in alphabetical
        result.sort((o1, o2) -> Collator.getInstance().compare(o1.getName(), o2.getName()));
        return result;
    }
    //Get patients by name and code of Classes.Diagnosis
    public List<Patient> getPatientsByDiagnosis(Diagnosis Diagnosis, boolean nameComp, boolean cureComp) {
        List<Patient> result = new LinkedList<Patient>();
        for (Iterator<Patient> i = patients.iterator(); i.hasNext(); ) {
            Patient currenntPatient = i.next();
            List<Diagnosis> currenntPatientDiagnosis = currenntPatient.getDiagnosis();
            for (Iterator<Diagnosis> j = currenntPatientDiagnosis.iterator(); j.hasNext(); ) {
                Diagnosis currentPatientCurrentDiagnosis = j.next();
                if ((nameComp && currentPatientCurrentDiagnosis.getDisease() == Diagnosis.getDisease()) || (cureComp && currentPatientCurrentDiagnosis.getCure() == Diagnosis.getCure()) || (nameComp && cureComp && currentPatientCurrentDiagnosis.getDisease() == Diagnosis.getDisease() && currentPatientCurrentDiagnosis.getCure() == Diagnosis.getCure())) {
                    result.add(currenntPatient);
                }
            }
        }
        return result;
    }
    //Get patients by diagnosis name
    public List<Patient> getPatientsByDiagnosisName(Diagnosis Diagnosis){
        return getPatientsByDiagnosis(Diagnosis, true, false);
    }
    public List<Patient> getPatientsByDiagnosisName(String name) throws ParseException {
        return getPatientsByDiagnosis(new Diagnosis(name, new GregorianCalendar(), "", ""), true, false);
    }
    //Get patients by cure
    public List<Patient> getPatientsByDiagnosisCure(Diagnosis Diagnosis){
        return getPatientsByDiagnosis(Diagnosis, false, true);
    }
    public List<Patient> getPatientsByDiagnosisCure(String cure){
        return getPatientsByDiagnosis(new Diagnosis("", new GregorianCalendar(), cure, ""), false, true);
    }

    public List<Patient> getPatientsByName(String name){
        LinkedList<Patient> result = new LinkedList<>();
        for (ListIterator<Patient> i = getPatients().listIterator(); i.hasNext();){
            Patient current = i.next();
            if (current.getName().equals(name)){
                result.add(current);
            }
        }
        return result;
    }
    public List<Patient> getPatientsBySex(Gender sex){
        LinkedList<Patient> result = new LinkedList<>();
        for (ListIterator<Patient> i = getPatients().listIterator(); i.hasNext();){
            Patient current = i.next();
            if (current.getSex().equals(sex)){
                result.add(current);
            }
        }
        return result;
    }
    public List<Patient> getPatientsByDiagnosis(Diagnosis diagnosis){
        LinkedList<Patient> result = new LinkedList<>();
        for (ListIterator<Patient> i = getPatients().listIterator(); i.hasNext();){
            Patient current = i.next();
            for (ListIterator<Diagnosis> j = current.getDiagnosis().listIterator(); j.hasNext();){
                if (j.next().equals(diagnosis)){
                    result.add(current);
                    break;
                }
            }
        }
        return result;
    }

    public List<Staff> getStaff() {
        return staff;
    }
    public Staff getStaff(int index){
        ListIterator<Staff> current = getStaff().listIterator();
        for (int i = 0; i < index & current.hasNext(); ++i, current.next());
        return current.next();
    }

    public List<Staff> getStaffBySalary(double startsWith, double endsWith){
        LinkedList<Staff> result = new LinkedList<>();
        for (ListIterator<Staff> i = getStaff().listIterator(); i.hasNext();){
            Staff current = i.next();
            if (current.getSalary() >= startsWith && current.getSalary()<= endsWith){
                result.add(current);
            }
        }
        return result;
    }

    public List<Staff> getStaffByName(String name){
        LinkedList<Staff> result = new LinkedList<>();
        for ( ListIterator<Staff> i = getStaff().listIterator(); i.hasNext();){
            Staff current = i.next();
            if (current.getName().equals(name));
        }
        return result;
    }
    public List<Staff> getStaffByPost(Post post){
        LinkedList<Staff> result = new LinkedList<>();
        for ( ListIterator<Staff> i = getStaff().listIterator(); i.hasNext();){
            Staff current = i.next();
            if (current.getPost().equals(post));
        }
        return result;
    }
    //Remove some staff
    public void removeStaff(Staff fired) throws StaffPostException {
        //would it work???
        if (fired.getPost() == Post.Administrator) throw new StaffPostException();
        else{
            staff.remove(fired);
        }
    }
}

