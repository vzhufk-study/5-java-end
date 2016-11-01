package Classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

/**
 * Created by zhufy on 14.09.2016.
 */
enum Gender{Male, Female}

class Person {
    private String name;
    private GregorianCalendar dateOfBirth;
    private Gender sex;
    private String address;

    Person(String name, Date dateOfBirth, Gender sex, String address){
        this.name = name;
        this.dateOfBirth.setGregorianChange(dateOfBirth);
        this.sex = sex;
        this.address = address;
    }

    private static boolean isValidForConstructor(String text){
        Pattern personPattern = Pattern.compile("\\w*\\s\\w*,\\s\\d\\d-\\d\\d-\\d\\d\\d\\d,\\s(Male|Female),\\s.*");
        return personPattern.matcher(text).matches();
    }

    Person(String text) throws ParseException {
        if (isValidForConstructor(text)){
            String name = text.split(", ")[0];
            String date = text.split(", ")[1];
            String sex = text.split(", ")[2];
            String address = text.substring(text.indexOf(sex)+sex.length() + 2);

            this.name = name;
            this.dateOfBirth = parseToDateFromStringShort(date);
            this.sex = Gender.valueOf(sex);
            this.address = address;
        }

    }

    //TODO take parser to other class and package
    public static GregorianCalendar parseToDateFromString(String text, String format) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date result = new Date();
        try {
            result = dateFormat.parse(text);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(result);
        return cal;
    }

    public static GregorianCalendar parseToDateFromStringShort(String text) throws ParseException {
        return  parseToDateFromString(text, "dd-mm-yyyy");
    }

    public static String parseGregorianToString(GregorianCalendar date, String format){
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date.getTime());
    }

    public static String parseGregorianToString(GregorianCalendar date){
        return parseGregorianToString(date, "dd-mm-yyyy");
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

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getDateOfBirth().hashCode();
        result = 31 * result + getSex().hashCode();
        result = 31 * result + getAddress().hashCode();
        return result;
    }

    public String getName() {

        return name;
    }

    public GregorianCalendar getDateOfBirth() {
        return dateOfBirth;
    }

    public Gender getSex() {
        return sex;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return   name +
                ", " + parseGregorianToString(dateOfBirth) +
                ", " + sex +
                ", " + address;
    }
}
