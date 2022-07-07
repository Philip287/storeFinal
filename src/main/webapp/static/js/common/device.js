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
    let addToOrderData = bodyTag.data('add-to-order');

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

    deviceSelect.select2({
        language: locale.substring(0, 2),
        placeholder: devices,
        theme: 'bootstrap',
        width: '100%',
        maximumInputLength: 50,
        ajax: {
            delay: 250,
            url: '/controller?command=get_devices',
            data: function (params) {
                return {
                    term: params.term || '',
                    page: params.page || 1,
                    pageSize: 10,
                    requestType: 'SELECT'
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

});
