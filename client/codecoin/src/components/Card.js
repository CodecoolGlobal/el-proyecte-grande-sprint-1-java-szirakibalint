import CardModule from "../styles/Card.css"
import {Link} from "react-router-dom";

function Card(props) {
    const coin = props.coin;

    return (
        <div className={"currency"}>
            <Link to={"/coins/" + coin.id}><p className={"name"}>{coin.name}</p></Link>
            <p className={"symbol"}>{coin.symbol}</p>
            <img className={"currency-image"} src={coin.image} alt="currency"/>
            <p className={"current-price"}>{coin.current_price}</p>
            <p className={"price-change-percentage-24h"}>{coin.price_change_percentage_24h}</p>
        </div>
    )
}

export default Card;
