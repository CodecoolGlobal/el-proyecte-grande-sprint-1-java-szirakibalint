import {Link} from "react-router-dom";
import NavbarModule from "../styles/Navbar.css"
import {useContext, useEffect} from "react";
import {UserContext} from "./UserContext";
import Sidebar from "./Sidebar";

function Navbar() {
    let [isLoggedIn, setIsLoggedIn] = useContext(UserContext);
    function clearSession() {
        sessionStorage.clear();
        setIsLoggedIn(false);
    }
    if (isLoggedIn) {
        return (
            <div className="navbar" id="page-wrap">
                <div id="logo-container">
                    <Link to='/'><img src='https://i.postimg.cc/J46SZv3g/logo.png' alt='logo'/></Link>
                </div>
                <div id="nav-options">
                    <Link to="/coins">Currencies</Link>
                    <div className={"nav-log"}>
                        <Link to="/portfolio">Portfolio</Link>
                        <Link onClick={clearSession} to="/coins">Logout</Link>
                    </div>
                </div>
                <div className="menu">
                <Sidebar pageWrapId={'page-wrap'} outerContainerId={'outer-container'} />
                </div>
            </div>
        )
    }
    else {
    return (
        <div className="navbar" id="page-wrap">
            <div id="logo-container">
                <Link to='/'><img src='https://i.postimg.cc/J46SZv3g/logo.png' alt='logo'/></Link>
            </div>
            <div id="nav-options">
                <Link to="/coins">Currencies</Link>
                <div className={"nav-log"}>
                    <Link to="/register">Register</Link>
                    <Link to="/login">Login</Link>
                </div>
            </div>
            <div className="menu">
            <Sidebar pageWrapId={'page-wrap'} outerContainerId={'outer-container'} />
            </div>
        </div>
    )
    }
}

export default Navbar;
