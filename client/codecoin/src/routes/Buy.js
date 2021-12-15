import {useParams} from "react-router-dom";
import CoinFetcher from "../components/CoinFetcher"
import PortfolioFetcher from "../components/PortfolioFetcher";

function Buy() {

    let { id } = useParams();
    const coin = CoinFetcher(id);
    const portfolio = PortfolioFetcher();
    async function handleBuy() {
        const inputField = document.querySelector('#amount-input');
        await fetch(`/api/coins/${id}`, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: 'POST',
            body: JSON.stringify({"amount": parseFloat(inputField.value)})
        })
    }
    return (
            <>
                <div className="header">
                    <img src={coin.image} alt="symbol"/>
                        <h1>{coin.name}</h1>
                </div>
                <div className="subheader">
                    <h3>Balance: {portfolio.portfolio !== undefined && portfolio.portfolio.currencies !== undefined && portfolio.portfolio.currencies.USD || 0} USD</h3>
                    <h3>{coin.name} balance: {portfolio.portfolio !== undefined && portfolio.portfolio.cryptoCurrencies !== undefined && portfolio.portfolio.cryptoCurrencies[coin.name] || 0} {coin.symbol}</h3>
                    <h3>1 {coin.symbol} = {coin.current_price} USD</h3>
                </div>
                <div className="transaction">
                    <label htmlFor="amount-input">How much {coin.name} you want to buy?</label>
                    <input id="amount-input" type="text" name="amount"/>
                        <form className="transaction-form">
                            <button onClick={handleBuy} id="buy-button" className="button" type="submit">Buy</button>
                        </form>
                </div>
            </>
    )
}

export default Buy