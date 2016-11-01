package Exceptions;

/**
 * Created by Zhufyak V.V.
 * zhufyakvv@gmail.com
 * github.com/zhufyakvv
 * 01.11.2016
 **/
public class StaffPostException extends Exception {
        public StaffPostException(){ super("You cant change this kind of staff"); }
        public StaffPostException(String message) { super(message); }

}
