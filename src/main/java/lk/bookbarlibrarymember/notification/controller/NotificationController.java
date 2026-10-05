package lk.bookbarlibrarymember.notification.controller;


import lk.bookbarlibrarymember.notification.dao.NotificationDao;
import lk.bookbarlibrarymember.notification.entity.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NotificationController {
    @Autowired
    private NotificationDao notificationDao;

    @GetMapping(value = "/notification/bymember/{memberid}", produces = "application/json")
    public List<Notification> getNotificationByMember(@PathVariable Integer memberid){
        return notificationDao.getNotificationByMember(memberid);
    }

    @GetMapping(value = "/notification/unreadcount/{memberid}" , produces = "application/json")
    public Integer getUnreadCount(@PathVariable Integer memberid){
        return notificationDao.getUnreadNotificationCount(memberid);
    }

    @PutMapping(value = "/notification/markasread")
    public String updateData(@RequestBody Notification notification) {
        // check existence
        if(notification.getId()==null){
            return "Notification Update not completed : Notification Not Exist..";
        }
        Notification extNotification= notificationDao.getReferenceById(notification.getId());
        if(extNotification.getId()==null){
            return "Notification Update not completed : Notification Not Exist..";
        }
        try{
            notification.setIsread(true);
            // operation
            notificationDao.save(notification);

            // check dependencies
            return "OK";
        }catch (Exception e){
            return "Notification update not completed. "+e.getMessage();
        }

    }

}
