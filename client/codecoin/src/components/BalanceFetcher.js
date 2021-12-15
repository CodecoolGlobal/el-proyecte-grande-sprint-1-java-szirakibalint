import {useEffect, useState} from "react";

function BalanceFetcher(id) {
    const [balance, setBalance] = useState([])
    useEffect(() => {
        const getBalance = async () => {
            const totalBalanceFromAPI = await fetchBalance()
            setBalance(totalBalanceFromAPI)
        }
        getBalance();
    }, [])

    const fetchBalance = async () => {
        if (id === '') {
            const res = await fetch(`/api/portfolio/total-balance`)
            return await res.json()
        }
    }
    return  balance
}

export default BalanceFetcher