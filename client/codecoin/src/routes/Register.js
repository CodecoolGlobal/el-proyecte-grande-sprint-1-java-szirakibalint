import LoginModule from "../styles/Login.css"
import useUsers from "../components/useUsers";
import {useState} from "react";

function Register() {

    //const usernames = useUsers('',true);
    const[username, setUsername] = useState([]);
    const[firstPassword, setFirstPassword] = useState([]);
    const[secondPassword, setSecondPassword] = useState([]);
    const[buttonDisabled, setButtonDisabled] = useState(true);

    function updateInputValue(event, setInput) {
        setInput(event.target.value);
        setButtonDisabled(!validateData());
        console.log(username);
    }
    function validateData() {
        return !(firstPassword !== secondPassword || firstPassword.length < 6 || username.length < 6);

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
                    <button className={"button purple-button"} disabled={buttonDisabled}>Sign in</button>
                </div>
            </div>
        </div>
    )
}

export default Register;


//email contains @ and .
//password contains one capital letter, number, and longer or equal than 8 chars
// confirmed pass is equals password
//one thingie, backend could check if it is already exists or not.