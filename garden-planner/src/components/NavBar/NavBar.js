import React from 'react';
import SignOutButton from "../SignOutButton";
import { Link } from "react-router-dom";

const NavBar = ({ authUser, handleGetYields, handleGetUserPlants }) => (
    <div>{authUser ?
        <NavigationAuth authUser={authUser} handleGetYields={handleGetYields} handleGetUserPlants={handleGetUserPlants}/> : <NavigationNonAuth />}</div>
);


class NavigationAuth extends React.Component {
    constructor(props) {
        super(props);
    }
    render() {
        return (
            <nav className="navbar is-dark" role="navigation" aria-label="main navigation">
                <NavBarBrand />
                <div className="navbar-end">
                    <div className="navbar-item">
                        <div className="field is-grouped">
                            <p className="control">
                                <Link
                                    className="button is-dark"
                                    to="/dashboard"
                                    onClick={() => {this.props.handleGetYields(); this.props.handleGetUserPlants();}}>
                                    <strong>Dashboard</strong>
                                </Link>
                            </p>
                            <p className="control">
                                <SignOutButton />
                            </p>
                        </div>
                    </div>
                </div>
            </nav>
        );
    }
}


class NavigationNonAuth extends React.Component {
    render() {
        return (
            <nav className="navbar is-dark" role="navigation" aria-label="main navigation">
                <NavBarBrand />
                <div className="navbar-end">
                    <div className="navbar-item">
                        <div className="field is-grouped">
                            <p className="control">
                                <Link
                                    to="/login"
                                    className="button is-dark">
                                    Login
                                </Link>

                            </p>
                            <p className="control">
                                <Link
                                    to="/signUp"
                                    className="button is-dark">
                                    Sign Up
                                </Link>
                            </p>
                        </div>
                    </div>
                </div>
            </nav>
        );
    }
}

let NavBarBrand = () => {
    return (
        <div className="navbar-brand">
            <Link className="navbar-item" to="/">
                <img src="/images/sprout.svg" width="28" height="28" alt="sprout"/>
                <h1 className="menuTitle pl-2">Garden Planner</h1>
            </Link>
        </div>
    )
}





export default NavBar;