import {Link} from "react-router-dom";
import LandingPageModule from "../styles/LandingPage.css"
import Button from "../components/Button";

function LandingPage() {
    return (
        <div className={"container"}>
            <div className="title">
                <p className="text primary">We are </p>
                <p className="text secondary" id="text">the dovari</p>
            </div>
            <div className="buttons">
                <Link to="/coins">
                    <Button className="button btn-contained" text="See currencies"/>
                </Link>
                <Link to="/portfolio">
                    <Button className="button btn-outlined" text="Get started"/>
                </Link>
            </div>
        </div>
    )
}

export default LandingPage;