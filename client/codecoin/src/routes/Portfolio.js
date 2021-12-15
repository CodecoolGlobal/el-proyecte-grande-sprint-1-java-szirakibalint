import {useEffect, useState} from "react";

function Portfolio() {
    const [portfolio, setPortfolio] = useState([])
    const [totalBalance, setTotalBalance] = useState([])
    useEffect(() => {
        const getPortfolio = async () => {
            const portfolioFromAPI = await fetchPortfolio()
            const totalBalanceFromAPI = await getTotalBalance()
            setPortfolio(portfolioFromAPI)
            setTotalBalance(totalBalanceFromAPI.totalBalance)
        }
        getPortfolio();
    }, [])

    const fetchPortfolio = async () => {
        const res = await fetch(`/api/portfolio`)
        return await res.json()
    }

    const getTotalBalance = async () => {
        const res = await fetch(`/api/portfolio/total-balance`)
        return await res.json()
    }

    return (
        <>
            <div className="header">
                <p className="label">Total balance:</p>
                <h1>{totalBalance} USD</h1>
            </div>
            <div className="portfolio-content">
                {portfolio.cryptoCurrencies !== undefined && Object.keys(portfolio.cryptoCurrencies).map((key) => (
                <div className="portfolio-card">
                    <p>{key} : {portfolio.cryptoCurrencies[key]}</p>
                </div>))}
            </div>
        </>
    )
}

export default Portfolio