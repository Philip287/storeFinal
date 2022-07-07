$(document).ready(function () {
    let bodyTag = $('body');
    let orderedData = bodyTag.data('ordered');
    let inProgressData = bodyTag.data('in-progress');
    let completedData = bodyTag.data('completed');
    let moreDetailedData = bodyTag.data('more-detailed');
    let footer = $('footer');
    let locale = footer.data('locale');
    localeChange($('#localeSelect'), locale);

    let rew = "/Gradle___com_suprun___store_1_0_SNAPSHOT_war";
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
            url: rew + '/controller?command=get_orders',
            data: function (data) {
                data.filterCriteria = 'ID_USER';
                data.requestType = 'DATATABLE';
                data.activeOrder = false;
            }
        },
        drawCallback: function () {
            onDataLoaded(table);
        },
        columns: [
            {data: 'entityId'},
            {data: 'userId'},
            {
                data: null,
                render: function (row) {
                    return '<a href="/Gradle___com_suprun___store_1_0_SNAPSHOT_war/controller?command=go_to_edit_user_page&id='
                        + row.userId + '" class="text-decoration-none"></a>'
                }
            },
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
                    return '<a href="/Gradle___com_suprun___store_1_0_SNAPSHOT_war/controller?command=go_to_order_page&id='
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

    function onDataLoaded(table) {
        table.rows().data().each(function (value, index) {
            fetchUser(value.userId, function (entity) {
                let userName = entity.login;
                let cell = table.cell(index, 2).node();
                $(cell).find('a').text(userName);
            });
        });
    }
});