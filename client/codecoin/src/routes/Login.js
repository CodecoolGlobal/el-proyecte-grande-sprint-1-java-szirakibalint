import LoginModule from "../styles/Login.css"
import {useState, useContext, useEffect} from "react";
import {UserContext} from "../components/UserContext";
import { useNavigate } from "react-router-dom";

function Login() {
    const[username, setUsername] = useState([]);
    const[password, setPassword] = useState([]);
    const[errorMessage, setErrorMessage] = useState("");
    const [loggedIn, setIsLoggedIn] = useContext(UserContext)
    let navigate = useNavigate();

    function goToRegistration() {
        navigate("/register")
    }

    useEffect(() => {
        if (loggedIn) {
            return navigate("/portfolio")
        }
    })

    function updateInputValue(event, setInput) {
        setInput(event.target.value);
    }

    // TODO improve lazy solution
    async function submitLogin() {
        console.log({username, password})
        const userResponse = await fetch("/api/users/login", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: 'POST',
            body: JSON.stringify({
                "username": username,
                "password": password
            })
        })
        const jwtResponse = await fetch("/authenticate", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: 'POST',
            body: JSON.stringify({
                "username": username,
                "password": password
            })
        })
        const {id} = await userResponse.json();
        const {jwt} = await jwtResponse.json();
        console.log({jwt, userResponse});
        if (id === "-1" || jwt === undefined) {
            setErrorMessage("Invalid email or password");
        }
        else {
            setIsLoggedIn(true);
            sessionStorage.setItem("jwt", jwt);
            sessionStorage.setItem("user-id", id);

        }

    }

    return (
        <div className={"full-container"}>
        <div className={"sign-content"}>
            <div className="sign-details">
                <h1 className={"form-title"}>Sign in</h1>
                <label htmlFor={"email"}>Email</label>
                <input className={"form-input"} id={"email"} name={"email"} type={"text"} placeholder={"code.coin@iotiger.hello"} onChange={event => updateInputValue(event, setUsername)}/>
                <label htmlFor={"password"}>Password</label>
                <input className={"form-input"} id={"confirm-password"} name={"password"} type={"password"} placeholder={"code.coin@iotiger.hello"} onChange={event => updateInputValue(event, setPassword)}/>
                <button onClick={submitLogin} className={"button purple-button"}>Sign in</button>
                <button onClick={goToRegistration} className={"button btn-outlined"}>Create new account</button>
                <div>{errorMessage}</div>
            </div>
        </div>
        </div>
    )
}

export default Login;
