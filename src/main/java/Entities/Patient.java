package Entities;

import javax.xml.bind.annotation.XmlElement;
import java.text.ParseException;
import java.util.*;

/**
 * Created by zhufy on 14.09.2016.
 */

public class Patient extends Person {

    private List<Diagnosis> diagnosis;

    public Patient() throws ParseException {
        super();
        diagnosis = new LinkedList<>();
    }

    public Patient(String name, Date dateOfBirth, Gender sex, String address, List<Diagnosis> diagnosis) {
        super(name, dateOfBirth, sex, address);
        this.diagnosis = diagnosis;
    }

    public Patient(String text) throws ParseException {
        super(text);
        diagnosis = new LinkedList<>();
    }

    public void addDiagnosis(Diagnosis diagnosis){
        this.diagnosis.add(diagnosis);
    }

    //Get Entities.Diagnosis in some date range
    public List<Diagnosis> getDiagnosis(GregorianCalendar startDate, GregorianCalendar finishDate){
        List<Diagnosis> result = new LinkedList<Diagnosis>();
        GregorianCalendar currentDiagnosisDate;
        for (Iterator<Diagnosis> i = diagnosis.listIterator(); i.hasNext(); ){
            Diagnosis currentDiagnos = i.next();
            currentDiagnosisDate = currentDiagnos.getDateOfDiagnosis();
            if (startDate.after(currentDiagnosisDate) || finishDate.after(currentDiagnosisDate)){
                result.add(currentDiagnos);
            }
        }
        return result;
    }

    //Get Entities.Diagnosis after some date
    public List<Diagnosis> getDiagnosisAfterBefore(GregorianCalendar startDate){
        return getDiagnosis(startDate, new GregorianCalendar());
    }
    @XmlElement
    public List<Diagnosis> getDiagnosis() {
        return diagnosis;
    }

    //Get Entities.Diagnosis before some date
    public List<Diagnosis> getDiagnosisAfter(GregorianCalendar finishDate){
        return getDiagnosis(new GregorianCalendar(), finishDate);
    }


    @Override
    public int hashCode() {
        int result = diagnosis.hashCode();
        return result;
    }

    @Override
    public String toString() {
        String result =  super.toString() + ":";
        for (ListIterator<Diagnosis> i = diagnosis.listIterator(); i.hasNext();){
            result += i.next().toString();
        }
        return result;
    }
}
