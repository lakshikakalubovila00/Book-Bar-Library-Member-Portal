
let tableBody= document.getElementById("tableBodyMyBorrowings");


window.addEventListener("load",()=>{
    // call refresh table function
    refreshMyBorrowingsTable();
})

const refreshMyBorrowingsTable=()=>{
    // IF table is already DataTable THEN remove it THEN create new DataTable
    if ($.fn.DataTable.isDataTable('#tableMyBorrowings')) {
        $('#tableMyBorrowings').DataTable().destroy();
    }

    let loggedMember= getHttpServiceRequest("/member/loggedmember");
    let myborrowings = ajaxGetrequest("/borrowings/bymember/"+ loggedMember.id);

    // property array
    let displayProperty = [
        { propertyName: getBorrowCode, dataType: "function" },
        { propertyName: getAccessionNo, dataType: "function" },
        { propertyName: getBookName, dataType: "function" },
        { propertyName: getBorrowDate, dataType: "function" },
        { propertyName: getHandoverDueDate, dataType: "function" },
        { propertyName: "renewdate", dataType: "string" },
        { propertyName: "renewhandoverduedate", dataType: "string" },
        { propertyName: "actualhandovereddate", dataType: "string" },
        { propertyName: "delaydays", dataType: "string" },
        { propertyName: "finecost", dataType: "string" },
        { propertyName: getBorrowHasBookCopyStatus, dataType: "function" }
    ];

    //fill data into table function
    fillDataIntoTableInfo(
        tableBody,
        myborrowings,
        displayProperty
    );

    $("#tableMyBorrowings").DataTable({
        responsive: true,
        autoWidth: false
    });
}
const getBorrowCode=(ob)=>{
    return ob.borrow_id.borrowcode;
}

const getAccessionNo=(ob)=>{
    return ob.bookcopy_id.accessionno;
}

const getBookName=(ob)=>{
    return ob.bookcopy_id.book_id.title;
}
const getBorrowDate=(ob)=>{
    return ob.borrow_id.borrowdate;
}
const getHandoverDueDate=(ob)=>{
    return ob.borrow_id.handoverduedate;
}
const getBorrowHasBookCopyStatus=(ob)=>{
    return ob.borrowhasbookcopystatus_id.name;
}




