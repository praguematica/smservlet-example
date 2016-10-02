import uk.co.praguematica.smservlet.SelfMappedServlet;
import uk.co.praguematica.urlmapping.annotations.Attribute;
import uk.co.praguematica.urlmapping.annotations.PathVariable;
import uk.co.praguematica.urlmapping.annotations.RequestMapping;
import uk.co.praguematica.urlmapping.serializers.Format;

import java.io.IOException;

/**
 * Created by mfa on 02/10/2016.
 */
public class ExampleServlet extends SelfMappedServlet {
    @RequestMapping("data/string/print-something")
    public String printSomething() {
        return "Print Something";
    }

    @RequestMapping("data/string/print-path-variable/{var1}")
    public String printPathVariable(@PathVariable("var1") String var1) {
        return "Get " + var1;
    }

    @RequestMapping("data/string/print-attribute")
    public String getSomething(@Attribute("var2") String var2) {
        return "Get " + var2;
    }

    @RequestMapping(value="data/json/print-something", responseFormat = Format.JSON)
    public Object printJson() {
        return new Person("John", "Smith");
    }

}
