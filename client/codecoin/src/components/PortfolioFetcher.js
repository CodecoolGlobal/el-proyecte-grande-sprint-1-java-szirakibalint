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

    // TODO change id from 1

    const fetchPortfolio = async () => {
        const res = await fetch(`/api/portfolio/1`)
        return await res.json()
    }
    return portfolio;
}

export default PortfolioFetcher