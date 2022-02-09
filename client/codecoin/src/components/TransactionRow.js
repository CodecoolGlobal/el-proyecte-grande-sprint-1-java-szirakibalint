import React from 'react';

const TransactionRow = (props) => {
    const {date, type, cryptoId, cryptoAmount, currency, currencyAmount} = props.transaction;

    return (
        <tr>
            <td>{cryptoId}</td>
            <td>{cryptoAmount}</td>
            <td>{type}</td>
            <td>{currency}</td>
            <td>{currencyAmount}</td>
            <td>{date}</td>
        </tr>
    )
}

export default TransactionRow;