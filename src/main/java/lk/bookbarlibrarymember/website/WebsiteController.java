package lk.bookbarlibrarymember.website;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class WebsiteController {

    @GetMapping(value="/")
    public ModelAndView websiteUi(){
        ModelAndView websiteView = new ModelAndView();
        websiteView.addObject("title", "Book Bar Library");
        websiteView.setViewName("website.html");
        return websiteView;
    }
    @GetMapping(value="/aboutus")
    public ModelAndView aboutUsUi(){
        ModelAndView aboutUsView = new ModelAndView();
        aboutUsView.addObject("title", "Book Bar Library-About Us");
        aboutUsView.setViewName("aboutus.html");
        return aboutUsView;
    }
    @GetMapping(value="/librarypolicy")
    public ModelAndView libraryPolicyUi(){
        ModelAndView libraryPolicyView = new ModelAndView();
        libraryPolicyView.addObject("title", "Book Bar Library-Library Policy");
        libraryPolicyView.setViewName("librarypolicy.html");
        return libraryPolicyView;
    }
    @GetMapping(value="/opac")
    public ModelAndView opacUi(){
        ModelAndView opacView = new ModelAndView();
        opacView.addObject("title", "Book Bar Library-Catalog");
        opacView.setViewName("opac.html");
        return opacView;
    }
    @GetMapping(value="/adultmembershiptypes")
    public ModelAndView adultMembershipTypesUi(){
        ModelAndView adultMembershipTypesView = new ModelAndView();
        adultMembershipTypesView.addObject("title", "Book Bar Library-Adult  Membership Types");
        adultMembershipTypesView.setViewName("adultmembershiptypes.html");
        return adultMembershipTypesView;
    }
    @GetMapping(value="/childmembershiptypes")
    public ModelAndView childMembershipTypesUi(){
        ModelAndView childMembershipTypesView = new ModelAndView();
        childMembershipTypesView.addObject("title", "Book Bar Library-Child  Membership Types");
        childMembershipTypesView.setViewName("childmembershiptypes.html");
        return childMembershipTypesView;
    }
}
