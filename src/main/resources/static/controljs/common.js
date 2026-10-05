// browser load event call
window.addEventListener("load", () => {
  // enable tooltip
  $('[data-bs-toggle="tooltip"]').tooltip();
})

// define function for get date value
const getDateValue=(dateOb)=>{

    let year = dateOb.getFullYear();
    let month= dateOb.getMonth()+1;
    if(month<10){
        month= "0"+month;
    }
    let date= dateOb.getDate();
    if(date<10){
        date="0"+date;
    }
    return year+"-"+month +"-"+date;
}



// define common function for call http get request(jquery ajax call)
const ajaxGetrequest = (url) => {
  let response = [];
  $.ajax({
    url: url,
    contentType: "application/json",
    type: "GET",
    async: false,
    success: function (successResponse) {
      console.log("Data = " + successResponse);
      response = successResponse;
    },
    error: function (errorresponse, errorMsg) {
      console.log("Error = " + errorMsg);
    }
  });
  return response;
}

// define common function for  call http request POST, PUT , DELETE (JQuery ajax call)\
const getHttpServiceRequest = (url, method, dataOb) => {

  let serviceResponse;

  $.ajax({
    url: url,
    contentType: "application/json",
    type: method,
    data: JSON.stringify(dataOb),
    async: false,
    success: function (successResponse) {
      console.log("data " + successResponse);
      serviceResponse = successResponse
    },
    error: function (errorResponse, errorMsg) {
      console.log("error" + errorMsg);
      serviceResponse = errorMsg;
    }
  })

  return serviceResponse;
  ;
}

//define function for set Initial color
const setInitial = (elementsList) => {
  for (const element of elementsList) {
    element.style.borderBottom = "1px solid #ced4da";
  }
}
const fillDataIntoDataList=(listElementId, dataList)=>{
    listElementId.innerHTML = "";

    dataList.forEach((element) => {
        let option = document.createElement("option");
        //option.value= dataob;
        // js object convert into json string
        option.value =element;
        listElementId.appendChild(option);
    });
}

//define funcyion for filldataintofropdown()
const fillDataIntoSelect = (selectElementId, message, dataList, property) => {
  selectElementId.innerHTML = "";
  if (message != "") {
    let optionmsg = document.createElement("option");
    optionmsg.innerText = message;
    optionmsg.value = "";
    optionmsg.selected = "selected";
    optionmsg.disabled = "disabled";
    selectElementId.appendChild(optionmsg);
  }

  dataList.forEach((dataob) => {
    let option = document.createElement("option");
    //option.value= dataob;
    // js object convert into json string
    option.value = JSON.stringify(dataob);
      //option.value = dataob[property];
    option.innerText = dataob[property];
    selectElementId.appendChild(option);
  });
};

// define function for filling radio buttons
const fillDataIntoRadio = (containerElementId, dataList, property, groupName) => {
  containerElementId.innerHTML = "";

  dataList.forEach((dataOb) => {
    let wrapper = document.createElement("div");

    wrapper.innerHTML = `
      <label>
        <input type="radio" class="form-check-input input-radio" name="${groupName}" value='${JSON.stringify(dataOb)}'>
        ${dataOb[property]}
      </label>
    `;
    containerElementId.appendChild(wrapper);
  });
};

// define function for set color for table row
const setColorForTableRow = (tableBodyElement, trElement, color) => {
  for (const rowelement of tableBodyElement.children) {
    rowelement.removeAttribute("style");
  }
  tableBodyElement.children[trElement].style.backgroundColor = color;
};

//fill data in to table - edit , delete, print by row click
const fillDataIntoTableEight = (
  tableBody,
  dataList,
  displayProperty,
  editFunctionName,
  defaultImagePath
) => {
  tableBody.innerHTML = "";

  dataList.forEach((dataOb, index) => {
    //tr

    let tr = document.createElement("tr");
    //td-8
    let tdIndex = document.createElement("td");
    tdIndex.innerText = index + 1;
    tr.appendChild(tdIndex);

    displayProperty.forEach((column,proIndex) => {
      let td = document.createElement("td");
      if (column.dataType == "string") {
        td.innerText = dataOb[column.propertyName]; //string from object
      }

      if (column.dataType == "function") {
        td.innerHTML = column.propertyName(dataOb); // function call
      }
        if (column.dataType == "image") {
            let img = document.createElement("img");
            img.style.width="25px";
            img.style.height="25px";
            img.style.borderRadius="100%";
            img.style.border="2px solid rgba(30, 58, 95,0.4)"
            if(dataOb[column.propertyName] != null){
                img.src= atob(dataOb[column.propertyName])
            }else{
                img.src=defaultImagePath;
            }

            td.appendChild(img);
        }
      if (column.dataType == "boolean") {
        userstatus = dataOb[column.propertyName];
        if (userstatus == true) {
          td.innerHTML =
            '<p class="p-1 bg-info fw-bold text-center">' + "Working" + "</p>";
        } else {
          td.innerHTML =
            '<p class="p-1 bg-warning fw-bold text-center">' +
            "Resign" +
            "</p>";
        }
      }
      tr.appendChild(td);
    });
    //column



    tr.onclick = () => {
      setColorForTableRow(tableBody, index, "pink");
      setTimeout(() => {
        tr.classList.remove("d-none");
        window['editOb'] = dataOb;
        editFunctionName(dataOb);
      }, 100);
    }



    //tr append into tbody
    tableBody.appendChild(tr);
  });
};

