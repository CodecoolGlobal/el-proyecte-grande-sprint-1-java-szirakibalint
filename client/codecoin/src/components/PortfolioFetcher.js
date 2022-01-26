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
        const res = await fetch(`/api/portfolio/${sessionStorage.getItem("user-id")}`)
        return await res.json()
    }
    return portfolio;
}

export default PortfolioFetcher