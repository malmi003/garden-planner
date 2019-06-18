import React, { Component } from "react";
import { Link } from "react-router-dom";

class LandingPage extends Component {
    constructor(props) {
        super(props);

    }

    componentDidMount = () => {
    }

    render() {
        return (
            <div className="has-text-centered" id="landing-page-container">
                <section className="hero is-dark is-bold">
                    <div className="hero-body">
                        <div className="container">
                            <div className="level">
                                <h1 className="title m-auto">
                                    Garden Planner
                                <img alt="seedling" src="/images/sprout.svg" className="image is-96x96 ml-2" />
                                </h1>
                            </div>
                        </div>
                        <h2 className="subtitle">
                            grow.
                            </h2>
                        <div className="field is-grouped is-grouped-centered">
                            <p className="control">
                                <HeroButton
                                    btnType="is-primary"
                                    path="/signup"
                                    desc="Get Started"
                                />
                            </p>
                            <p className="control">
                                <HeroButton
                                    btnType="is-info"
                                    path="/login"
                                    desc="Login"
                                />
                            </p>
                        </div>
                    </div>
                </section>
            </div >
        )
    }
}

let HeroButton = props => {
    return (
        <Link
            className={`button ${props.btnType}`}
            to={props.path}
        >{props.desc}
        </Link>
    )
}

export default LandingPage;