// define function for fill data into table
const fillDataIntoInnerTable = (
    tableBody,
    dataList,
    displayProperty,
    editFunctionName,
    deleteFunctionName,
    buttonVisibility = true
) => {
    tableBody.innerHTML = "";

    dataList.forEach((dataOb, index) => {
        //tr

        let tr = document.createElement("tr");
        //td-8
        let tdIndex = document.createElement("td");
        tdIndex.innerText = index + 1;
        tr.appendChild(tdIndex);

        displayProperty.forEach((column) => {
            let td = document.createElement("td");
            if (column.dataType == "string") {
                td.innerText = dataOb[column.propertyName]; //string from object
            }

            if (column.dataType == "function") {
                td.innerHTML = column.propertyName(dataOb); // function call
            }
            if (column.dataType == "boolean") {
                userstatus = dataOb[column.propertyName];
                if (userstatus == true) {
                    td.innerHTML =
                        '<p class="p-1 bg-info fw-bold text-center">' + "Working" + "</p>";
                } else {
                    td.innerHTML =
                        '<p class="p-1 bg-warning fw-bold text-center">' +
                        "Resign" +
                        "</p>";
                }
            }
            tr.appendChild(td);
        });

        let tdButton = document.createElement("td");

        let buttonEdit = document.createElement("button");
        buttonEdit.type="button";
        buttonEdit.className = "btn me-1";
        buttonEdit.innerHTML = '<i class="fa-solid fa-pen-to-square" style="color: #ffa600;"></i>';
        buttonEdit.onclick = () => {
            console.log("Edit");
            setColorForTableRow(tableBody, index, "lightyellow");
            // window.confirm("Are You sure to Edit Employee?");
            //for (const trelement of tableBody.children) {
            //trelement.removeAttribute("style");
            //}
            //tableBody.children[index].style.backgroundColor = "lightblue";

            setTimeout(() => {
                editFunctionName(dataOb);
            }, 100);
        };

        let buttonDelete = document.createElement("button");
        // buttonDelete.className = "btn btn-outline-danger fw-bold me-1";
        buttonDelete.type="button";
        buttonDelete.className = "btn me-1";
        buttonDelete.innerHTML = ' <i class="fa-solid fa-trash" style="color: rgb(255, 0, 0);"></i>';
        buttonDelete.onclick = () => {
            console.log("Delete");
            // window.confirm("Are You sure to Delete Employee?");
            setColorForTableRow(tableBody, index, "pink");

            // for (const trelement of tableBody.children) {
            //trelement.removeAttribute("style");
            //}
            //tableBody.children[index].style.backgroundColor = "pink";
            //tableBody.children[index].style.border = "5px dotted orange";
            //to get raw color before confirm message. there is no error on above line. so program excetue next line after that.so ww have to run following method
            setTimeout(() => {
                // call delete function name parameter
                deleteFunctionName(dataOb);
            }, 100);
        };
        if (buttonVisibility) {
            tdButton.appendChild(buttonEdit);
            tdButton.appendChild(buttonDelete);
            //td append into tr

            tr.appendChild(tdButton);
        }
        //tr append into tbody
        tableBody.appendChild(tr);
    });
};


// define function for fill data into table without update
const fillDataIntoInnerTableWithoutUpdate = (
    tableBody,
    dataList,
    displayProperty,
    deleteFunctionName,
    buttonVisibility = true
) => {
    tableBody.innerHTML = "";

    dataList.forEach((dataOb, index) => {
        //tr

        let tr = document.createElement("tr");
        //td-8
        let tdIndex = document.createElement("td");
        tdIndex.innerText = index + 1;
        tr.appendChild(tdIndex);

        displayProperty.forEach((column) => {
            let td = document.createElement("td");
            if (column.dataType == "string") {
                td.innerText = dataOb[column.propertyName]; //string from object
            }

            if (column.dataType == "function") {
                td.innerHTML = column.propertyName(dataOb); // function call
            }
            if (column.dataType == "boolean") {
                userstatus = dataOb[column.propertyName];
                if (userstatus == true) {
                    td.innerHTML =
                        '<p class="p-1 bg-info fw-bold text-center">' + "Working" + "</p>";
                } else {
                    td.innerHTML =
                        '<p class="p-1 bg-warning fw-bold text-center">' +
                        "Resign" +
                        "</p>";
                }
            }
            tr.appendChild(td);
        });

        let tdButton = document.createElement("td");

        let buttonDelete = document.createElement("button");
        // buttonDelete.className = "btn btn-outline-danger fw-bold me-1";
        buttonDelete.type="button";
        buttonDelete.className = "btn me-1";
        buttonDelete.innerHTML = ' <i class="fa-solid fa-trash" style="color: rgb(255, 0, 0);"></i>';
        buttonDelete.onclick = () => {
            console.log("Delete");
            // window.confirm("Are You sure to Delete Employee?");
            setColorForTableRow(tableBody, index, "pink");

            // for (const trelement of tableBody.children) {
            //trelement.removeAttribute("style");
            //}
            //tableBody.children[index].style.backgroundColor = "pink";
            //tableBody.children[index].style.border = "5px dotted orange";
            //to get raw color before confirm message. there is no error on above line. so program excetue next line after that.so ww have to run following method
            setTimeout(() => {
                // call delete function name parameter
                deleteFunctionName(dataOb);
            }, 100);
        };
        if (buttonVisibility) {
            tdButton.appendChild(buttonDelete);
            //td append into tr

            tr.appendChild(tdButton);
        }
        //tr append into tbody
        tableBody.appendChild(tr);
    });
};

