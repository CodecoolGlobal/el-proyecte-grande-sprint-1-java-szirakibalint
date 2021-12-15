import {useParams} from "react-router-dom";
import CoinFetcher from "../components/CoinFetcher";
import Button from "../components/Button";
import {Link} from "react-router-dom";
import CoinDetailsModule from "../styles/CoinDetails.css"

function Details() {
    let { id } = useParams();
    const coin = CoinFetcher(id);
    return (
        <>
            <div className={"header"}>
                <img src={coin.image} className={"detailed-img"} alt=""/>
                <h1>{coin.name}</h1>
            </div>

        </>
    )
}

export default Details;