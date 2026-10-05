package lk.bookbarlibrarymember.opac.contoller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class OpacContoller {
    @RequestMapping(value="/catalog")
    public ModelAndView getUi() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView opacPortalView = new ModelAndView();
        opacPortalView.addObject("loggedusername", authentication.getName());
        opacPortalView.addObject("title", "OPAC");
        opacPortalView.setViewName("opacportal.html");
        return opacPortalView;
    }
}
