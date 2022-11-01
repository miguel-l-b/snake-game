package socket;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class MessageData {
    public final Class<?> type;
    public final String data;

    public MessageData(Class<?> type, String data) {
        this.type = type;
        this.data = data;
    }

    public Object convertData() throws JsonSyntaxException 
    { return new Gson().fromJson(data, type); }
}
