import uk.co.praguematica.smservlet.UserContext;
import uk.co.praguematica.urlmapping.handlers.SecurityHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by mfa on 07/10/2016.
 */
public class ExampleSecurityHandler implements SecurityHandler {
    @Override
    public boolean checkAuthentication(HttpServletRequest httpServletRequest) {
        String userId = (String)httpServletRequest.getParameter("userId");
        return userId != null;
    }

    @Override
    public UserContext getUserContext(HttpServletRequest httpServletRequest) {
        UserContext userContext = new UserContext(httpServletRequest.getParameter("userId"), (String)httpServletRequest.getParameter("userName"));
        return userContext;
    }
}
