package Classes;

import java.text.ParseException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

enum Post{
    Physician, Nurse, Administrator, Medical_Technician, Dietitian, Clinical_Specialist, Mental_Health_Worker, Cleaner
}

/**
 * Created by zhufy on 14.09.2016.
 */
public class Staff extends  Person {

    private float salary;
    private GregorianCalendar dateOfHiring;
    //rename or not?
    private Post post;

    Staff(String name, Date dateOfBirth, Gender sex, String address, float salary, Date dateOfHiring, Post post) {
        super(name, dateOfBirth, sex, address);
        this.salary = salary;
        this.dateOfHiring.setGregorianChange(dateOfHiring);
        this.post = post;
    }

    private static boolean isValidForConstructor(String text){
        Pattern personPattern = Pattern.compile("\\d*,\\s\\d\\d-\\d\\d-\\d\\d\\d\\d,\\s\\w*");
        return personPattern.matcher(text).matches();
    }

    public Staff(String personText, String staffText) throws ParseException {
        super(personText);
        if (isValidForConstructor(staffText)){
            this.salary = Float.parseFloat(staffText.split(", ")[0]);
            this.dateOfHiring = Person.parseToDateFromStringShort(staffText.split(", ")[1]);
            this.post = Post.valueOf(staffText.split(", ")[2]);
        }
    }

    public Staff(String text) throws ParseException {
        super(text.split(": ")[0]);
        String staffText = text.split(": ")[1];
        if (isValidForConstructor(staffText)){
            this.salary = Float.parseFloat(staffText.split(", ")[0]);
            this.dateOfHiring = Person.parseToDateFromStringShort(staffText.split(", ")[1]);
            this.post = Post.valueOf(staffText.split(", ")[2]);
        }    }

    public float getSalary() {
        return salary;
    }

    public GregorianCalendar getDateOfHiring() {
        return dateOfHiring;
    }

    public Post getPost() {
        return post;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Staff)) return false;
        if (!super.equals(o)) return false;

        Staff staff = (Staff) o;

        if (Float.compare(staff.salary, salary) != 0) return false;
        if (!dateOfHiring.equals(staff.dateOfHiring)) return false;
        return post == staff.post;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (salary != +0.0f ? Float.floatToIntBits(salary) : 0);
        result = 31 * result + dateOfHiring.hashCode();
        result = 31 * result + post.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return  super.toString() + ": " + salary +
                ", " + parseGregorianToString(dateOfHiring) +
                ", " + post;
    }
}
