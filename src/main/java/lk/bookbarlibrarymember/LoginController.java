package lk.bookbarlibrarymember;

import lk.bookbarlibrarymember.member.dao.MemberDao;
import lk.bookbarlibrarymember.member.entity.ChangeUser;
import lk.bookbarlibrarymember.member.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController // active services
    public class LoginController {

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

        // get mapping for load memberlogin.html
    @GetMapping(value="/login")
    public ModelAndView loginUi(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView loginView = new ModelAndView();
        loginView.addObject("loggedusername", authentication.getName());
        loginView.addObject("title", "Login");
        loginView.setViewName("memberlogin.html");
        return loginView;
    }

    // get mapping for load dashboard
    @GetMapping(value="/dashboard")
    public ModelAndView dashboardUi(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView dashboardView = new ModelAndView();
        dashboardView.addObject("loggedusername" , authentication.getName());
        dashboardView.addObject("title", "Dashboard");
        dashboardView.setViewName("memberdashboard.html");
        return dashboardView;
    }
    @GetMapping(value="/profile")
    public ModelAndView profileUi(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView profileView = new ModelAndView();
        profileView.addObject("loggedusername" , authentication.getName());
        profileView.addObject("title", "Profile");
        profileView.setViewName("profile.html");
        return profileView;
    }

    @GetMapping(value="/editprofile")
    public ModelAndView editProfileUi(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView editProfileView = new ModelAndView();
        editProfileView.addObject("loggedusername" , authentication.getName());
        editProfileView.addObject("title", "Edit Profile");
        editProfileView.setViewName("editprofile.html");
        return editProfileView;
    }

    // get mapping error page
    @GetMapping(value="/errorpage")
    public ModelAndView errorpageUi(){
        ModelAndView errorpageView = new ModelAndView();
        errorpageView.setViewName("errorpage.html");
        return errorpageView;
    }


    @PostMapping(value="/savechangeprofile")
    public String changeUserProfileSave(@RequestBody ChangeUser changeUser){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member loggeduser = memberDao.getByUsername(authentication.getName());

        // duplicate username
        Member extUserByUsername =memberDao.getByUsername(changeUser.getUsername());
        if (extUserByUsername != null && !extUserByUsername.getUsername().equals(loggeduser.getUsername())) {
            return "Save not completed : Given Username "+ changeUser.getUsername() +" already exists ";
        }

        // duplicate email
        Member extUserByEmail =memberDao.getByEmail(changeUser.getEmail());
        if (extUserByEmail != null && !extUserByEmail.getEmail().equals(loggeduser.getEmail())) {
            return "Save not completed : Given Email "+ changeUser.getEmail() +" already exists ";
        }

        try{
            Member member = memberDao.getByUsername(authentication.getName());

            member.setMemberphoto(changeUser.getUserphoto());
            member.setUsername(changeUser.getUsername());
            member.setEmail(changeUser.getEmail());

            // if have a new password
            if(changeUser.getNewpassword()!=null){
                //if matches - no changes
                if(bCryptPasswordEncoder.matches(changeUser.getNewpassword(),member.getPassword())){
                    return "Save Not Completed : Password Match to Previous Password";
                }else{
                    // new password
                    member.setPassword(bCryptPasswordEncoder.encode(changeUser.getNewpassword()));
                }
            }

            memberDao.save(member);
            return "OK";

        }catch(Exception e){
            return "Changes not completed."+e.getMessage();
        }

    }
}
