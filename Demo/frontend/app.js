const HOST = "http://localhost:8080";

const loadStudent = () => {
var table = document.querySelector("#tbl");
while (table.rows.length > 1) {
  table.deleteRow(table.rows.length - 1);
}

  $.ajax({
    method: "get",
    url: `${HOST}/student/`
  })
    .done((response) => {

      var num = 1;
      for(const obj of response){
         var row = table.insertRow(num);
         row.className="tableBodyTR";
         var cell0 = row.insertCell(0);
         cell0.innerHTML = obj.studentID;
         cell0.className="tableBodyTD";
         var cell1 = row.insertCell(1);
         cell1.innerHTML = obj.firstName;
         cell1.className="tableBodyTD";
         var cell2 = row.insertCell(2);
         cell2.innerHTML = obj.obj;
         cell2.className="tableBodyTD";
         var cell3 = row.insertCell(3);
         cell3.innerHTML = obj.emailAddress;
         cell3.className="tableBodyTD";
         var cell4 = row.insertCell(4);
         cell4.innerHTML = obj.cellphoneNumber;
         cell4.className="tableBodyTD";
         num=num+1;
      }
    })
    .fail((obj, textStatus) => {
      if ((obj && obj.responseText)) {
        alert(obj.responseText.message);
      }
    });
};

const btnSearch = document.querySelector("#searchbtn");
btnSearch.addEventListener("click", loadStudent);
