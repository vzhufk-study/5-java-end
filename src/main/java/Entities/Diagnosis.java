package Entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.ParseException;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by zhufy on 15.09.2016.
 */
@XmlRootElement
public class Diagnosis {
    private String disease;
    private GregorianCalendar dateOfDiagnosis;
    private String cure;
    private String responsible;

    public Diagnosis() throws ParseException {
        this.disease = "";
        this.dateOfDiagnosis  = Parsers.DataParser.parseToDateFromStringShort("01-01-2001");
        this.cure = "";
        this.responsible = "You";
    }

    public Diagnosis(String disease, Date dateOfDiagnosis, String cure, String responsible) {
        this.disease = disease;
        this.dateOfDiagnosis = new GregorianCalendar();
        this.dateOfDiagnosis.setTime(dateOfDiagnosis);
        this.cure = cure;
        this.responsible = responsible;
    }

    public Diagnosis(String disease, GregorianCalendar dateOfDiagnosis, String cure, String responsible) {
        this.disease = disease;
        this.dateOfDiagnosis = dateOfDiagnosis;
        this.cure = cure;
        this.responsible = responsible;
    }

    Diagnosis(String disease, String dateOfDiagnosis, String cure, String responsible) throws ParseException {
        this(disease, Parsers.DataParser.parseToDateFromStringShort(dateOfDiagnosis), cure, responsible);
    }

    //TODO Add spell checking pattern n
    //Format: Disease, date, cure, responsible name
    Diagnosis(String text) throws ParseException {
        this(text.split(", ")[0], text.split(", ")[1], text.split(", ")[2], text.split(", ")[3]);
    }

    @XmlElement
    public String getDisease() {
        return disease;
    }

    public GregorianCalendar getDateOfDiagnosis() {
        return dateOfDiagnosis;
    }

    @XmlElement
    public String getDateOfDiagnosisString() {
        return Parsers.DataParser.parseGregorianToString(dateOfDiagnosis);
    }

    @XmlElement
    public String getCure() {
        return cure;
    }

    @XmlElement
    public String getResponsible() {
        return responsible;
    }


    public void setDisease(String disease) {
        this.disease = disease;
    }
    public void setDateOfDiagnosis(GregorianCalendar dateOfDiagnosis) {
        this.dateOfDiagnosis = dateOfDiagnosis;
    }

    public void setDateOfDiagnosisString(String dateOfDiagnosis) throws ParseException {
        this.dateOfDiagnosis = Parsers.DataParser.parseToDateFromStringShort(dateOfDiagnosis);
    }

    public void setCure(String cure) {
        this.cure = cure;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Diagnosis)) return false;

        Diagnosis diagnosis = (Diagnosis) o;

        if (!getDisease().equals(diagnosis.getDisease())) return false;
        if (!getDateOfDiagnosis().equals(diagnosis.getDateOfDiagnosis())) return false;
        if (!getCure().equals(diagnosis.getCure())) return false;
        return getResponsible().equals(diagnosis.getResponsible());

    }

    @Override
    public int hashCode() {
        int result = getDisease().hashCode();
        result = 31 * result + getDateOfDiagnosis().hashCode();
        result = 31 * result + getCure().hashCode();
        result = 31 * result + getResponsible().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return  "" + disease +
                ", " + Parsers.DataParser.parseGregorianToString(dateOfDiagnosis) +
                ", " + cure +
                ", " + responsible + ":";
    }
}
