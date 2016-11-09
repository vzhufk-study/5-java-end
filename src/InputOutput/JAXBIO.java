package InputOutput;

/**
 * Created by Zhufyak V.V.
 * zhufyakvv@gmail.com
 * github.com/zhufyakvv
 * 01.11.2016
 **/

import Entities.Hospital;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JAXBIO{

    public void toFile(Entities.Hospital h, String fileName) {
        try {

            Hospital hospital = new Hospital(h);
            fileName += ".xml";

            File file = new File(fileName);
/*
            JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(c, sw);
            String xmlString = sw.toString();
*/
            JAXBContext jaxbContext = JAXBContext.newInstance(Hospital.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(h, file);
            //jaxbMarshaller.marshal(hospital, file);
            //jaxbMarshaller.marshal(Hospital, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public Entities.Hospital fromFile(String fileName) {
        try {
            File file = new File(fileName + ".xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Hospital.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Hospital fromFile = (Hospital) jaxbUnmarshaller.unmarshal(file);
            Entities.Hospital Hospital = new Entities.Hospital(fromFile);
            return Hospital;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
