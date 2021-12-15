import CardModule from "../styles/Card.css"
import {Link} from "react-router-dom";

function Card(props) {
    const {id, name, symbol, image, current_price, price_change_percentage_24h, amount} = props.coin;

    return (
        <div className={"currency"}>
            <Link to={"/coins/" + id}><p className={"name"}>{name}</p></Link>
            <p className={"symbol"}>{amount ? `${amount} ${symbol}` : symbol}</p>
            <img className={"currency-image"} src={image} alt="currency"/>
            <p className={"current-price"}>{amount ? amount * current_price : current_price}</p>
            <p className={"price-change-percentage-24h"}>{price_change_percentage_24h}</p>
        </div>
    )
}

export default Card;
