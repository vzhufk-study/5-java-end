package InputOutput;

import Classes.Hospital;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
/**
 * Created by Zhufyak V.V.
 * zhufyakvv@gmail.com
 * github.com/zhufyakvv
 * 01.11.2016
 **/
public class JSonIO{

    @Override
    public void toFile(Hospital hosp, String fileName) {
        Hospital hospital = new Hospital(hosp);
        ObjectMapper mapper = new ObjectMapper();

        File file = new File(fileName + ".json");

        try {
            mapper.writeValue(file, hospital);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Classes.Hospital fromFile(String fileName) {

        ObjectMapper mapper = new ObjectMapper();
        File file = new File(fileName + ".json");
        Hospital hospital = new Hospital("");

        try {
            hospital = mapper.readValue(file, Hospital.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Classes.Hospital(hospital);
    }

}
