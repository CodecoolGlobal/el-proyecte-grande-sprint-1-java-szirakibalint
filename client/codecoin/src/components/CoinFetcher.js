import {useEffect, useState} from "react";
import Error from "../routes/Error";

function CoinFetcher(id) {
    const [coin, setCoin] = useState([])
    useEffect(() => {
        const getCoin = async () => {
            const coinFromAPI = await fetchCoin()
            setCoin(coinFromAPI)
        }
        getCoin();
    }, [])

    const fetchCoin = async () => {
        let res
        try {
        if (id === '') {
            res = await fetch(`/api/coins`)
        } else {
            res = await fetch(`/api/coins/${id}`)
        }
            return await res.json()
        } catch (e) {
            if (id === '') {
                return Error(`Error: failed to fetch coin data from API endpoint /api/coins/`);
            } else {
                return Error(`Error: failed to fetch coin data from API endpoint /api/coins/${id}`);
            }
        }
    }
    return coin;
}

export default CoinFetcher;