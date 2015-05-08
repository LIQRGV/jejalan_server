package co.id.jejalan.rest.adapterClass;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateTimeAdapter extends XmlAdapter<Timestamp, String> {

    @Override
    public String unmarshal(Timestamp dateString) throws Exception {
        return dateString.toString();
    }

    @Override
    public Timestamp marshal(String dateTime) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat();
        long longTime = format.parse(dateTime).getTime(); 
        Timestamp timestamp = new Timestamp(longTime);
        return timestamp;
        //System.out.println("DEAD CODEEEEEEE LOL"); pasti kerjaan rafi
    }
}
