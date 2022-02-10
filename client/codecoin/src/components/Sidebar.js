import React, {useContext, useEffect, useState} from 'react';
import { pushRotate as Menu } from 'react-burger-menu';
import '../styles/Sidebar.css';
import {UserContext} from "./UserContext";
import { Turn as Hamburger } from 'hamburger-react'


export default props => {
    let [isLoggedIn, setIsLoggedIn] = useContext(UserContext);
    const [isOpen, setOpen] = useState(false);
    function clearSession() {
        sessionStorage.clear();
        setIsLoggedIn(false);
    }
    let changeOpen = function() {
        setOpen(!isOpen)
        return isOpen;
    };

    if (isLoggedIn) {
        return (
            <Menu
                onStateChange={ changeOpen }
                customBurgerIcon={<Hamburger toggled={isOpen} toggle={changeOpen}/>}
                noOverlay
                right
                width={'100%'}>
                <a className="menu-item" href="/coins">
                    Currencies
                </a>
                <a className="menu-item" href="/portfolio">
                    Portfolio
                </a>
                <a className="menu-item" href="/transactions">
                    My transaction history
                </a>
                <a className="menu-item" onClick={clearSession}>
                    Logout
                </a>
            </Menu>
        );
    }
    else {
        return (
            <Menu onStateChange={changeOpen} customBurgerIcon={<Hamburger toggled={isOpen} toggle={changeOpen}/>} noOverlay right width={'100%'}>
                <a className="menu-item" href="/coins">
                    Currencies
                </a>
                <a className="menu-item" href="/register">
                    Register
                </a>
                <a className="menu-item" href="/login">
                    Login
                </a>
            </Menu>
        );
    }
};
