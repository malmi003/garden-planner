import React, { Component } from "react";
import InputBox from "../../components/InputBox";
import { Link, withRouter } from "react-router-dom";
import { SignUpLink } from '../SignUp';
import { withFirebase } from '../../components/Firebase';

const INITIAL_STATE = {
    email: "",
    password: "",
    emailValidated: false,
    emailValidationMessage: "please enter your email address",
    passwordValidated: false,
    passwordValidationMessage: "please enter your password",
    signInError: null,
};
class LoginFormBase extends Component {
    constructor(props) {
        super(props);

        this.state = { ...INITIAL_STATE };

    }

    // sets the state/value for any type of input box in real time as long as they share the same name/value attributes as the state
    handleInputChange = (event, callback) => {
        const { name, value } = event.target;
        this.setState(
            {
                [name]: value
            },
            callback
        );
    };
    // handles the client-side email/password validation
    handleInputValidation = event => {
        // First handles the input change (from above)
        this.handleInputChange(event, () => {
            // if there's something in the email box (length >=1, sets its corresponding states to true
            if (this.state.email.match(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)) {
                this.setState({
                    emailValidated: true,
                    emailValidationMessage: "please enter your email"
                });
                // else false
            } else {
                this.setState({
                    emailValidated: false,
                    emailValidationMessage: "please enter your email"
                });
            }
            // same deal with password, just making sure the box isn't empty
            if (this.state.password.length >= 6) {
                this.setState({
                    passwordValidated: true,
                    passwordValidationMessage: "please enter your password"
                });
            } else {
                this.setState({
                    passwordValidated: false,
                    passwordValidationMessage: "please enter your password"
                });
            }
        });
    };
    // handles when "submitBtn" is clicked on form
    handleFormSubmit = event => {
        const { email, password } = this.state;

        this.props.firebase
            .doSignInWithEmailAndPassword(email, password)
            .then(authUser => {
                this.setState({ ...INITIAL_STATE });
                this.props.history.push("/dashboard");
            })
            .catch(signInError => {
                this.setState({
                    signInError: "The username or password entered is incorrect."
                });
            });

        event.preventDefault();
    };

    render() {
        return (

            <div className="card p-5 w-50 m-auto" style={{ backgroundColor: "white" }}>
                <div >
                    <div className="form" id="loginForm">
                        <InputBox
                            label="Email"
                            inputClassName={
                                this.state.emailValidated
                                    ? "input modal-input is-success"
                                    : "input modal-input is-danger"
                            }
                            inputType="email"
                            inputId="signInEmailInputBox"
                            inputValue={this.state.email}
                            inputOnChange={this.handleInputValidation}
                            inputName="email"
                            inputPlaceholder="enter your email"
                            leftIconClassName="fas fa-envelope"
                            rightIconClassName={
                                this.state.emailValidated
                                    ? "fas fa-check"
                                    : "fas fa-exclamation-triangle"
                            }
                            paragraphClassName={
                                this.state.emailValidated
                                    ? "help is-success"
                                    : "help is-danger"
                            }
                            paragraphMessage={this.state.emailValidationMessage}
                        />
                        {/* Password input box */}
                        <InputBox
                            label="Password"
                            inputClassName={
                                this.state.passwordValidated
                                    ? "input modal-input is-success"
                                    : "input modal-input is-danger"
                            }
                            inputType="password"
                            inputId="signInPasswordInputBox"
                            inputValue={this.state.password}
                            inputOnChange={this.handleInputValidation}
                            inputName="password"
                            inputPlaceholder="enter your password"
                            leftIconClassName="fas fa-key"
                            rightIconClassName={
                                this.state.passwordValidated
                                    ? "fas fa-check"
                                    : "fas fa-exclamation-triangle"
                            }
                            paragraphClassName={
                                this.state.passwordValidated
                                    ? "help is-success"
                                    : "help is-danger"
                            }
                            paragraphMessage={this.state.passwordValidationMessage}
                        />
                        {/* Submit/close btns */}
                        <div className="field is-grouped level">
                            <button
                                className="button is-info mr-1"
                                id="signInBtn"
                                onClick={this.handleFormSubmit}
                                disabled={
                                    this.state.passwordValidated && this.state.emailValidated
                                        ? ""
                                        : "disabled"
                                }
                                type="button"
                            > Login</button>
                            <Link
                                className="button is-danger"
                                id="cancelBtn"
                                to="/"

                            >Cancel
                    </Link>
                        </div>
                        <div className="has-text-danger">
                            {this.state.signInError && <p>{this.state.signInError}</p>}
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

const LoginForm = withRouter(withFirebase(LoginFormBase));


const Login = () => (
    <div id="login-container">
        <h1 className="text-center has-text-white-ter">Login</h1>
        <LoginForm />
        <SignUpLink />
    </div>
);


export default Login;
export { LoginForm };