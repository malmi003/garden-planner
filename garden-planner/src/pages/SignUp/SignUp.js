import React, { Component } from "react";
import InputBox from "../../components/InputBox";
// import APIArtists from "../../../utils/APIArtists";
import { Link, withRouter } from "react-router-dom";
import { withFirebase } from '../../components/Firebase';
import UserAPI from "../../utils/UserAPI";

const INITIAL_STATE = {
    email: "",
    password: "",
    confirmPassword: "",
    phone: "",
    zipCode: "",
    emailValidated: false,
    emailValidationMessage: "please enter your email address",
    passwordValidated: false,
    passwordValidationMessage: "please enter your password",
    confirmPasswordValidated: false,
    confirmPasswordValidationMessage: "passwords must match",
    phoneValidated: false,
    phoneValidationMessage: "please enter your password",
    zipCodeValidated: false,
    zipCodeValidationMessage: "please enter a valid zip",
    signUpError: null,
};

class SignUpFormBase extends Component {


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
            // phone regex
            if (this.state.phone.match(/^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/)) 
            {
                this.setState({
                    phoneValidated: true,
                    phoneValidationMessage: "please enter your phone number"
                });
            } else {
                this.setState({
                    phoneValidated: false,
                    phoneValidationMessage: "please enter your phone number"
                });
            }
            // zip regex
            if (this.state.zipCode.match(/(^\d{5}$)|(^\d{5}-\d{4}$)/)) {
                this.setState({
                    zipCodeValidated: true,
                    zipCodeValidationMessage: "please enter your zip code"
                });
            } else {
                this.setState({
                    zipCodeValidated: false,
                    zipCodeValidationMessage: "please enter your zip code"
                });
            }


        });
    };
    // Handling client-side password validation
    handlePasswordConfirmValidation = event => {
        // first handles the input change (from above)
        this.handleInputChange(event, () => {
            // checks if the password is long enough (7 characters in this case)
            if (this.state.password.length >= 7) {
                // if so, sets state.passwordValidated to true and displays success message
                this.setState({
                    passwordValidated: true,
                });
            } else {
                // else, sets state.passwordValidated to false and displays enter pw message
                this.setState({
                    passwordValidated: false,
                });
            }
            // then if the actual pw is validated, and its value matches the value of the confirmPassword
            if (this.state.passwordValidated && (this.state.password === this.state.confirmPassword)) {
                // set the state.confirmPwValidated to true and display success message
                this.setState({
                    confirmPasswordValidated: true,
                });
            } else {
                // else set state.confirmPwValidated to false with instructional message
                this.setState({
                    confirmPasswordValidated: false,
                });
            }
        });
    };
    // handles when "submitBtn" is clicked on form
    handleFormSubmit = event => {

        const { email, password } = this.state;

        this.props.firebase
            .doCreateUserWithEmailAndPassword(email, password)
            .then(authUser => {
                // make ajax call here to create user in db w/ all the state info
                UserAPI.addUser({
                    id: authUser.user.uid,
                    phone: this.state.phone,
                    notificationEmail: this.state.email,
                    zipCode: this.state.zipCode
                }).then(() => {
                    this.setState({ ...INITIAL_STATE });
                    this.props.history.push("/dashboard");
                }).catch(signUpError => {
                    this.setState({ signUpError });
                })

            })
            .catch(signUpError => {
                this.setState({ signUpError });
            });

        event.preventDefault();
    };

    render() {
        return (
            <div className="card p-5 w-50 m-auto">
                <form className="form" id="signUpForm">
                    <InputBox
                        label="Email"
                        inputClassName={
                            this.state.emailValidated
                                ? "input modal-input is-success"
                                : "input modal-input is-danger"
                        }
                        inputType="email"
                        inputId="signUpEmailInputBox"
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
                        inputId="signUpPasswordInputBox"
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
                    {/* confirm pw input box */}
                    <InputBox
                        label="Confirm Password"
                        inputClassName={
                            this.state.confirmPasswordValidated
                                ? "input modal-input is-success"
                                : "input modal-input is-danger"
                        }
                        inputType="password"
                        inputId="signUpConfirmPasswordInputBox"
                        inputValue={this.state.confirmPassword}
                        inputOnChange={this.handlePasswordConfirmValidation}
                        inputName="confirmPassword"
                        inputPlaceholder="confirm password"
                        leftIconClassName="fas fa-key"
                        rightIconClassName={
                            this.state.confirmPasswordValidated
                                ? "fas fa-check"
                                : "fas fa-exclamation-triangle"
                        }
                        paragraphClassName={
                            this.state.confirmPasswordValidated
                                ? "help is-success"
                                : "help is-danger"
                        }
                        paragraphMessage={this.state.confirmPasswordValidationMessage}
                    />

                    {/* phone input box */}
                    <InputBox
                        label="phone"
                        inputClassName={
                            this.state.phoneValidated
                                ? "input modal-input is-success"
                                : "input modal-input is-danger"
                        }
                        inputType="phone"
                        inputId="signUpPhoneInputBox"
                        inputValue={this.state.phone}
                        inputOnChange={this.handleInputValidation}
                        inputName="phone"
                        inputPlaceholder="enter your phone number"
                        leftIconClassName="fas fa-phone"
                        rightIconClassName={
                            this.state.phoneValidated
                                ? "fas fa-check"
                                : "fas fa-exclamation-triangle"
                        }
                        paragraphClassName={
                            this.state.phoneValidated
                                ? "help is-success"
                                : "help is-danger"
                        }
                        paragraphMessage={this.state.phoneValidationMessage}
                    />
                    {/* zipcode input box */}
                    <InputBox
                        label="ZipCode"
                        inputClassName={
                            this.state.zipCodeValidated
                                ? "input modal-input is-success"
                                : "input modal-input is-danger"
                        }
                        inputType="number"
                        inputId="signUpZipCodeInputBox"
                        inputValue={this.state.zipCode}
                        inputOnChange={this.handleInputValidation}
                        inputName="zipCode"
                        inputPlaceholder="enter your zipcode"
                        leftIconClassName="fas fa-map-pin"
                        rightIconClassName={
                            this.state.zipCodeValidated
                                ? "fas fa-check"
                                : "fas fa-exclamation-triangle"
                        }
                        paragraphClassName={
                            this.state.zipCodeValidated
                                ? "help is-success"
                                : "help is-danger"
                        }
                        paragraphMessage={this.state.zipCodeValidationMessage}
                    />
                    {/* Submit/close btns */}
                    <div className="field">
                        <button
                            className="button is-info mr-1"
                            id="signUpBtn"
                            onClick={this.handleFormSubmit}
                            disabled={
                                this.state.passwordValidated && this.state.emailValidated && this.state.confirmPasswordValidated && this.state.phoneValidated && this.state.zipCodeValidated
                                    ? ""
                                    : "disabled"
                            }
                            type="button"
                        >Sign Up</button>
                        <Link
                            className="button is-danger"
                            id="cancelBtn"
                            to="/"

                        >Cancel
                    </Link>
                    </div>
                    <div className="has-text-danger">
                        {this.state.signUpError && <p>{this.state.signUpError.message}</p>}
                    </div>
                </form>
            </div>
        );
    }
}

const SignUpForm = withRouter(withFirebase(SignUpFormBase));

const SignUp = () => (
    <div id="sign-up-container" className="mb-5">
        <h1 className="text-center has-text-white-ter">SignUp</h1>
        <SignUpForm />
    </div>
);

const SignUpLink = () => (
    <p className="text-center has-text-white-ter mt-2">
        Don't have an account? <Link to={"/signup"} className="has-text-info">Sign Up</Link>
    </p>
);

export default SignUp;

export { SignUpForm, SignUpLink };
