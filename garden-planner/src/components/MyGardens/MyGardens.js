import React from "react";
import { withRouter } from 'react-router';
import { Link } from "react-router-dom";
import CreatGardenButton from "../CreatGardenButton";

class MyGardens extends React.Component {
    constructor(props) {
        super(props)

        this.state = {
            error: null,
        }
    }
    handleInputChange = (event, callback) => {
        const { name, value } = event.target;
        this.setState(
            {
                [name]: value
            },
            callback
        );
    };
    render() {
        return (
            <div id="gardensBox">
                <h3 className="text-center pb-3">My Gardens</h3>
                <CreatGardenButton
                    handleCreateNewDiagram={this.props.handleCreateNewDiagram}
                />
                {this.props.gardens.map(g => {
                    return (
                        <div className="card mb-2" key={g.id}>
                            <header className="card-header">
                                <input
                                    className="input"
                                    type="text"
                                    onChange={this.handleInputChange}
                                    name={g.id}
                                    id={g.id}
                                    value={this.state[g.id] != null ? this.state[g.id] : g.name ? g.name : ""}
                                    placeholder="garden name"
                                >
                                </input>
                                <button
                                    className="icon has-text-warning is-medium button ml-1 top-tooltip"
                                    onClick={() => {
                                        this.props.updateGarden(g.id, this.state[g.id]);
                                        // this.setState({[g.id]: null});
                            }}
                                    disabled={this.state[g.id] == null}>
                                    <i className="fas fa-lg fa-check-square"></i>
                                <div className="tooltiptext">
                                    Save name
                                    </div>
                                    
                                </button>

                            </header>

                        <footer className="card-footer">
                            <Link
                                to={`gardendiagram:${g.id}`}
                                className="card-footer-item button is-primary"
                                onClick={() => this.props.getLayouts(g.id)}>Open</Link>
                            <span
                                className="card-footer-item button is-danger"
                                onClick={() => this.setState({ ["modal-" + g.id]: "is-active" })} >
                                Delete
                                </span>
                            <div className={`modal ${this.state["modal-" + g.id]}`} id={"modal-" + g.id}>
                                <div className="modal-background"></div>
                                <div className="modal-background"></div>
                                <div className="modal-card">
                                    <header className="modal-card-head">
                                        <p className="modal-card-title">Confirm Delete</p>
                                        <button
                                            className="delete"
                                            aria-label="close"
                                            onClick={() => this.setState({ ["modal-" + g.id]: "" })}></button>
                                    </header>
                                    <section className="modal-card-body">
                                        <p>Are you sure you want to delete this garden?</p>
                                        <p>{g.name}</p>
                                    </section>
                                    <footer className="modal-card-foot">
                                        <button
                                            className="button is-danger"
                                            onClick={() => this.props.onDeleteGardenClick(g.id)}>Delete</button>
                                        <button
                                            className="button"
                                            onClick={() => this.setState({ ["modal-" + g.id]: "" })}
                                        >Cancel</button>
                                    </footer>
                                </div>
                            </div>
                        </footer>
                        </div>
            )
        })}
                <div>
                    {this.state.error && <p>{this.state.error.message}</p>}
                </div>
            </div>

        )
    }
}
export default withRouter(MyGardens)