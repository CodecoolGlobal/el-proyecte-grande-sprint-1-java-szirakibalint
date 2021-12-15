import {useEffect, useState} from "react";

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
        if (id === '') {
            res = await fetch(`/api/coins`)
        } else {
            res = await fetch(`/api/coins/${id}`)
        }
        try {
            return await res.json()
        } catch (e) {
            return [];
        }
    }
    return coin;
}

export default CoinFetcher;