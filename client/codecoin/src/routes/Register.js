import LoginModule from "../styles/Login.css"

function Register() {

    return (
        <div>
            <div className={"sign-content"}>
                <div className="sign-details">
                    <h1 className={"form-title"}>Register</h1>
                    <label htmlFor={"email"}>Email</label>
                    <input className={"form-input"} id={"email"} name={"email"} type={"text"} placeholder={"code.coin@iotiger.hello"}/>
                    <label htmlFor={"password"}>Password</label>
                    <input className={"form-input"} id={"password"} name={"password"} type={"password"}/>
                    <label htmlFor={"confirm-password"}>Confirm password</label>
                    <input className={"form-input"} id={"confirm-password"} name={"confirm-password"} type={"password"}/>
                    <button className={"button purple-button"}>Sign in</button>
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