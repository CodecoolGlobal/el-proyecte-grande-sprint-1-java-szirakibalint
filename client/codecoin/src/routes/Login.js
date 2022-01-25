import LoginModule from "../styles/Login.css"

function Login() {
    return (
        <div className={"sign-content"}>
            <div className="sign-details">
                <h1 className={"form-title"}>Sign in</h1>
                <label htmlFor={"email"}>Email</label>
                <input className={"form-input"} id={"email"} name={"email"} type={"text"} placeholder={"code.coin@iotiger.hello"}/>
                <label htmlFor={"password"}>Password</label>
                <input className={"form-input"} id={"confirm-password"} name={"password"} type={"password"} placeholder={"code.coin@iotiger.hello"}/>
                <button className={"button purple-button"}>Sign in</button>
            </div>
        </div>
    )
}

//maybe email validation: required, @, one .
//password validation necessary?

export default Login;