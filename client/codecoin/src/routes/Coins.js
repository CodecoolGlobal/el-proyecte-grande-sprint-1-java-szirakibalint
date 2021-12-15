import CoinFetcher from "../components/CoinFetcher";
import Card from "../components/Card";
import CoinsModule from "../styles/Coins.css"

function Coins() {
    const coins = CoinFetcher('');
    return (
        <>
            <dic className="header"><h1>Currencies</h1></dic>
            <div className={"currencies"}>{coins.map((coin) => (
                <Card coin={coin}/>
                ))}
            </div>
        </>
    )
}

export default Coins;