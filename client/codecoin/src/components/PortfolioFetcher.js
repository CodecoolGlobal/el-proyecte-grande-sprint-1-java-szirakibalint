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
        const res = await fetch(`/api/portfolio`)
        return await res.json()
    }
}

export default PortfolioFetcher