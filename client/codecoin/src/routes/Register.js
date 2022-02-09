import LoginModule from "../styles/Login.css"
import useUsers from "../components/useUsers";
import {useEffect, useState} from "react";

function Register() {

    const usernames = useUsers('',true);
    const[username, setUsername] = useState([]);
    const[firstPassword, setFirstPassword] = useState([]);
    const[secondPassword, setSecondPassword] = useState([]);
    const[buttonDisabled, setButtonDisabled] = useState(true);
    const[errors, setErrors] = useState("");

    useEffect(() => {
        setButtonDisabled(!validateData());
    })

    function updateInputValue(event, setInput) {
        setInput(event.target.value);
    }

    function validateData() {
        let errorMessages = "";
        if (firstPassword !== secondPassword) {
            errorMessages += "Passwords does not match\n";
        }
        if (firstPassword.length < 6) {
            errorMessages += "Password is too short\n";
        }
        if (username.length < 5 || !username.includes("@") || !username.includes(".")) {
            errorMessages += "Email is invalid\n";
        }
        for (const username1 of usernames) {
            if (username1 === username) {
                errorMessages += "This email is already taken\n";
                break;
            }
        }
        setErrors(errorMessages);
        return errorMessages === "";
    }

    async function submitRegistration() {
        if (validateData()) {
            await fetch(`/api/users`, {
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                method: 'POST',
                body: JSON.stringify({
                    "username": username,
                    "password": firstPassword
                })
            })
            window.location.href = `/login`;
        }
    }

    return (
        <div>
            <div className={"sign-content"}>
                <div className="sign-details">
                    <h1 className={"form-title"}>Register</h1>
                    <label htmlFor={"email"}>Email</label>
                    <input className={"form-input"} id={"email"} name={"email"} type={"text"} placeholder={"code.coin@iotiger.hello"} onChange={event => updateInputValue(event, setUsername)}/>
                    <label htmlFor={"password"}>Password</label>
                    <input className={"form-input"} id={"password"} name={"password"} type={"password"} onChange={event => updateInputValue(event, setFirstPassword)}/>
                    <label htmlFor={"confirm-password"}>Confirm password</label>
                    <input className={"form-input"} id={"confirm-password"} name={"confirm-password"} type={"password"} onChange={event => updateInputValue(event, setSecondPassword)} />
                    <button className={"button purple-button"} disabled={buttonDisabled} onClick={submitRegistration}>Register</button>
                    <div>{errors}</div>
                </div>
            </div>
        </div>
    )
}

export default Register;


//password contains one capital letter, number, and longer or equal than 8 chars