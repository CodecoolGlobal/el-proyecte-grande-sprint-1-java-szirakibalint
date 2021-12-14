import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";

function Buy() {

    const [coin, setCoin] = useState([])
    let { id } = useParams();
    useEffect(() => {
        const getCoin = async () => {
            const coinFromAPI = await fetchCoin()
            setCoin(coinFromAPI)
        }
        getCoin();
    }, [])

    const fetchCoin = async () => {
        const res = await fetch(`/api/coins/${id}`)
        return await res.json()
    }
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
                    <label htmlFor="amount-input">How much {coin.name} you want to buy?</label>
                    <input id="amount-input" type="text" name="amount"/>
                        <form className="transaction-form">
                            <button id="buy-button" className="button" type="submit">Buy</button>
                        </form>
                </div>
            </>
    )
}

export default Buy