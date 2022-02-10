import React, {useEffect, useState} from 'react';
import Error from "../routes/Error";

function useTransactions() {
    const [transactions, setTransactions] = useState([])
    useEffect(async () => {
        const transactionsFromAPI = await fetchTransactions()
        setTransactions(transactionsFromAPI)
    }, []);
    const userId = sessionStorage.getItem("user-id")

    const fetchTransactions = async () => {
        try {
            const jwt = sessionStorage.getItem("jwt");
            const res = await fetch(
                `/api/transactions/${userId}`,
                {
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${jwt}`
                    }
                })
            let data = await res.json()
            data = data.length === 0 ? "empty" : data
            return data
        } catch (e) {
            return Error(`Error: failed to fetch transaction history from API endpoint /api/transactions/${userId}`);
        }
    }
    return transactions;
}

export default useTransactions;
