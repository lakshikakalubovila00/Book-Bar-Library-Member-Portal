package lk.bookbarlibrarymember.notification.dao;

import lk.bookbarlibrarymember.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationDao extends JpaRepository<Notification,Integer> {

    @Query(value = "select n from Notification n where n.member_id.id=?1 order by n.addeddatetime desc ")
    List<Notification> getNotificationByMember(Integer memberid);

    @Query(value = "select count(n) from Notification n where n.member_id.id=?1 and n.isread=false ")
    Integer getUnreadNotificationCount(Integer memberid);
}
