let rew = "/Gradle___com_suprun___store_1_0_SNAPSHOT_war";

function fetchOrder(id, callback, async = true) {
    $.ajax({
        method: 'GET',
        url: rew + '/controller?command=get_orders',
        data: {
            id: id,
            requestType: 'FETCH'
        },
        async: async,
        success: function (response) {
            let data = JSON.parse(response);

            if (data) {
                callback(data.entity);
            }
        }
    });
}

function fetchDevice(id, callback, async = true) {
    $.ajax({
        method: 'GET',
        url: rew + '/controller?command=get_devices',
        data: {
            id: id,
            requestType: 'FETCH'
        },
        async: async,
        success: function (response) {
            let data = JSON.parse(response);

            if (data) {
                callback(data.entity);
            }
        }
    });
}


function fetchUser(id, callback, async = true) {
    $.ajax({
        method: 'GET',
        url: rew + '/controller?command=get_users',
        data: {
            id: id,
            requestType: 'FETCH'
        },
        async: async,
        success: function (response) {
            let data = JSON.parse(response);
            if (data) {
                callback(data.entity);
            }
        }
    });
}

function fetchDeviceHasOrder(id, callback, async = true) {
    $.ajax({
        method: 'GET',
        url: rew + '/controller?command=get_devices_has_orders',
        data: {
            id: id,
            requestType: 'FETCH'
        },
        async: async,
        success: function (response) {
            let data = JSON.parse(response);
            console.log(data);
            if (data) {
                callback(data.entity);
            }
        }
    });
}

function fetchDeviceHasOrderSelect(id, callback, async = true) {
    $.ajax({
        method: 'GET',
        url: rew + '/controller?command=get_devices_has_orders',
        data: {
            id: id,
            requestType: 'SELECT'
        },
        async: async,
        success: function (response) {
            let data = JSON.parse(response);
            console.log(data);
            if (data) {
                callback(data.entity);
            }
        }
    });
}
