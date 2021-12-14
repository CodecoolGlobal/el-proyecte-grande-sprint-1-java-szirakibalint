import {useEffect, useState} from "react";
import {Link} from "react-router-dom";

function Portfolio() {
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

    return (
        <>
            <div className="header">
                <p className="label">Total balance:</p>
                <h1>0 USD</h1>
            </div>
            <div className="portfolio-content">
                {portfolio.cryptoCurrencies !== undefined && Object.keys(portfolio.cryptoCurrencies).map((key) => (
                <div className="portfolio-card">
                    <p><Link to={`/coins/${key.id}`}>{key.name}</Link></p>
                    <p>{key.symbol}</p>
                    <img src={key.image}/>
                        <p>portfolio.cryptoCurrencies.key</p>
                </div>))}
            </div>
        </>
    )
}

export default Portfolio