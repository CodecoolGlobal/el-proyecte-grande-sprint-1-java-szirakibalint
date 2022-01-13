import {useParams} from "react-router-dom";
import CoinFetcher from "../components/CoinFetcher";
import PortfolioFetcher from "../components/PortfolioFetcher";
import PreviewOffer from "../components/PreviewOffer";
import Loading from "../components/Loading";
import React from "react";

function Sell() {

    let { id } = useParams();
    const coin = CoinFetcher(id);
    const portfolio = PortfolioFetcher();

    const secondaryContainer = {
        marginLeft: "4rem",
    }

    if (coin === undefined || coin.length === 0) {
        return (
            <div className="loading-container">
                <Loading></Loading>
            </div>
        )
    }

    return (
        <>
                <div className="header">
                    <img src={coin.image} alt="symbol"/>
                    <h1>{coin.name}</h1>
                </div>
            <div className={"secondary-container"} style={secondaryContainer}>
                <div className="subheader">
                    <h3>Balance: {portfolio.portfolio !== undefined && portfolio.currencyBalance || 0} USD</h3>
                    <h3>{coin.name} balance: {portfolio.portfolio !== undefined && portfolio.portfolio.cryptoCurrencies !== undefined && portfolio.portfolio.cryptoCurrencies[coin.id] || 0} {coin.symbol}</h3>
                    <h3>1 {coin.symbol} = {coin.current_price} USD</h3>
                </div>
                <PreviewOffer buyOrSell="sell" coin={coin}/>
            </div>
        </>
    )
}

export default Sell