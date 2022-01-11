import {Link} from "react-router-dom";
import ErrorModule from "../styles/Error.css"

function Error(message) {
    message = isNaN(message) ? "Oops, something went wrong." : message;
    return (
        <>
            <div className={"error-content"}>
                <div className={"error-logo"}>
                    <img src="https://i.postimg.cc/k5zT5dmN/logo-broken.png" id={"error-logo"} alt="logo"/>
                </div>
            <div className={"error-message"}>
            <h1 id="specific-message">{message}</h1>
            <h1 id={"error-message"}>You can report the issue or simply go back to the main page.</h1>
            </div>
                <div className={"error-action"}>
            <Link to="/coins">
                <button className="button btn-outlined">Report the issue!</button>
            </Link>
            <Link to="/coins">
                <button className="button btn-contained">Go to homepage!</button>
            </Link>
                </div>
            </div>
            </>
    )
}

export default Error