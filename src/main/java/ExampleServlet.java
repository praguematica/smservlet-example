import uk.co.praguematica.smservlet.SelfMappedServlet;
import uk.co.praguematica.smservlet.UserContext;
import uk.co.praguematica.urlmapping.MappingProcessorException;
import uk.co.praguematica.urlmapping.annotations.Attribute;
import uk.co.praguematica.urlmapping.annotations.PathVariable;
import uk.co.praguematica.urlmapping.annotations.RequestMapping;
import uk.co.praguematica.urlmapping.annotations.SelfMapped;
import uk.co.praguematica.urlmapping.handlers.AnonymousSecurityHandler;
import uk.co.praguematica.urlmapping.serializers.Format;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mfa on 02/10/2016.
 */
public class ExampleServlet extends SelfMappedServlet {
    @Override
    public void init() throws ServletException {
        super.init();
        try {
            mappingProcessor.registerSerializer(new ExampleSerializer());
        } catch (MappingProcessorException e) {
            throw new ServletException(e);
        }
    }

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

    @RequestMapping(value="data/string/secured-method", securityHandler=ExampleSecurityHandler.class)
    public String securedMethod(UserContext userContext) {
        return "This method has been secured. Logged in user: " + userContext.getUserId();
    }


    @RequestMapping(value="data/json/print-something", responseFormat = Format.JSON)
    public Object printJson() {
        return new Person("John", "Smith");
    }

    @RequestMapping(value="data/custom/print-something", responseFormat = "exs")
    public Object printCustom() {
        List<String> result = new ArrayList<String>();
        result.add("Item 1");
        result.add("Item 2");
        result.add("Item 3");
        result.add("Item 4");
        result.add("Item 5 - \"needs escaping\"");
        return result;
    }

}
