import PortfolioFetcher from "../components/PortfolioFetcher";
import CoinFetcher from "../components/CoinFetcher";
import Card from "../components/Card";
import Loading from "../components/Loading";
import React from "react";

function Portfolio() {
    const {portfolio, totalBalance} = PortfolioFetcher();
    const coins = CoinFetcher('');
    const portfolioCoins = [];

    if (coins === undefined || coins.length === 0 || !portfolio) {
        return (
            <div className="loading-container">
                <Loading></Loading>
            </div>
        )
    }

    if (portfolio) {
        const {cryptoCurrencies} = portfolio;
        if (cryptoCurrencies) {
            const portfolioCoinIds = Object.keys(cryptoCurrencies);
            for (let coin of coins) {
                if (portfolioCoinIds.includes(coin.id)) {
                    portfolioCoins.push({...coin, "amount": cryptoCurrencies[coin.id]});
                }
            }
        }
    }

    return (
        <>
            <div className="header">
                <p className="label">Total balance:</p>
                <h1>{totalBalance} USD</h1>
            </div>
            <div className="currencies">
                {portfolioCoins ? portfolioCoins.map((coin) => (<Card coin={coin}/>)) : null}
            </div>
        </>
    )
}

export default Portfolio