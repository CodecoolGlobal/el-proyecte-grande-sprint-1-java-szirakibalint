initBuyButton();

function initBuyButton() {
    const buyButton = document.querySelector('#sell-button');
    buyButton.addEventListener('click', handleSellEvent);
}

async function handleSellEvent(clickEvent) {
    const inputField = document.querySelector('#amount-input');
    const stringArray = window.location.href.split("/");
    const coinId = stringArray[stringArray.length - 2];
    await fetch(`/api/coins/${coinId}`, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'},
        method: 'PUT',
        body: JSON.stringify({"amount": parseFloat(inputField.value)})
    })
}