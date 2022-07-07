$(document).ready(function () {
    let count = $('.count')
    let countQty = +count.text()

    const newrew = JSON.parse(localStorage.getItem("card"))
    countQty = newrew ? newrew.length : 0
    count.text(countQty)

});