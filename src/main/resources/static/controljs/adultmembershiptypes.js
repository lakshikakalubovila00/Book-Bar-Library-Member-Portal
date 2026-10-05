let tableBody= document.getElementById("tableBodyAdultMembershipTypes");

window.addEventListener("load",()=>{
    // call refresh table function
    refreshMembershipTypeTable();
})


const refreshMembershipTypeTable=()=>{
    // IF table is already DataTable THEN remove it THEN create new DataTable
    if ($.fn.DataTable.isDataTable('#tableAdultMembershipTypes')) {
        $('#tableAdultMembershipTypes').DataTable().destroy();
    }

    let validmembershipTypes = ajaxGetrequest("/adultmembershiptypes/valid");

    // property array
    let displayProperty = [
        { propertyName: "name", dataType: "string" },
        { propertyName: "membershipfee", dataType: "string" },
        { propertyName: "renewalfee", dataType: "string" },
        { propertyName: "borrowlimit", dataType: "string" },
        { propertyName: "borrowduration", dataType: "string" },
        { propertyName: "reservationlimit", dataType: "string" },
        { propertyName: "reservationduration", dataType: "string" },
        { propertyName: "reservationfee", dataType: "string" },
        { propertyName: "fineprice", dataType: "string" },
    ];

    //fill data into table function
    fillDataIntoTableInfo(
        tableBody,
        validmembershipTypes,
        displayProperty
    );

    $("#tableAdultMembershipTypes").DataTable({
        responsive: true,
        autoWidth: false
    });
}