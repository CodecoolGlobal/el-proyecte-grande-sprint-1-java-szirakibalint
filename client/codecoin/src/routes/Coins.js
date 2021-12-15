import CoinFetcher from "../components/CoinFetcher";

function Coins() {
    const coins = CoinFetcher('');
    return (
        <>
            <div>{coins.map((coin) => (
                <div>
                    <p>{coin.name}</p>
                </div>))}
            </div>
        </>
    )
}

export default Coins;