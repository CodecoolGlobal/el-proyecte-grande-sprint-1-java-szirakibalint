import {useEffect, useState} from "react";

function PortfolioFetcher() {
    const [portfolio, setPortfolio] = useState([])
    useEffect(() => {
        const getPortfolio = async () => {
            const portfolioFromAPI = await fetchPortfolio()
            setPortfolio(portfolioFromAPI)
        }
        getPortfolio();
    }, [])

    const fetchPortfolio = async () => {
        const jwt = sessionStorage.getItem("jwt");
        const res = await fetch(
            `/api/portfolio/${sessionStorage.getItem("user-id")}`,
            {
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${jwt}`
                }
            })
        return await res.json()
    }
    return portfolio;
}

export default PortfolioFetcher