import {Link} from "react-router-dom";
import LandingPageModule from "../styles/LandingPage.css"
import Button from "../components/Button";
import TypewriterComponent from "typewriter-effect";

function LandingPage() {
    return (
        <div className={"container"}>
            <div className="bg-image" />
            <div className="title">
                <p className="text primary">We are </p>
                <p className={"text secondary"}>
                <TypewriterComponent options={{autoStart:true, loop:true}} onInit={(typewriter) => {
                    typewriter.typeString("helping your finance")
                        .pauseFor(2000)
                        .deleteAll()
                        .typeString("tracking your success")
                        .pauseFor(2000)
                        .deleteAll()
                        .typeString("making your life easier")
                        .pauseFor(2000)
                        .deleteAll()
                        .start();
                }}/></p>
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