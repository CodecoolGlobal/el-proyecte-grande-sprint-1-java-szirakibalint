initCards();

function initCards() {
    const cards = document.querySelectorAll('.currency, .portfolio-card');
    for (let i = 0; i < cards.length; i++) {
        const card = cards[i];
        card.addEventListener('click', handleCardClick)
    }
}

function handleCardClick(clickEvent) {
    const card = clickEvent.currentTarget;
    const otherVariable = card.querySelector('a');
    otherVariable.click();
}