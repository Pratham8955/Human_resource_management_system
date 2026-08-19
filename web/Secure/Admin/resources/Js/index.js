/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function ExportEmpData() {
    window.location.href = "exportemp.fin";
}


function CurrentDatePickerforIntermediateLeave() {
    var fromDateInput = document.getElementById("fromDate");
    var today = new Date();
    var tomorrow = new Date(today);
    tomorrow.setDate(today.getDate() + 1);
    var dd = String(tomorrow.getDate()).padStart(2, '0');
    var mm = String(tomorrow.getMonth() + 1).padStart(2, '0');
    var yyyy = tomorrow.getFullYear();
    var tomorrowDate = yyyy + '-' + mm + '-' + dd;
    fromDateInput.min = today.toISOString().split('T')[0];
    fromDateInput.max = tomorrowDate;
}


function TomorrowDatePickerforIntermediateLeave() {
    var toDateInput = document.getElementById("toDate");
    var today = new Date();
    var tomorrow = new Date(today);
    tomorrow.setDate(today.getDate() + 1);
    var dd = String(tomorrow.getDate()).padStart(2, '0');
    var mm = String(tomorrow.getMonth() + 1).padStart(2, '0');
    var yyyy = tomorrow.getFullYear();
    var tomorrowDate = yyyy + '-' + mm + '-' + dd;
    toDateInput.min = today.toISOString().split('T')[0];
    toDateInput.max = tomorrowDate;
}