// function to normalize Sri Lankan mobile number as 10  number character
const setMobileNo = (mobile) => {
    // +94713935982
    // 0713935982
    // 713935982
    mobile = mobile.trim();

    if (mobile.startsWith("+94")) {
        mobile = "0"+mobile.substring(3);
    }
    if (mobile.startsWith("7")) {
        mobile = "0"+mobile;
    }
    return mobile;
};

const validateFile=(element, object, property, previewid)=>{
    console.log(element.files)
    let file= element.files[0];
    let fileReader= new FileReader();
    fileReader.onload=(event)=>{
        previewid.src = event.target.result;
        object[property]= btoa(event.target.result);
    }
    fileReader.readAsDataURL(file);

}

// define function for fill data into table
const fillDataIntoTableInfo = (
    tableBody,
    dataList,
    displayProperty
) => {
    tableBody.innerHTML = "";

    dataList.forEach((dataOb, index) => {
        //tr

        let tr = document.createElement("tr");
        //td-8
        let tdIndex = document.createElement("td");
        tdIndex.innerText = index + 1;
        tr.appendChild(tdIndex);

        displayProperty.forEach((column) => {
            let td = document.createElement("td");
            if (column.dataType == "string") {
                td.innerText = dataOb[column.propertyName]; //string from object
            }

            if (column.dataType == "function") {
                td.innerHTML = column.propertyName(dataOb); // function call
            }
            if (column.dataType == "boolean") {
                userstatus = dataOb[column.propertyName];
                if (userstatus == true) {
                    td.innerHTML =
                        '<p class="p-1 bg-info fw-bold text-center">' + "Working" + "</p>";
                } else {
                    td.innerHTML =
                        '<p class="p-1 bg-warning fw-bold text-center">' +
                        "Resign" +
                        "</p>";
                }
            }
            tr.appendChild(td);
        });
        //tr append into tbody
        tableBody.appendChild(tr);
    });
};

const fillDataIntoBooksTable = (tableBodyElement, dataList, displayProperty, viewFunction, reserveFunction ,buttonVisibility=true) => {

    tableBodyElement.innerHTML = "";
    dataList.forEach((dataOb, index) => {
        let tr = document.createElement("tr");


        let tdNo = document.createElement("td");
        tdNo.innerText = parseInt(index) + 1;
        tr.appendChild(tdNo);

        displayProperty.forEach(displaypro => {
            let td = document.createElement("td");

            if (displaypro.dataType === "string") {
                td.innerText = dataOb[displaypro.propertyName];
            }
            if (displaypro.dataType === "function") {
                td.innerHTML = displaypro.propertyName(dataOb);
            }
            if (displaypro.dataType === "date") {
                td.innerHTML = displaypro.propertyName(dataOb);
            }
            tr.appendChild(td);
        })

        let tdAction = document.createElement("td");


        let buttonView = document.createElement("button");
        buttonView.innerHTML = '<i class="fa-solid fa-eye fa-lg" style="color: rgb(116, 192, 252);"></i>'
        buttonView.className="form-btn me-2 p-1"
        tdAction.appendChild(buttonView);
        buttonView.onclick = () => {
            console.log("View", dataOb);
            viewFunction(dataOb);
        }


        let buttonReserve = document.createElement("button");
        buttonReserve.innerHTML = '<i class="fa-solid fa-bookmark fa-lg" style="color: rgb(116, 192, 252);"></i>'
        buttonReserve.className="form-btn p-1 m-1"
        tdAction.appendChild(buttonReserve);
        buttonReserve.onclick = () => {
            console.log("Reserve", dataOb);
            reserveFunction(dataOb);
        }

        // there are no edit , delete, print buttons in some forms so defined a function for visibility
        // fiilDataIntoTable buttonVisibility=true for display buttons when refresh
        // can remove buttons from each forms js files by enter false
        if (buttonVisibility) {
            tr.appendChild(tdAction);
        }

        tableBodyElement.appendChild(tr);

    });
}

