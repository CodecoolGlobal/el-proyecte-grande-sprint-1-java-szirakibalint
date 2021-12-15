import PortfolioFetcher from "../components/PortfolioFetcher";

function Portfolio() {
    const fullPortfolio = PortfolioFetcher();
    const portfolio = fullPortfolio.portfolio;
    const totalBalance = fullPortfolio.totalBalance;
    return (
        <>
            <div className="header">
                <p className="label">Total balance:</p>
                <h1>{totalBalance} USD</h1>
            </div>
            <div className="portfolio-content">
                {portfolio !== undefined && portfolio.cryptoCurrencies !== undefined && Object.keys(portfolio.cryptoCurrencies).map((key) => (
                <div className="portfolio-card">
                    <p>{key} : {portfolio.cryptoCurrencies[key]}</p>
                </div>))}
            </div>
        </>
    )
}

export default Portfolio