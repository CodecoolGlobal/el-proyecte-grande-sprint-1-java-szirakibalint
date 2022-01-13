import {Link} from "react-router-dom";
import NavbarModule from "../styles/Navbar.css"

function Navbar() {
    return (
        <div className="navbar">
            <div id="logo-container">
                <Link to='/'><img src='https://i.postimg.cc/J46SZv3g/logo.png' alt='logo'/></Link>
            </div>
            <div id="nav-options">
                <Link to="/coins">Currencies</Link>
                <Link to="/portfolio">Portfolio</Link>
                <Link to="/register">Register</Link>
                <Link to="/login">Login</Link>
            </div>
        </div>
    )
}

export default Navbar;