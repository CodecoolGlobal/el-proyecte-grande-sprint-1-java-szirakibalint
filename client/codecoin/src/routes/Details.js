import {useEffect, useState} from "react";
import {useParams} from "react-router-dom";

function Details() {
    const [coin, setCoin] = useState([])
    let { id } = useParams();
    useEffect(() => {
        const getCoin = async () => {
            const coinFromAPI = await fetchCoin()
            setCoin(coinFromAPI)
        }
        getCoin();
        console.log(coin.name)
    }, [])

    const fetchCoin = async () => {
        const res = await fetch(`https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&ids=${id}`)
        return await res.json()
    }
    return (
        <>
            <div>{coin.map((coin) => (
                <div>
                    <p>{coin.name}</p>
                    <p>{coin.current_price}</p>
                    <p>{coin.high_24h}</p>
                    <p>{coin.low_24h}</p>
                </div>))}
            </div>
        </>
    )
}

export default Details;