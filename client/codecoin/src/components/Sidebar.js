import React, {useContext, useEffect, useState} from 'react';
import { pushRotate as Menu } from 'react-burger-menu';
import '../styles/Sidebar.css';
import {UserContext} from "./UserContext";


export default props => {
    let [isLoggedIn, setIsLoggedIn] = useContext(UserContext);
    function clearSession() {
        sessionStorage.clear();
        setIsLoggedIn(false);
    }
    if (isLoggedIn) {
        return (
            <Menu right width={'100%'}>
                <a className="menu-item" href="/coins">
                    Currencies
                </a>
                <a className="menu-item" href="/portfolio">
                    Portfolio
                </a>
                <a className="menu-item" onClick={clearSession}>
                    Logout
                </a>
            </Menu>
        );
    }
    else {
        return (
            <Menu noOverlay right width={'100%'}>
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
