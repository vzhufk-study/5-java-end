package Classes;

import java.text.ParseException;
import java.util.GregorianCalendar;

/**
 * Created by zhufy on 15.09.2016.
 */
class Diagnosis {
    private String disease;
    private GregorianCalendar dateOfDiagnosis;
    private String cure;
    private String responsible;

    public Diagnosis(String disease, GregorianCalendar dateOfDiagnosis, String cure, String responsible) {
        this.disease = disease;
        this.dateOfDiagnosis = dateOfDiagnosis;
        this.cure = cure;
        this.responsible = responsible;
    }

    Diagnosis(String disease, String dateOfDiagnosis, String cure, String responsible) throws ParseException {
        this(disease, Person.parseToDateFromStringShort(dateOfDiagnosis), cure, responsible);
    }

    //TODO Add spell checking pattern n
    //Format: Disease, date, cure, responsible name
    Diagnosis(String text) throws ParseException {
        this(text.split(", ")[0], text.split(", ")[1], text.split(", ")[2], text.split(", ")[3]);
    }

    public String getDisease() {
        return disease;
    }

    public GregorianCalendar getDate() {
        return dateOfDiagnosis;
    }

    public String getCure() {
        return cure;
    }

    public String getResponsible() {
        return responsible;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Diagnosis)) return false;

        Diagnosis diagnosis = (Diagnosis) o;

        if (!getDisease().equals(diagnosis.getDisease())) return false;
        if (!getDate().equals(diagnosis.getDate())) return false;
        if (!getCure().equals(diagnosis.getCure())) return false;
        return getResponsible().equals(diagnosis.getResponsible());

    }

    @Override
    public int hashCode() {
        int result = getDisease().hashCode();
        result = 31 * result + getDate().hashCode();
        result = 31 * result + getCure().hashCode();
        result = 31 * result + getResponsible().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return  "" + disease +
                ", " + Person.parseGregorianToString(dateOfDiagnosis) +
                ", " + cure +
                ", " + responsible + ":";
    }
}
