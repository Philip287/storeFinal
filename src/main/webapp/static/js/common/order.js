$(document).ready(function () {
    let bodyTag = $('body');
    let orderId = bodyTag.data('orderid');
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

    let table = $('#orders_table').DataTable({
        language: {
            jsonUrl
        },
        dom: '<"toolbar">tipr',
        processing: true,
        serverSide: true,
        ordering: false,
        ajax: {
            url: path + '/controller?command=get_devices_has_orders',
            data: function (data) {
                data.filterCriteria = 'ID';
                data.requestType = 'DATATABLE';
                data.activeOrder = false;
            }
        },
        drawCallback: function () {
            onDataLoaded(table);
        },
        columns: [
            {data: 'orderId'},
            {
                data: null,
                render: function (row) {
                    return '<a href="' + path + '/controller?command=go_to_device_page&id=' + row.deviceId + '" class="text-decoration-none"></a>'
                }
            },
            {
                data: null,
                render: function (row) {
                    return '<span></span>'
                }
            },
            {data: 'number'},
        ],
        initComplete: function () {
            onDataTableInitComplete(table);
        }
    });

    function onDataTableInitComplete(table) {
        table.search(orderId).draw();
    }
    function onDataLoaded(table) {

        table.rows().data().each(function (value, index) {
            fetchDevice(value.deviceId, function (entity) {
                let name = entity.name;
                let price = entity.price;
                let cell = table.cell(index, 1).node();
                $(cell).find('a').text(name);
                cell = table.cell(index, 2).node();
                $(cell).find('span').text(price);

            });
        });
    }

});