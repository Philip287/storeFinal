$(document).ready(function () {

    let userID = $('body').data('user-id');
    let getStorage = [];
    let getStorage2 = [];
    let htmlStorage = null;
    const count = $('.count');
    let countQty = +count.text();

    let price = $('body').data('price');
    let remove = $('body').data('remove');
    let quantity = $('body').data('quantity');

// PAGE CARD
    const pageCard = $('#page-card')
    const sendGetStorage = $('#send-get-storage')
    let jsonCard = `{"order":{"orderStatus":"ORDERED","userId":${userID}},"deviceHasOrderList":`

    let cardParse = localStorage.getItem("card") ? JSON.parse(localStorage.getItem("card")) : ''
    let cardParseMap = []
    let allPrice = 0

    cardParse.forEach(function sumPrice(el) {
        allPrice += +el.price * el.number
    })
    console.log(allPrice)
    $('.card-price').text(allPrice)

    cardParseMap = cardParse ? cardParse.map(el => ({deviceId: +el.deviceId, number: +el.number})) : ''
    console.log(cardParse)
    console.log(cardParseMap)

    localStorage.setItem('cardParseMap', JSON.stringify(cardParseMap));

    if (localStorage.getItem("cardParseMap") === '') {
        sendGetStorage.val('0')
    } else {
        sendGetStorage.val(jsonCard + localStorage.getItem("cardParseMap") + '}')
    }

    getStorage = localStorage.getItem("card")
        ? JSON.parse(localStorage.getItem("card"))
        : getStorage

    htmlStorage = getStorage.map(item => {
        return (
            `<div 
                class="item-card" 
                style="border: 1px solid #6f42c1; 
                margin-bottom: 20px;
                border: 1px solid #6f42c1;
                margin-bottom: 20px;
                padding: 30px;
                box-sizing: border-box;
                display: flex;
                align-items: center;
                justify-content: space-between;">
        <div class="title">${item.title}</div>
        <div 
            style="width:90px;
            height: 90px; 
            object-fit: contain;
            object-position: center;">
            <img style="width:90px;
            height: 90px; 
            object-fit: contain;
            object-position: center;" src="${item.picturePath}" alt="">
            </div>
        <div class="price">${price} ${item.price}</div>
        <div class="qty">${quantity}: 
        <input type="number" min="1" max="99" onkeypress="return false" value="${item.number}" /></div>
        <button class="remove-from-card" data-id="${item.deviceId}">${remove}</button>
      </div>`
        )
    })

    pageCard.html(htmlStorage)

// REMOVE PRODUCT FROM CARD


    const removeFromCardBtn = $('.remove-from-card')

    const removeFromCard = function () {
        const _this = $(this)
        const id = _this.data('id')
        getStorage = JSON.parse(localStorage.getItem("cardParseMap"))
        getStorage2 = JSON.parse(localStorage.getItem("card"))

        let newGetStorage = getStorage.filter(item => item.deviceId != id)
        let newGetStorage2 = getStorage2.filter(item => item.deviceId != id)

        localStorage.setItem('cardParseMap', JSON.stringify(newGetStorage))
        localStorage.setItem('card', JSON.stringify(newGetStorage2))

        countQty--
        count.text(countQty)

        _this.parents('.item-card').css('display', 'none')

        sendGetStorage.val(jsonCard + localStorage.getItem("cardParseMap") + '}')

        if (countQty == 0) {
            $('.send-submit-card').prop('disabled', true)
        } else {
            $('.send-submit-card').prop('disabled', false)
        }

        let allPrice = 0
        JSON.parse(localStorage.getItem("card")).forEach(function sumPrice(el) {
            allPrice += +el.price * el.number
        })
        console.log(allPrice)
        $('.card-price').text(allPrice)

    }

    $('#page-card').on('click', '.remove-from-card', removeFromCard)

    const changeValue = function () {
        const _this = $(this)
        const newQty = +_this.val()
        const id = _this.parents('.item-card').find('.remove-from-card').data('id')

        getStorage = JSON.parse(localStorage.getItem("cardParseMap"))
        getStorage2 = JSON.parse(localStorage.getItem("card"))

        let newGetStorage = getStorage.map(item => item.deviceId == id ? {...item, number: newQty} : item)
        let newGetStorage2 = getStorage2.map(item => item.deviceId == id ? {...item, number: newQty} : item)

        localStorage.setItem('cardParseMap', JSON.stringify(newGetStorage))
        localStorage.setItem('card', JSON.stringify(newGetStorage2))

        sendGetStorage.val(jsonCard + localStorage.getItem("cardParseMap") + '}')
        let allPrice = 0
        JSON.parse(localStorage.getItem("card")).forEach(function sumPrice(el) {
            allPrice += +el.price * el.number
        })
        $('.card-price').text(allPrice)
    }

    $(".qty input[type='number']").on('change', changeValue);

    $('.send-submit-card').on('click', function () {
        localStorage.setItem('cardParseMap', '')
        localStorage.setItem('card', '')
    })

    if (JSON.parse(localStorage.getItem("cardParseMap")).length == 0) {
        $('.send-submit-card').prop('disabled', true)
    } else {
        $('.send-submit-card').prop('disabled', false)
    }

});