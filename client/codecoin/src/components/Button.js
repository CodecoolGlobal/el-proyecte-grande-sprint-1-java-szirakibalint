import ButtonModule from "../styles/Button.css"


function Button(props) {
    return (
        <div>
            <button className={props.className}>{props.text}</button>
        </div>
    )
}

export default Button