initBuyButton();

function initBuyButton() {
    const buyButton = document.querySelector('#buy-button');
    buyButton.addEventListener('click', handleBuyEvent);
}

async function handleBuyEvent(clickEvent) {
    const inputField = document.querySelector('#amount-input');
    const stringArray = window.location.href.split("/");
    const coinId = stringArray[stringArray.length - 2];
    await fetch(`/api/coins/${coinId}`, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'},
        method: 'POST',
        body: JSON.stringify({"amount": parseFloat(inputField.value)})
    })
}