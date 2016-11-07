package be.dragoncave.util;

import be.dragoncave.domain.Country;
import com.thoughtworks.xstream.XStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

/**
 * Created by benoit on 02/11/2016.
 */
@Component("CountryConverter")
public class CountryConverterImpl implements CountryConverter {

    @Autowired
    private XStreamMarshaller xStreamMarshaller;

    @Override
    public List<Country> parse(String path) {
        File file = new File(path);
        if (file.exists()) {
            FileReader reader = null;  // load file
            try {

                reader = new FileReader(file);

                XStream xstream = xStreamMarshaller.getXStream();
                //  xstream.processAnnotations(Country.class);
                // xstream.processAnnotations(Data.class); // inform XStream to parse annotations in Data class
                // xstream.processAnnotations(Data.class); // inform XStream to parse annotations in Data class
                xstream.alias("countries", Data.class);

                xstream.alias("country", Country.class);
                xstream.aliasAttribute(Country.class, "countryName", "countryName");
                ;

                xstream.addImplicitCollection(Data.class, "countriesList");
                //   xstream.aliasField("countries",Data.class,"bans");
                Data data = (Data) xstream.fromXML(reader); // parse
                return data.getCountriesList();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

}
