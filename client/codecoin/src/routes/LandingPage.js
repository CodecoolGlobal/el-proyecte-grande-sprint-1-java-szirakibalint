import {Link} from "react-router-dom";

function LandingPage() {
    return (
        <div>
            <div className="title">
                <p className="text primary">We are </p>
                <p className="text secondary" id="text">the dovari</p>
            </div>
            <div className="buttons">
                <Link to="/coins">
                    <button className="button btn-primary">See currencies</button>
                </Link>
                <Link to="/portfolio">
                    <button className="button btn-secondary">Get started</button>
                </Link>
            </div>
        </div>
    )
}

export default LandingPage;