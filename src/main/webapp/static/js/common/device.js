$(document).ready(function () {

    let footer = $('footer');
    let locale = footer.data('locale');
    localeChange($('#localeSelect'), locale);

    // ADD TO CARD START
    const count = $('.count')
    let countQty = count.text()
    let newrew = null
    let getStorage = []

    const addToCard = function () {
        const _this = $(this)

        const userId = $('body').data('user-id')
        const id = $('body').data('entity-id')
        const price = $('body').data('price')
        const title = $('body').data('name')
        const picturePath = $('body').data('picture-path')
        const qty = 1

        const item = {deviceId: id, userId, title, price, number: qty, picturePath}

        getStorage = JSON.parse(localStorage.getItem("card"))
            ? JSON.parse(localStorage.getItem("card"))
            : getStorage

        localStorage.setItem('card', JSON.stringify([...getStorage, item]));
        countQty++
        count.text(countQty)
        _this.attr('disabled', true)
    }

    getStorage = JSON.parse(localStorage.getItem("card"))
        ? JSON.parse(localStorage.getItem("card"))
        : getStorage

    newrew = JSON.parse(localStorage.getItem("card"))
    countQty = newrew ? newrew.length : 0
    count.text(countQty)

    $('.add-to-card').on('click', addToCard)

});
