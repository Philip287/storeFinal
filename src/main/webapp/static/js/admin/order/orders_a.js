$(document).ready(function () {
    let bodyTag = $('body');
    let searchData = bodyTag.data('search');
    let idData = bodyTag.data('id');
    let userData = bodyTag.data('user');
    let orderStatusData = bodyTag.data('order-status');
    let orderedData = bodyTag.data('ordered');
    let inProgressData = bodyTag.data('in-progress');
    let completedData = bodyTag.data('completed');
    let moreDetailedData = bodyTag.data('more-detailed');
    let deleteData = bodyTag.data('delete');
    let createData = bodyTag.data('create');
    let anyData = bodyTag.data('any');
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

    let table = $('#orders_table').DataTable({
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
                data.filterCriteria = $('#searchCriteria').val();
                data.requestType = 'DATATABLE';
                data.activeOrder = false;
            }
        },
        drawCallback: function () {
            onDataLoaded(table);
        },
        columns: [
            {data: 'entityId'},
            {
                data: null,
                render: function (row) {
                    return '<a href="/Gradle___com_suprun___store_1_0_SNAPSHOT_war/controller?command=go_to_edit_user_page&id=' + row.userId + '" class="text-decoration-none"></a>'
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
                    console.log(row)
                    return '<a href="/Gradle___com_suprun___store_1_0_SNAPSHOT_war/controller?command=go_to_order_page&id=' + row.entityId + '" type="button" class="btn btn-outline-primary me-1">'
                        + moreDetailedData + '</a>'
                        + '<a href="/Gradle___com_suprun___store_1_0_SNAPSHOT_war/controller?command=delete_order&id=' + row.entityId + '" type="button" class="btn btn-outline-primary me-1">'
                        + deleteData + '</a>'
                        + '<a href="/Gradle___com_suprun___store_1_0_SNAPSHOT_war/controller?command=update_order&id=' + row.entityId + '&orderStatus=ORDERED&userId=' + row.userId  + '" type="button" class="btn btn-outline-primary me-1">'
                        + orderedData + '</a>'
                        + '<a href="/Gradle___com_suprun___store_1_0_SNAPSHOT_war/controller?command=update_order&id=' + row.entityId + '&orderStatus=IN_PROGRESS&userId=' +  row.userId + '" type="button" class="btn btn-outline-primary me-1">'
                        + inProgressData + '</a>'
                        + '<a href="/Gradle___com_suprun___store_1_0_SNAPSHOT_war/controller?command=update_order&id=' + row.entityId + '&orderStatus=COMPLETED&userId=' + row.userId  + '" type="button" class="btn btn-outline-primary me-1">'
                        + completedData + '</a>'
                }
            },
        ],
        initComplete: function () {
            onDataTableInitComplete(table);
        }
    });

    function onDataTableInitComplete(table) {
        $("div.toolbar").html(`
            <div class="input-group input-group-sm mb-2">
                <button id="createButton" type="button" class="btn btn-secondary">
                    ${createData}
                </button>
                <select id="searchCriteria" class="form-select input-sm">
                    <option value="ID">${idData}</option>        
                    <option value="LOGIN">${userData}</option>
                    <option value="ORDER_STATUS">${orderStatusData}</option>
                </select>
                <input id="searchInput" maxlength="50" type="text" class="form-control w-50" placeholder=${searchData}>
                <select id="searchSelect" class="form-select w-50"></select>
            </div>
        `);

        let searchInput = $('#searchInput');
        let searchCriteria = $('#searchCriteria');
        let searchSelect = $('#searchSelect');

        searchSelect.hide();


        searchInput.keyup(function () {
            table.search(searchInput.val().trim()).draw();
        });

        searchCriteria.change(function () {
            searchInput.val('');
            searchSelect.html('');
            if (searchCriteria.val() === 'LOGIN') {
                searchInput.show();
                searchSelect.hide();
                searchSelect.select2({
                    language: locale.substring(0, 2),
                    placeholder: bodyTag,
                    theme: 'bootstrap',
                    width: '71%',
                    maximumInputLength: 30,
                    ajax: {
                        delay: 250,
                        url: rew + '/controller?command=get_users',
                        data: function (params) {
                            return {
                                term: params.term || '',
                                page: params.page || 1,
                                pageSize: 10,
                                requestType: 'SELECT',
                                filterCriteria: 'NAME'
                            }
                        },
                        processResults: function (data, params) {
                            data = JSON.parse(data);
                            let mappedData = $.map(data.results, function (item) {
                                item.id = item.entityId;
                                item.text = item.name;
                                return item;
                            });
                            params.page = params.page || 1;

                            return {
                                results: mappedData,
                                pagination: {
                                    more: data.paginationMore
                                }
                            }
                        }
                    }
                });
                table.search(searchInput.val()).draw();
            } else if (searchCriteria.val() === 'ORDER_STATUS') {
                table.search(searchInput.val()).draw();
                searchInput.hide();
                searchSelect.show();
                searchSelect.html('')
                    .append($("<option></option>").attr("value", "").text(anyData))
                    .append($("<option></option>").attr("value", "ORDERED").text(orderedData))
                    .append($("<option></option>").attr("value", "IN_PROGRESS").text(inProgressData))
                    .append($("<option></option>").attr("value", "COMPLETED").text(completedData))
                searchSelect.select2('destroy');
            }  else {
                searchInput.val("");
                searchInput.show();
                searchSelect.hide();
                table.search(searchInput.val()).draw();
            }
        });

        searchSelect.change(function () {
            table.search(searchSelect.val()).draw();
        });

        searchSelect.on('select2:select', function () {
            let searchValue = $(this).val();
            table.search(searchValue).draw();
        });

        searchSelect.on('select2:select', function () {
            let searchValue = $(this).val();
            table.search(searchValue).draw();
        });
    }

    function onDataLoaded(table) {

        table.rows().data().each(function (value, index) {
            fetchUser(value.userId, function (entity) {
                let userName = entity.login;
                let cell = table.cell(index, 1).node();
               $(cell).find('a').text(userName);
            });
        });
    }
});