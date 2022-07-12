$(document).ready(function () {
    let bodyTag = $('body');
    let idData = bodyTag.data('id');
    let nameData = bodyTag.data('name');
    let typeData = bodyTag.data('type');
    let hardDiskData = bodyTag.data('hard-disk');
    let hullData = bodyTag.data('hull');
    let keyboardData = bodyTag.data('keyboard');
    let monitorData = bodyTag.data('monitor');
    let motherboardDta = bodyTag.data('motherboard');
    let mouseData = bodyTag.data('mouse');
    let powerSupplyData = bodyTag.data('power-supply');
    let processorData = bodyTag.data('processor');
    let ramData = bodyTag.data('ram');
    let speakerData = bodyTag.data('speaker');
    let coolerData = bodyTag.data('cooler');
    let videoCardData = bodyTag.data('video-card');
    let ventilatorData = bodyTag.data('ventilator');
    let priceData = bodyTag.data('price');
    let descriptionData = bodyTag.data('description');
    let searchData = bodyTag.data('search');
    let editData = bodyTag.data('edit');
    let deleteData = bodyTag.data('delete');
    let createData = bodyTag.data('create');
    let anyData = bodyTag.data('any');
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

    let table = $('#devices_table').DataTable({
        language: {
            jsonUrl
        },
        dom: '<"toolbar">tipr',
        processing: true,
        serverSide: true,
        ordering: false,
        ajax: {
            url: path + '/controller?command=get_devices',
            data: function (data) {
                data.filterCriteria = $('#searchCriteria').val();
                data.requestType = 'DATATABLE';
            }
        },

        columns: [
            {data: 'entityId'},
            {
                data: 'type',
                render: function (data) {
                    if (data === "HARD_DISK") {
                        return "<p>" + hardDiskData + "</p>";
                    } else if (data === "HULL") {
                        return "<p>" + hullData + "</p>";
                    } else if (data === "KEYBOARD") {
                        return "<p>" + keyboardData + "</p>";
                    } else if (data === "MONITOR") {
                        return "<p>" + monitorData + "</p>";
                    } else if (data === "MOTHERBOARD") {
                        return "<p>" + motherBoardData + "</p>";
                    } else if (data === "MOUSE") {
                        return "<p>" + mouseData + "</p>";
                    } else if (data === "POWER_SUPPLY") {
                        return "<p>" + powerSupplyData + "</p>";
                    } else if (data === "PROCESSOR") {
                        return "<p>" + processorData + "</p>";
                    } else if (data === "RAM") {
                        return "<p>" + ramData + "</p>";
                    } else if (data === "SPEAKER") {
                        return "<p>" + speakerData + "</p>";
                    } else if (data === "VIDEOCARD") {
                        return "<p>" + videoCardData + "</p>";
                    } else if (data === "COOLER") {
                        return "<p>" + coolerData + "</p>";
                    } else {
                        return "<p>" + ventilatorData + "</p>";
                    }
                }
            },
            {data: 'name'},
            {
                data: 'picturePath',
                render: function (data) {
                    return '<img src="' + data + '" alt="Device picture" width="auto" height="80" class="img-thumbnail">'
                }
            },
            {data: 'description'},
            {data: 'price'},

            {
                data: null,
                render: function (row) {
                    return '<a href= "'+ path + '/controller?command=go_to_edit_device_page&id=' + row.entityId + '" type="button" class="btn btn-outline-primary me-1">'
                        + editData + '</a>'
                        + '<a href= "'+ path + '/controller?command=delete_device&id=' + row.entityId + '" type="button" class="btn btn-outline-primary me-1">'
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
                    <option value="TYPE">${typeData}</option>
                    <option value="PRICE">${priceData}</option>
                    <option value="DESCRIPTION">${descriptionData}</option>                   
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
            window.location.href = path + "/controller?command=go_to_create_device_page";
        });

        searchInput.keyup(function () {
            table.search(searchInput.val().trim()).draw();
        });

        searchCriteria.change(function () {
            searchInput.val('');
            searchSelect.html('');
            if (searchCriteria.val() === 'TYPE') {
                table.search(searchInput.val()).draw();
                searchInput.hide();
                searchSelect.show();
                searchSelect.html('')
                    .append($("<option></option>").attr("value", "").text(anyData))
                    .append($("<option></option>").attr("value", "HARD_DISK").text(hardDiskData))
                    .append($("<option></option>").attr("value", "HULL").text(hullData))
                    .append($("<option></option>").attr("value", "KEYBOARD").text(keyboardData))
                    .append($("<option></option>").attr("value", "MONITOR").text(monitorData))
                    .append($("<option></option>").attr("value", "MOTHERBOARD").text(motherboardDta))
                    .append($("<option></option>").attr("value", "MOUSE").text(mouseData))
                    .append($("<option></option>").attr("value", "POWER_SUPPLY").text(powerSupplyData))
                    .append($("<option></option>").attr("value", "PROCESSOR").text(processorData))
                    .append($("<option></option>").attr("value", "RAM").text(ramData))
                    .append($("<option></option>").attr("value", "SPEAKER").text(speakerData))
                    .append($("<option></option>").attr("value", "VIDEOCARD").text(videoCardData))
                    .append($("<option></option>").attr("value", "COOLER").text(coolerData))
                    .append($("<option></option>").attr("value", "VENTILATOR").text(ventilatorData))
                searchSelect.select('destroy');
            } else {
                table.search(searchInput.val()).draw();
                searchInput.show();
                searchSelect.hide();
            }
        });

        searchSelect.change(function () {
            table.search(searchSelect.val()).draw();
        });
    }

});
