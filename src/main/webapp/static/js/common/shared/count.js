$(document).ready(function () {
    let count = $('.count')
    let countQty = +count.text()

    let newrew = localStorage.getItem("card") ? JSON.parse(localStorage.getItem("card")) : ''
    countQty = newrew ? newrew.length : 0
    count.text(countQty)

    $('#orders_table tbody tr').each(function() {
        let price = $(this).find('td:nth-child(3)').text()
        console.log(price)
    })

});