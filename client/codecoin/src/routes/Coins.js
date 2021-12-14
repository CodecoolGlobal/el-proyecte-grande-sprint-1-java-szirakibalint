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
        const res = await fetch(`/api/coins`)
        return await res.json()
    }
    return (
        <>
            <div>{coins.map((coin) => (
                <div>
                    <p>{coin.name}</p>
                </div>))}
            </div>
        </>
    )
}

export default Coins;