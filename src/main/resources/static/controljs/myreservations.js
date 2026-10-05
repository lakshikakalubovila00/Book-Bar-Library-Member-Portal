
let tableBody= document.getElementById("tableBodyMyReservations");

window.addEventListener("load",()=>{
    // call refresh table function
    refreshMyReservationsTable();
})

const refreshMyReservationsTable=()=>{
    // IF table is already DataTable THEN remove it THEN create new DataTable
    if ($.fn.DataTable.isDataTable('#tableMyReservations')) {
        $('#tableMyReservations').DataTable().destroy();
    }

    let loggedMember= getHttpServiceRequest("/member/loggedmember");
    let myreservations = ajaxGetrequest("/reservations/bymember/"+ loggedMember.id);

    // property array
    let displayProperty = [
        { propertyName: "reservationno", dataType: "string" },
        { propertyName: getAccessionNo, dataType: "function" },
        { propertyName: getBookName, dataType: "function" },
        { propertyName: "reserveddate", dataType: "string" },
        { propertyName: "borrowdate", dataType: "string" },
        { propertyName: getReservationStatus, dataType: "function" }
    ];

    //fill data into table function
    fillDataIntoTableInfo(
        tableBody,
        myreservations,
        displayProperty
    );

    $("#tableMyReservations").DataTable({
        responsive: true,
        autoWidth: false
    });
}

const getAccessionNo=(ob)=>{
    return ob.bookcopy_id.accessionno;
}

const getBookName=(ob)=>{
    return ob.bookcopy_id.book_id.title;
}
const getReservationStatus=(ob)=>{
    return ob.reservationstatus_id.name
}






