const basicText = document.getElementById("text");
const texts = ["\xa0tracking\xa0your\xa0success", "\xa0making\xa0your\xa0life\xa0easier", "\xa0helping\xa0your\xa0finance"];
let textIndex = 0;
let index = 0;
let currentText = getNextText();

function getNextText() {
    textIndex++;
    if (textIndex === texts.length) {
        textIndex = 0;
    }
    return texts[textIndex];
}

function writeText() {
    if (index < currentText.length) {
        basicText.innerText += currentText[index];
        index++;
        setTimeout(writeText, 150);
    }
    else {
        deleteText();
    }
}

function deleteText() {
    basicText.innerText = "";
    index = 0;
    currentText = getNextText();
    writeText();
}

writeText();