$(document).ready(function () {
    let bodyTag = $('body');
    let orderedData = bodyTag.data('ordered');
    let inProgressData = bodyTag.data('in-progress');
    let completedData = bodyTag.data('completed');
    let moreDetailedData = bodyTag.data('more-detailed');
    let path = bodyTag.data('path');
    let footer = $('footer');
    let locale = footer.data('locale');

    localeChange($('#localeSelect'), locale);


    let jsonUrl;
    if (locale === 'en_US') {
        jsonUrl = 'https://cdn.datatables.net/plug-ins/1.11.1/i18n/en-gb.json'
    } else if (locale === 'ru_RU') {
        jsonUrl = 'https://cdn.datatables.net/plug-ins/1.11.1/i18n/ru.json'
    }

    let table = $('#my_orders_table').DataTable({
        language: {
            jsonUrl
        },
        dom: '<"toolbar">tipr',
        processing: true,
        serverSide: true,
        ordering: false,
        ajax: {
            url: path + '/controller?command=get_orders',
            data: function (data) {
                data.filterCriteria = 'ID_USER';
                data.requestType = 'DATATABLE';
                data.activeOrder = false;
            }
        },
        columns: [
            {data: 'entityId'},
            {data: 'userId'},
            {
                data: 'orderStatus',
                render: function (data) {
                    if (data === "ORDERED") {
                        return "<p>" + orderedData + "</p>";
                    } else if (data === "IN_PROGRESS") {
                        return "<p>" + inProgressData + "</p>";
                    } else {
                        return "<p>" + completedData + "</p>";
                    }
                }
            },
            {
                data: null,
                render: function (row) {
                    return '<a href="'+ path +'/controller?command=go_to_order_page&id='
                        + row.entityId + '" type="button" class="btn btn-outline-primary me-1">'
                        + moreDetailedData + '</a>'
                }
            },
        ],
        initComplete: function () {
            onDataTableInitComplete(table);
        }
    });

    function onDataTableInitComplete(table) {
        table.search(userId).draw();
    }

});