$(document).ready(function () {

    let getStorage = []
    let htmlStorage = null
    const count = $('.count')
    let countQty = +count.text()
// PAGE CARD
    const pageCard = $('#page-card')
    const sendGetStorage = $('#send-get-storage')

    sendGetStorage.val(localStorage.getItem("card"))
    getStorage = JSON.parse(localStorage.getItem("card"))
        ? JSON.parse(localStorage.getItem("card"))
        : getStorage

    htmlStorage = getStorage.map(item => {
        return (
            `<div class="item-card">
        <div class="title">${item.title}</div>
        <div class="price">${item.price}</div>
        <div class="qty"><input type="number" min="1" max="99" onkeypress="return false" value="${item.qty}" /></div>
        <button class="remove-from-card" data-id="${item.id}">Remove from card</button>
      </div>`
        )
    })

    pageCard.html(htmlStorage)

// REMOVE PRODUCT FROM CARD


    const removeFromCardBtn = $('.remove-from-card')

    const removeFromCard = function () {
        const _this = $(this)
        const id = _this.data('id')
        getStorage = JSON.parse(localStorage.getItem("card"))

        let newGetStorage = getStorage.filter(item => item.id != id)
        localStorage.setItem('card', JSON.stringify(newGetStorage))
        countQty--
        count.text(countQty)

        _this.parents('.item-card').css('display', 'none')

        sendGetStorage.val(localStorage.getItem("card"))
    }

    $('#page-card').on('click', '.remove-from-card', removeFromCard)

    const changeValue = function () {
        const _this = $(this)
        const newQty = +_this.val()
        const id = _this.parents('.item-card').find('.remove-from-card').data('id')

        getStorage = JSON.parse(localStorage.getItem("card"))
        let newGetStorage = getStorage.map(item => item.id == id ? {...item, qty: newQty} : item)
        localStorage.setItem('card', JSON.stringify(newGetStorage))
        sendGetStorage.val(localStorage.getItem("card"))
    }

    $(".qty input[type='number']").on('change', changeValue);

});