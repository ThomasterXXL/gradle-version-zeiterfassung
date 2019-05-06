package zeiterfassung;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DataStore {
    private Data data = null;
    //added Exception throw to signatures, because lazy.
    public DataStore() throws JAXBException {
        // TODO: autoload?

        Data data;
        JAXBContext jaxbContext = JAXBContext.newInstance(Data.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        File xml = new File("src/data.xml");
        //sysout to confirm Construction success.
        System.out.println(xml.toString());
        data = (Data) jaxbUnmarshaller.unmarshal(xml);
        if (data != null) {
            this.data = data;
        }
    }

    //Thomas: save and load is more human-readable, I think.
    //public void load() {
    //    // TODO: load data from xml
    //}
    //
    //public void unload() {
    //    // TODO: save data to xml
    //}

    @XmlRootElement
    public class Data {
        @XmlElement
        Map<String, Collection<Object>> persistent = new HashMap<String, Collection<Object>>();
        @XmlElement
        private ArrayList<Object> settings;
        @XmlElement
        private ArrayList<Object> state;
        @XmlElement
        private ArrayList<Object> userdata;

        Data(){
            //fill ArrayLists with stuff to print to test if it works. At this point, there are still null...
            persistent.put("settings", settings);
            persistent.put("state", state);
            persistent.put("userdata", userdata);

        }
    }

    public void save() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Data.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        File xml = new File("data.xml");
        jaxbMarshaller.marshal(data, xml); //marshal is Spanish for: Object -> File
        System.out.println(xml.toString());
        //Sysout generated xml to confirm save success.
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        //reload and print the file to confirm success.
        System.out.println(jaxbUnmarshaller.unmarshal(new File("src/data.xml")).toString()); //unmarshal is Spanish for: File to Object
    }
    public void load() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Data.class);
        //Sysout generated xml to confirm save success.
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        File xml = new File("src/data.xml");
        System.out.println(xml.toString());
        this.data = (Data) jaxbUnmarshaller.unmarshal(xml); //unmarshal is Spanish for: File to Object
    }
}
