const imgMemberPhotoElement= document.getElementById("imgMemberPhoto");
const textNameElement= document.getElementById("textName");
const textMemberNoElement= document.getElementById("textMemberNo");
const textMemberTypeElement= document.getElementById("textMemberType");
const textNICElement= document.getElementById("textNIC");
const textDOBElement= document.getElementById("textDOB");
const textEmailElement= document.getElementById("textEmail");
const textMobileNoElement= document.getElementById("textMobileNo");
const textAddressElement= document.getElementById("textAddress");
const textGuarantorNameElement= document.getElementById("textGuarantorName");
const textMembershipTypeElement= document.getElementById("textMembershipType");
const textStartDateElement= document.getElementById("textStartDate");
const textEndDateElement= document.getElementById("textEndDate");
const memberDetailsTable= document.getElementById("memberDetailsTable");

window.addEventListener("load",()=>{
    fillMemberDetails();
})
const fillMemberDetails=()=>{
    let loggedMember= getHttpServiceRequest("/member/loggedmember");
    let lastMembershipByMemberid= getHttpServiceRequest("/membership/bymember/"+loggedMember.id);

    if(!lastMembershipByMemberid){
        memberDetailsTable.innerText="";
        return;
    }
    if(lastMembershipByMemberid && lastMembershipByMemberid.id){
        if(loggedMember.memberphoto){
            imgMemberPhotoElement.src= atob(loggedMember.memberphoto);
        }else{
            imgMemberPhotoElement.src="/resources/images/memberdefault.png";
        }

        textNameElement.innerText = loggedMember.name ?? "-";
        textMemberNoElement.innerText = loggedMember.memberno ?? "-";
        textMemberTypeElement.innerText = loggedMember.membertype ?? "-";
        textNICElement.innerText = loggedMember.nic ?? "-";
        textDOBElement.innerText = loggedMember.dob ?? "-";
        textEmailElement.innerText = loggedMember.email ?? "-";
        textMobileNoElement.innerText = loggedMember.mobileno ?? "-";
        textAddressElement.innerText = loggedMember.address ?? "-";
        textGuarantorNameElement.innerText = loggedMember.guarantor_id?.name ?? "-";
        textMembershipTypeElement.innerText = lastMembershipByMemberid?.membershiptype_id?.name ?? "-";
        textStartDateElement.innerText = lastMembershipByMemberid?.startdate ?? "-";
        textEndDateElement.innerText = lastMembershipByMemberid?.enddate ?? "-";

    }
}

