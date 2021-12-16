import CoinFetcher from "../components/CoinFetcher";
import Card from "../components/Card";
import CoinsModule from "../styles/Coins.css"
import React from "react";
import Loading from "../components/Loading";

function Coins() {
    const coins = CoinFetcher('');
    if (coins.length === 0) {
        return (
            <>
                <div className="header"><h1>Currencies</h1></div>
                <div className="loading-container">
                    <Loading></Loading>
                </div>
            </>
        )
    }
    else if (React.isValidElement(coins)) {
        return coins;
    } else {
        return (
            <>
                <div className="header"><h1>Currencies</h1></div>
                <div className={"currencies"}>{coins.map((coin) => (
                    <Card coin={coin}></Card>
                    ))}
                </div>
            </>
        )
    }
}

export default Coins;
