import {useParams} from "react-router-dom";
import CoinFetcher from "../components/CoinFetcher";
import {useEffect} from "react";

function Sell() {

    let { id } = useParams();
    const coin = CoinFetcher(id);
    useEffect(() => {
        const button = document.querySelector('#sell-button');
        button.addEventListener('click', async () => {
            const inputField = document.querySelector('#amount-input');
            await fetch(`/api/coins/${id}`, {
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'},
                method: 'PUT',
                body: JSON.stringify({"amount": parseFloat(inputField.value)})
            })
        })
    }, []);
    return (
        <>
                <div className="header">
                    <img src={coin.image} alt="symbol"/>
                    <h1>{coin.name}</h1>
                </div>
                <div className="subheader">
                    <h3>Balance: 0 USD</h3>
                    <h3>{coin.name} balance: 0 {coin.symbol}</h3>
                    <h3>1 {coin.symbol} = {coin.current_price} USD</h3>
                </div>
                <div className="transaction">
                    <label htmlFor="amount-input">How much {coin.name} you want to sell?</label>
                    <input id="amount-input" type="text" name="amount"/>
                    <form className="transaction-form">
                        <button id="sell-button" className="button" type="submit">Sell</button>
                    </form>
                </div>
        </>
    )
}

export default Sell