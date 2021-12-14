import {useParams} from "react-router-dom";
import CoinFetcher from "../components/CoinFetcher";

function Details() {
    let { id } = useParams();
    const coin = CoinFetcher(id);
    return (
        <>
            <div>
                    <p>{coin.name}</p>
                    <p>{coin.current_price}</p>
                    <p>{coin.high_24h}</p>
                    <p>{coin.low_24h}</p>
            </div>
        </>
    )
}

export default Details;