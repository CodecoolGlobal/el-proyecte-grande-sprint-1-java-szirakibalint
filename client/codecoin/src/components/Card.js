import CardModule from "../styles/Card.css"
import {Link} from "react-router-dom";

function Card(props) {
    return (
        <div className={"currency"}>
            <Link to={"/coins/" + props.coin.id}><p className={"name"}>{props.coin.name}</p></Link>
            <p className={"symbol"}>{props.coin.symbol}</p>
            <img className={"currency-image"} src={props.coin.image} alt="currency"/>
            <p className={"amount"}>{props.coin.current_price}</p>
            <p className={"price-change-percentage-24h"}>{props.coin.price_change_percentage_24h}</p>
        </div>
    )
}

export default Card;
