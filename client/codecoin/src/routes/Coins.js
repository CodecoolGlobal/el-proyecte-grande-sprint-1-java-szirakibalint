import CoinFetcher from "../components/CoinFetcher";
import Card from "../components/Card";
import CoinsModule from "../styles/Coins.css"
import React from "react";

function Coins() {
    const coins = CoinFetcher('');
    if (React.isValidElement(coins)) {
        return coins;
    } else {
        return (
            <>
                <dic className="header"><h1>Currencies</h1></dic>
                <div className={"currencies"}>{coins.map((coin) => (
                    <Card coin={coin}></Card>
                    ))}
                </div>
            </>
        )
    }
}

export default Coins;