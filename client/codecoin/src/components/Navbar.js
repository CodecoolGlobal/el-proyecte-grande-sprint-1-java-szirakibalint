import {Link} from "react-router-dom";

function Navbar() {
    return (
        <div className="navbar">
            <div id="logo-container">
                <Link to='/'><img src='https://i.postimg.cc/J46SZv3g/logo.png' width="200px" alt='logo'/></Link>
            </div>
            <div id="nav-options">
                <Link to="/coins">Currencies</Link>
                <Link to="/portfolio">Portfolio</Link>
            </div>
        </div>
    )
}

export default Navbar;