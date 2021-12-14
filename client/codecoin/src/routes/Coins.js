import DataHandler from "../components/DataHandler";
import {useEffect, useState} from "react";

function Coins() {
    const [coins, setCoins] = useState([])
    useEffect(() => {
        const getCoins = async () => {
            const coinsFromAPI = await fetchCoins()
            setCoins(coinsFromAPI)
        }
        getCoins();
    }, [])

    const fetchCoins = async () => {
        const res = await fetch(`https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd`)
        return await res.json()
    }
}

export default Coins;