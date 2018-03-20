package ru.stc.model.utils;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Timestamp;
import java.util.GregorianCalendar;

/**
 * Created by admin on 19.04.2017.
 */
public class DateConverter {
//    static {
//        PropertyConfigurator.configure("log4j.properties");
//    }
//    private static final Logger LOGGER = Logger.getLogger(Main.class);

    /**
     * Converts from Timestamp to XMLGregorianCalendar
     * @param timestamp
     * @return
     */
    public static XMLGregorianCalendar convertTimestampToXmlGreg(Timestamp timestamp)
    {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(timestamp);

        XMLGregorianCalendar xmlGregorianCalendar = null;

        try {
            xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        }
        catch (DatatypeConfigurationException e)
        {
            //LOGGER.debug("Couldn't convert XMLGregorianCalendar to Timestamp:" + e.getMessage());
        }

        return xmlGregorianCalendar;
    }

    /**
     * Converts from XMLGregorianCalendar to Timestamp
     * @param xmlGregorianCalendar
     * @return
     */
    public static Timestamp convertXmlGregToTimestamp(XMLGregorianCalendar xmlGregorianCalendar)
    {
        return new Timestamp(xmlGregorianCalendar.toGregorianCalendar().getTimeInMillis());
    }
}
