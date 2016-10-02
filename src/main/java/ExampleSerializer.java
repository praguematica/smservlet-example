import uk.co.praguematica.urlmapping.MappingProcessorException;
import uk.co.praguematica.urlmapping.serializers.Serializer;

import java.util.Collection;
import java.util.StringJoiner;

/**
 * Created by mfa on 02/10/2016.
 */
public class ExampleSerializer implements Serializer{
    public String type() {
        return "exs";
    }

    public Object serialize(Object o) throws MappingProcessorException {
        if (o != null & o instanceof Collection) {
            StringJoiner joiner = new StringJoiner("\",\"");
            for (Object item : (Collection) o) {
                String strItem = String.valueOf(item);
                String escaped = strItem.replaceAll("\"", "\\\"");
                joiner.add(String.valueOf(escaped));
            }
            return "\"" + joiner.toString() + "\"\n";
        }
        return "\n";
    }

    public <T> T deserialize(String s, Class<T> aClass) throws MappingProcessorException {
        return aClass.cast(s);
    }
}
