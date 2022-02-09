import React from 'react';
import useTransactions from "../components/useTransactions";
import Loading from "../components/Loading";
import TransactionRow from "../components/TransactionRow";
import "../styles/TransactionHistory.css"
import {Link} from "react-router-dom";

const Transactions = () => {
    const transactions = useTransactions();
    if (transactions.length === 0) {
        return (
            <>
                <div className="header">
                    <h1>Transaction history</h1>
                </div>
                <div className="loading-container">
                    <Loading/>
                </div>
            </>
        )
    }
    else if (transactions === "empty") {
        return (
            <>
            <div className={"transaction-empty-container"}>
                <h1>You haven't made any transaction yet.</h1>
                <p>You can go and check the newest currencies below</p>
                <button className={"button btn-contained"}><Link to="/coins">See currencies</Link></button>
            </div>
            </>
        )
    }
    else if (React.isValidElement(transactions)) {
        return transactions;
    }
    else {
        return (
            <>
                <div className="header">
                    <h1>Transaction history</h1>
                </div>
                <div className="transaction-history-table-container">
                <table>
                    <thead>
                    <tr >
                        <th>Crypto</th>
                        <th>Crypto Amount</th>
                        <th>Transaction Type</th>
                        <th>Currency</th>
                        <th>Currency Amount</th>
                        <th>Date</th>
                    </tr>
                    </thead>
                    <tbody>
                        {transactions.map((transaction) => <TransactionRow transaction={transaction}/>)}
                    </tbody>
                </table>
                </div>
            </>
        )
    }
}

export default Transactions;
