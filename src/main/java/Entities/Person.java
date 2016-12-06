package Entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.ParseException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

@XmlRootElement
class Person {
    private String name;
    private GregorianCalendar dateOfBirth;
    private Gender sex;
    private String address;

    Person() throws ParseException {
        this.name = "";
        this.dateOfBirth = Parsers.DataParser.parseToDateFromStringShort("01-01-2001");
        this.sex = Gender.Male;
        this.address = "";
    }

    Person(String name, Date dateOfBirth, Gender sex, String address){
        this.name = name;
        this.dateOfBirth = new GregorianCalendar();
        this.dateOfBirth.setGregorianChange(dateOfBirth);
        this.sex = sex;
        this.address = address;
    }
    // TODO rename and javadoc
    private static boolean isPerson(String text){
        Pattern personPattern = Pattern.compile("\\w*\\s\\w*,\\s\\d\\d-\\d\\d-\\d\\d\\d\\d,\\s(Male|Female),\\s.*");
        return personPattern.matcher(text).matches();
    }

    Person(String text) throws ParseException {
        if (isPerson(text)){
            String name = text.split(", ")[0];
            String date = text.split(", ")[1];
            String sex = text.split(", ")[2];
            String address = text.substring(text.indexOf(sex)+sex.length() + 2);

            this.name = name;
            this.dateOfBirth = Parsers.DataParser.parseToDateFromStringShort(date);
            this.sex = Gender.valueOf(sex);
            this.address = address;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (!getName().equals(person.getName())) return false;
        if (!getDateOfBirth().equals(person.getDateOfBirth())) return false;
        if (getSex() != person.getSex()) return false;
        return getAddress().equals(person.getAddress());

    }

    public GregorianCalendar getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getDateOfBirth().hashCode();
        result = 31 * result + getSex().hashCode();
        result = 31 * result + getAddress().hashCode();
        return result;
    }

    @XmlElement
    public String getName() {

        return name;
    }

    @XmlElement
    public String getDateOfBirthString(){
        return Parsers.DataParser.parseGregorianToString(dateOfBirth);
    }

    @XmlElement
    public Gender getSex() {
        return sex;
    }

    @XmlElement
    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(GregorianCalendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setDateOfBirthString(String dateOfBirth) throws ParseException {
        this.dateOfBirth = Parsers.DataParser.parseToDateFromStringShort(dateOfBirth);
    }

    public void setSex(Gender sex) {
        this.sex = sex;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return   name +
                ", " + Parsers.DataParser.parseGregorianToString(dateOfBirth) +
                ", " + sex +
                ", " + address;
    }
}
