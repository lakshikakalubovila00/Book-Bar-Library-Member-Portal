const notificationCountElement = document.getElementById("notificationCount");
const notificationListElement = document.getElementById("notificationList");

console.log("sidebar js loaded");

let loggedmember= getHttpServiceRequest("/member/loggedmember");
if(loggedmember.memberphoto!=null){
    imgUserImageOffCanvas.src= atob(loggedmember.memberphoto);
    imgUserImage.src= atob(loggedmember.memberphoto);
}

const loadNotifications=()=>{
    let loggedmember= getHttpServiceRequest("/member/loggedmember");
    let notifications= getHttpServiceRequest("/notification/bymember/"+loggedmember.id);
    console.log (typeof notifications);
    notificationListElement.innerHTML="";
    notifications.forEach(notification=>{
        let li = document.createElement("li");
        li.className="dropdown-item";
        if(notification.isread==false){
            li.style.backgroundColor="#f1f5ff";
        }
        li.innerHTML=`<div><div class="fw-bold">${notification.title} </div><div class="normal-text mt-1">${notification.messagetext}</div><div class="small mt-1">${notification.addeddatetime}</div></div>`;
        li.onclick=()=>{
            if(notification.isread== false){
                getHttpServiceRequest("/notification/markasread", "PUT", notification)
                loadNotifications();
            }
        }
        notificationListElement.appendChild(li);
    })
}
const loadUnreadnotificationCount=()=>{
    let loggedmember= getHttpServiceRequest("/member/loggedmember");
    let notificationCount= getHttpServiceRequest("/notification/unreadcount/"+loggedmember.id);
    notificationCountElement.innerText= notificationCount;
    if(notificationCount == 0){
        notificationCountElement.classList.add("d-none");
    }else{
        notificationCountElement.classList.remove("d-none");
    }
}

window.addEventListener("load", () => {
    loadNotifications();
    loadUnreadnotificationCount();
});

// refresh notification every 30 seconds
setInterval(() => {
    loadNotifications();
    loadUnreadnotificationCount();
}, 30000);

const logoutConfirmation=()=>{
    let userConfirmationMsg= "Are you sure to logout ? "
    Swal.fire({
        title: userConfirmationMsg,
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#fb73a4",
        cancelButtonColor: "rgb(90,184,230)",
        confirmButtonText: "Yes",
        cancelButtonText: "Cancel",
        reverseButtons :true

    }).then((result)=>{
        if(result.isConfirmed){
            window.location.replace('/logout')
        }
    })
}