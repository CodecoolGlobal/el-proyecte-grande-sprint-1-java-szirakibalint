import CoinFetcher from "../components/CoinFetcher";
import Card from "../components/Card";
import CoinsModule from "../styles/Coins.css"
import Error from "./Error";

function Coins() {
    const coins = CoinFetcher('');
    if (coins.length === 1 && coins[0]["error"] !== undefined) {
        return Error(coins[0]["error"]);
    }
    return (
        <>
            <dic className="header"><h1>Currencies</h1></dic>
            <div className={"currencies"}>{coins.map((coin) => (
                <Card coin={coin}></Card>
                ))}
            </div>
        </>
    )
}

export default Coins;