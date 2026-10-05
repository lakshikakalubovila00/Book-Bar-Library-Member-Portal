
const currentBorrowingsContainerElement= document.getElementById("currentBorrowingsContainer");
const currentReservationsContainerElement= document.getElementById("currentReservationsContainer");


window.addEventListener("load",()=>{
    refreshCurrentBorrowings()
    refreshCurrentReservations()
})
const refreshCurrentBorrowings=()=>{
    let loggedMember= getHttpServiceRequest("/member/loggedmember");
    let borrowings = ajaxGetrequest("/borrow/borrowedbooks/"+loggedMember.id);
    // create table according to the length of borrowings
    // show these info
    // book cover image
    // Book Name
    // Author Name
    // Borrowed Date
    // Handover Due Date
    if(borrowings.length===0){
        currentBorrowingsContainerElement.innerHTML=`
        <div class="col">
            <span class="normal-text fw-bold">
                No Current Borrowings
            </span>
        </div>`;
        return;
    }
    borrowings.forEach((borrowing)=>{

        if(borrowing.bookcopy_id.book_id.coverimage){
            bookImgSrc= atob(borrowing.bookcopy_id.book_id.coverimage);
        }else{
            bookImgSrc="/resources/images/bookdefault.png";
        }
        // delay days and fine for today if have
        let today= new Date();
        let dueDate;
        if(borrowing.renewhandoverduedate){
            dueDate= new Date(borrowing.renewhandoverduedate)
        }else{
            dueDate= new Date(borrowing.borrow_id.handoverduedate)
        }
        let delayDays = Math.floor((today- dueDate)/ 86400000);

        // no delay if negative
        if(delayDays<0){
            delayDays=0;
        }
        let lastMembership = ajaxGetrequest("/membership/bymember/" + loggedMember.id)
        let delayFinePrice= lastMembership.membershiptype_id.fineprice;
        let delayFine= delayDays* delayFinePrice;

        let borrowingCard=`
        <div class="col-lg-4 col-md-6 col-sm-12 mb-3">
            <div class="row">
                        <div class="col-4">
                            <div class="book-cards">
                                <img src="${bookImgSrc}" alt="" class="book-img">
                            </div>
                        </div>
                        <div class="col-8">
                            <!--book name-->
                            <span class="normal-text"><strong>Book : </strong>${borrowing.bookcopy_id.book_id.title}</span><br>
                            <!--author name-->
                            <span class="normal-text"><strong>Author : </strong>${borrowing.bookcopy_id.book_id.author}</span><br>
                            <!--borrow date-->
                            <span class="normal-text"><strong>Borrow Date : </strong>${borrowing.borrow_id.borrowdate}</span><br>
                            <!--handover due date-->
                            <span class="normal-text"><strong>Handover Due Date : </strong>${borrowing.borrow_id.handoverduedate}</span><br>
                            <!--renew handover due date-->
                            <span class="normal-text"><strong>Renew Handover Due Date : </strong>${borrowing.renewhandoverduedate ? borrowing.renewhandoverduedate : "-"}</span><br>
                            <!--delay days for today-->
                            <span class="normal-text"><strong>Delay Days For Today : </strong>${delayDays} Days</span><br>
                            <!--delay fine fee for today-->
                            <span class="normal-text"><strong>Delay Fine Fee For Today : </strong>Rs.${delayFine}</span>
                        </div>
                    </div>
        </div>`;
        currentBorrowingsContainerElement.innerHTML+=borrowingCard;
    })
}

const refreshCurrentReservations=()=>{
    let loggedMember= getHttpServiceRequest("/member/loggedmember");
    let reservation = ajaxGetrequest("/reservation/bymember/"+loggedMember.id);

    if(reservation.length===0){
        currentReservationsContainerElement.innerHTML=`
        <div class="col">
            <span class="normal-text fw-bold">
                No Current Reservations
            </span>
        </div>`;
        return;
    }
        if(reservation.bookcopy_id.book_id.coverimage){
            bookImgSrc= atob(reservation.bookcopy_id.book_id.coverimage);
        }else{
            bookImgSrc="/resources/images/bookdefault.png";
        }

        let reservationCard=`
        <div class="col-lg-4 col-md-6 col-sm-12 mb-3">
            <div class="row">
                        <div class="col-4">
                            <div class="book-cards">
                                <img src="${bookImgSrc}" alt="" class="book-img">
                            </div>
                        </div>
                        <div class="col-8">
                            <!--book name-->
                            <span class="normal-text"><strong>Book : </strong>${reservation.bookcopy_id.book_id.title}</span><br>
                            <!--author name-->
                            <span class="normal-text"><strong>Author : </strong>${reservation.bookcopy_id.book_id.author}</span><br>
                            <!--reservation date-->
                            <span class="normal-text"><strong>Reserve Date : </strong>${reservation.reserveddate}</span><br>
                            <!--borrow date-->
                            <span class="normal-text"><strong>Borrow Date : </strong>${reservation.borrowdate}</span><br>
                        </div>
                    </div>
        </div>`;
        currentReservationsContainerElement.innerHTML+=reservationCard;
}