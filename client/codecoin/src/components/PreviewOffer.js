import {useEffect, useState} from "react";

function PreviewOffer(props) {
    const coinId = props.coin.id;
    const coinName = props.coin.name;
    const buyOrSell = props.buyOrSell;
    const [coin, setCoin] = useState([])
    const [amount, setAmount] = useState(0);
    const [visible, setVisible] = useState(false);
    const[remainingSecs, setRemainingSecs] = useState(7);
    const fetchCoin = async () => {
        const res = await fetch(`/api/coins/${coinId}`)
        return await res.json()
    }
    const getCoin = async () => {
        const coinFromAPI = await fetchCoin()
        setCoin(coinFromAPI)
    }
    useEffect(() => {
        getCoin();
    }, [])
    function getPreview() {
        const inputField = document.querySelector('#amount-input');
        if (inputField.value !== '') {
            getCoin();
            setAmount(parseFloat(inputField.value));
            setVisible(true);
            let seconds = 6;
            const count = setInterval(() => {
                if (seconds === 0) {
                    setRemainingSecs(7);
                    clearInterval(count);
                } else {
                    setRemainingSecs(seconds);
                    seconds--;
                }
            }, 1000)
            clearInterval(document.timer);
            document.timer = setInterval(() => {
                setVisible(false);
                clearInterval(document.timer);
            }, 7000);
        }
    }

    async function handleTransaction() {
        if (buyOrSell === "buy") {
            await handleBuy();
        } else if (buyOrSell === "sell") {
            await handleSell();
        }
    }

    async function handleBuy() {
        await fetch(`/api/coins/${coinId}`, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: 'POST',
            body: JSON.stringify({"amount": amount})
        })
        window.location.reload();
    }

    async function handleSell() {
        await fetch(`/api/coins/${coinId}`, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: 'PUT',
            body: JSON.stringify({"amount": amount})
        })
        window.location.reload();
    }

    function cancelTransaction() {
        setVisible(false);
        clearInterval(document.timer);
    }

    return (
        <>
            <div className="transaction">
                <label htmlFor="amount-input">How much {coinName} you want to {buyOrSell}?</label>
                <input id="amount-input" type="text" name="amount"/>
                <button onClick={getPreview} id="offer-button" className="button">Preview</button>
            </div>
            { visible &&
            (<div className="preview-offer">
                    {buyOrSell === "buy" &&
                        (<p>You can get {amount} {coin.name} for {amount * coin.current_price} USD. Do you take it?</p>)
                    || (<p>You can get {amount * coin.current_price} USD for {amount} {coin.name}. Do you take it? {remainingSecs}s</p>)}
                <button onClick={handleTransaction} id="transaction-button" className="button">Take it!</button>
                <button onClick={cancelTransaction} id="cancel-button" className="button">Cancel</button>
            </div>)
            }
        </>

    )
}

export default PreviewOffer;