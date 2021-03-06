var ajaxUrl = 'ajax/employees/';
var datatableApi;

function updateTable() {
    $.ajax({
        type: "get",
        url: "ajax/departments/"+departId+"/employees",
        success: updateTableByData
    });
}

/*function updateTable() {
    $.ajax({
        type: "post",
        url: ajaxUrl+"filter",
        data: $('#filter').serialize(),

        success: updateTableByData
    });
}*/
function updateTable2() {
/*    data.add("departmentid",departId);*/
    $.ajax({
        type: "post",
        url: ajaxUrl+"filter",
        data: $('#filter').serialize(),

        success: updateTableByData
    });
}

$(function () {
    datatableApi = $('#datatable').DataTable({
        "ajax": {

/*            "url": ajaxUrl+"filtered?departmentid="+departId,*/
            url: "ajax/departments/"+departId+"/employees",
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "fullName"
            },

            {
                "data": "birthDay"

            },
            {
                "data": "salary"
            },


            {
                "defaultContent": "",
                "orderable": false,
                "render": renderEditBtn
            },
            {
                "defaultContent": "",
                "orderable": false,
                "render": renderDeleteBtn

            }
        ],
        "order": [
            [
                0,
                "desc"
            ]
        ],
        "createdRow": function (row, data, dataIndex) {
            $(row).addClass('normal');
        },

        "initComplete": makeEditable
    });




});
