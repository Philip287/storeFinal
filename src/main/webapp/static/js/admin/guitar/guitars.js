$(document).ready( function () {
    let bodyTag = $('body');
    let idData = bodyTag.data('id');
    let nameData = bodyTag.data('name');
    let bodyData = bodyTag.data('body');
    let neckData = bodyTag.data('neck');
    let pickupData = bodyTag.data('pickup');
    let neckJointData = bodyTag.data('neck-joint');
    let boltOnData = bodyTag.data('bolt-on');
    let setNeckData = bodyTag.data('set-neck');
    let neckThroughData = bodyTag.data('neck-through');
    let colorData = bodyTag.data('color');
    let userData = bodyTag.data('user');
    let orderStatusData = bodyTag.data('order-status');
    let orderedData = bodyTag.data('ordered');
    let inProgressData = bodyTag.data('in-progress');
    let completedData = bodyTag.data('completed');
    let searchData = bodyTag.data('search');
    let editData = bodyTag.data('edit');
    let deleteData = bodyTag.data('delete');
    let createData = bodyTag.data('create');
    let anyData = bodyTag.data('any');

    let footer = $('footer');
    let locale = footer.data('locale');
    localeChange($('#localeSelect'), locale);

    let jsonUrl;
    if (locale === 'en_US') {
        jsonUrl = 'https://cdn.datatables.net/plug-ins/1.11.1/i18n/en-gb.json'
    } else if (locale === 'ru_RU') {
        jsonUrl = 'https://cdn.datatables.net/plug-ins/1.11.1/i18n/ru.json'
    }

    let table = $('#guitars_table').DataTable( {
        language: {
            jsonUrl
        },
        dom: '<"toolbar">tipr',
            processing: true,
            serverSide: true,
            ordering: false,
            ajax: {
            url: '/controller?command=get_guitars',
                data: function (data) {
                data.filterCriteria = $('#searchCriteria').val();
                data.requestType = 'DATATABLE';
                data.activeOrder = false;
            }
        },
        drawCallback: function () { onDataLoaded(table); },
        columns: [
            { data: 'entityId'},
            { data: 'name'},
            {
                data: 'picturePath',
                render: function (data) {
                    return '<img src="'+ data +'" alt="Guitar picture" width="auto" height="80" class="img-thumbnail">'
                }
            },
            {
                data: null,
                render: function (row) {
                    return '<a href="/controller?command=go_to_edit_body_page&id=' + row.bodyId + '" class="text-decoration-none"></a>'
                }
            },
            {
                data: null,
                render: function (row) {
                    return '<a href="/controller?command=go_to_edit_neck_page&id=' + row.neckId + '" class="text-decoration-none"></a>'
                }
            },
            {
                data: null,
                render: function (row) {
                    return '<a href="/controller?command=go_to_edit_pickup_page&id=' + row.pickupId + '" class="text-decoration-none"></a>'
                }
            },
            {
                data: 'neckJoint',
                render: function (data) {
                    if (data === "BOLT_ON") {
                        return "<p>" + boltOnData + "</p>";
                    }
                    else if (data === "SET_NECK") {
                        return "<p>" + setNeckData + "</p>";
                    } else {
                        return "<p>" + neckThroughData + "</p>";
                    }
                }
            },
            { data: 'color'},
            {
                data: null,
                render: function (row) {
                    return '<a href="/controller?command=go_to_edit_user_page&id=' + row.userId + '" class="text-decoration-none"></a>'
                }
            },
            {
                data: 'orderStatus',
                render: function (data) {
                    if (data === "ORDERED") {
                        return "<p>" + orderedData + "</p>";
                    }
                    else if (data === "IN_PROGRESS") {
                        return "<p>" + inProgressData + "</p>";
                    } else {
                        return "<p>" + completedData + "</p>";
                    }
                }
            },
            {
                data: null,
                render: function (row) {
                    return '<a href="/controller?command=go_to_edit_guitar_page&id=' + row.entityId + '" type="button" class="btn btn-outline-primary me-1">'
                        + editData + '</a>'
                        + '<a href="/controller?command=delete_guitar&id=' + row.entityId + '" type="button" class="btn btn-outline-primary me-1">'
                        + deleteData + '</a>'
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
                    <option value="NAME">${nameData}</option>
                    <option value="BODY_ID">${bodyData}</option>
                    <option value="NECK_ID">${neckData}</option>
                    <option value="PICKUP_ID">${pickupData}</option>
                    <option value="NECK_JOINT">${neckJointData}</option>
                    <option value="COLOR">${colorData}</option>
                    <option value="USER_ID">${userData}</option>
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

        $('#createButton').click(function () {
            window.location.href = "/controller?command=go_to_create_guitar_page";
        });

        searchInput.keyup(function () {
            table.search(searchInput.val().trim()).draw();
        });

        searchCriteria.change(function () {
            searchInput.val('');
            searchSelect.html('');
            if (searchCriteria.val() === 'BODY_ID') {
                searchInput.hide();
                searchSelect.show();
                searchSelect.select2({
                    language: locale.substring(0, 2),
                    placeholder: bodyData,
                    theme: 'bootstrap',
                    width: '71%',
                    maximumInputLength: 30,
                    ajax: {
                        delay: 250,
                        url: '/controller?command=get_bodies',
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
            } else if (searchCriteria.val() === 'NECK_ID') {
                searchInput.hide();
                searchSelect.show();
                searchSelect.select2({
                    language: locale.substring(0, 2),
                    placeholder: neckData,
                    theme: 'bootstrap',
                    width: '71%',
                    maximumInputLength: 30,
                    ajax: {
                        delay: 250,
                        url: '/controller?command=get_necks',
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
            } else if (searchCriteria.val() === 'PICKUP_ID') {
                searchInput.hide();
                searchSelect.show();
                searchSelect.select2({
                    language: locale.substring(0, 2),
                    placeholder: pickupData,
                    theme: 'bootstrap',
                    width: '71%',
                    maximumInputLength: 30,
                    ajax: {
                        delay: 250,
                        url: '/controller?command=get_pickups',
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
            } else if (searchCriteria.val() === 'USER_ID') {
                searchInput.hide();
                searchSelect.show();
                searchSelect.select2({
                    language: locale.substring(0, 2),
                    placeholder: userData,
                    theme: 'bootstrap',
                    width: '71%',
                    maximumInputLength: 30,
                    ajax: {
                        delay: 250,
                        url: '/controller?command=get_users',
                        data: function (params) {
                            return {
                                term: params.term || '',
                                page: params.page || 1,
                                pageSize: 10,
                                requestType: 'SELECT',
                                filterCriteria: 'LOGIN'
                            }
                        },
                        processResults: function (data, params) {
                            data = JSON.parse(data);
                            let mappedData = $.map(data.results, function (item) {
                                item.id = item.entityId;
                                item.text = item.login;
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
            } else if (searchCriteria.val() === 'NECK_JOINT') {
                table.search(searchInput.val()).draw();
                searchInput.hide();
                searchSelect.show();
                searchSelect.html('')
                    .append($("<option></option>").attr("value", "").text(anyData))
                    .append($("<option></option>").attr("value", "BOLT_ON").text(boltOnData))
                    .append($("<option></option>").attr("value", "SET_NECK").text(setNeckData))
                    .append($("<option></option>").attr("value", "NECK_THROUGH").text(neckThroughData))
                searchSelect.select2('destroy');
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
            } else {
                table.search(searchInput.val()).draw();
                searchInput.show();
                searchSelect.hide();
                searchSelect.select2('destroy');
            }
        });

        searchSelect.change(function () {
            table.search(searchSelect.val()).draw();
        });

        searchSelect.on('select2:select', function () {
            let searchValue = $(this).val();
            table.search(searchValue).draw();
        });
    }

    function onDataLoaded(table) {
        table.rows().data().each(function (value, index) {
            fetchBody(value.bodyId, function (entity) {
                let bodyName = entity.name;
                let cell = table.cell(index, 3).node();
                $(cell).find('a').text(bodyName);
            });
        });

        table.rows().data().each(function (value, index) {
            fetchNeck(value.neckId, function (entity) {
                let neckName = entity.name;
                let cell = table.cell(index, 4).node();
                $(cell).find('a').text(neckName);
            });
        });

        table.rows().data().each(function (value, index) {
            fetchPickup(value.pickupId, function (entity) {
                let pickupName = entity.name;
                let cell = table.cell(index, 5).node();
                $(cell).find('a').text(pickupName);
            });
        });

        table.rows().data().each(function (value, index) {
            fetchUser(value.userId, function (entity) {
                let userName = entity.login;
                let cell = table.cell(index, 8).node();
                $(cell).find('a').text(userName);
            });
        });
    }
});