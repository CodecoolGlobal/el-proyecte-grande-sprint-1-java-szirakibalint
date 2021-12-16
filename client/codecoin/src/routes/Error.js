import {Link} from "react-router-dom";

function Error(message) {
    message = isNaN(message) ? "Oops, something went wrong." : message;
    return (
        <>
        <img src="https://i.postimg.cc/k5zT5dmN/logo-broken.png" width="800px" alt="logo"/>
            <h1 id="error-message">{message}</h1>
            <h1>You can report the issue or simply go back to the main page.</h1>
            <Link to="/coins">
                <button className="button">Report the issue!</button>
            </Link>
            <Link to="/coins">
                <button className="button">Go to homepage!</button>
            </Link>
            </>
    )
}

export default Error