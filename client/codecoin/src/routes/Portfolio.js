import PortfolioFetcher from "../components/PortfolioFetcher";
import CoinFetcher from "../components/CoinFetcher";
import Card from "../components/Card";

function Portfolio() {
    const fullPortfolio = PortfolioFetcher();
    const portfolio = fullPortfolio.portfolio;
    const totalBalance = fullPortfolio.totalBalance;

    const coins = CoinFetcher('');

    const portfolioHasCoins = (portfolio) && (portfolio.cryptoCurrencies);
    const portfolioCoins = [];

    if (portfolioHasCoins) {
        const currencies = portfolio.cryptoCurrencies;
        const portfolioCoinIds = Object.keys(currencies);
        for (let coin of coins) {
            if (portfolioCoinIds.includes(coin.id)) {
                portfolioCoins.push(
                    {...coin,
                    "amount": currencies[coin.id]
                    });
            }
        }
    }

    return (
        <>
            <div className="header">
                <p className="label">Total balance:</p>
                <h1>{totalBalance} USD</h1>
            </div>
            <div className="portfolio-content">
                {portfolioHasCoins ? portfolioCoins.map((coin) => (<Card coin={coin}/>)) : null}
            </div>
        </>
    )
}

export default Portfolio