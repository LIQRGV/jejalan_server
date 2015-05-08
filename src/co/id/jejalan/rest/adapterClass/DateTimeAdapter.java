package co.id.jejalan.rest.adapterClass;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateTimeAdapter extends XmlAdapter<String, Timestamp> {

    @Override
    public String marshal(Timestamp dateString) throws Exception {
    	SimpleDateFormat format = new SimpleDateFormat();
    	String parsed = format.format(dateString);
        //return dateString.toString();
    	return parsed;
    }

    @Override
    public Timestamp unmarshal(String dateTime) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat();
        long longTime = format.parse(dateTime).getTime(); 
        Timestamp timestamp = new Timestamp(longTime);
        //Date date = new Date(longTime);
        return timestamp;
    	//return new Timestamp(dateTime);
        //System.out.println("DEAD CODEEEEEEE LOL"); pasti kerjaan rafi
    }
}
