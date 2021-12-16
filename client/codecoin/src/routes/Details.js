import {useParams} from "react-router-dom";
import CoinFetcher from "../components/CoinFetcher";
import Button from "../components/Button";
import {Link} from "react-router-dom";
import CoinDetailsModule from "../styles/CoinDetails.css"
import React from "react";
import Loading from "../components/Loading";

function Details() {
    let { id } = useParams();
    const coin = CoinFetcher(id);
    if (coin === undefined || coin.length === 0) {
        return (
            <>
                <div className="loading-container">
                    <Loading />
                </div>
            </>
        )
    }
    else if (React.isValidElement(coin)) {
        return coin;
    } else {
        return (
            <>
                <div className={"header"}>
                    <img src={coin.image} className={"detailed-img"} alt=""/>
                    <h1>{coin.name}</h1>
                </div>
                <div className="action">
                    <Link to={'/coins/' + coin.id + '/buy'}><Button className={"button transaction-button buy-button"} text={"Buy"}/></Link>
                    <Link to={'/coins/' + coin.id + '/sell'}><Button className={"button transaction-button sell-button"} text={"Sell"}/></Link>
                </div>
                <div className="content">
                    <div className="details">
                        <h3 id="details">Details</h3>
                        <p className="tag">Name:</p>
                        <p className="text">{coin.name}</p>
                        <p className="tag">Symbol:</p>
                        <p className="text">{coin.symbol}</p>
                        <p className="tag">Last update:</p>
                        <p className="text">{coin.last_update}</p>
                    </div>
                    <div className="profile">
                        <h3 id="profile">Profile</h3>
                        <p className="tag">Price:</p>
                        <p className="text">{coin.current_price}</p>
                        <p className="tag">Low 24 hour:</p>
                        <p className="text">{coin.low_24h}</p>
                        <p className="tag">High 24 hour:</p>
                        <p className="text">{coin.high_24h}</p>
                        <p className="tag">Price percentage change in 24 hour</p>
                        <p className="text">{coin.price_change_percentage_24h}</p>
                    </div>
                </div>
            </>
        )
    }
}

export default Details;