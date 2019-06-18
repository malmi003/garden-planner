import React, { Component } from "react";
import MyGardens from "../../components/MyGardens";
import PieChart from "../../components/PieChart";
import RangeBarChart from "../../components/RangeBarChart";

class DashBoard extends Component {
    constructor(props) {
        super(props);

    }

    componentDidMount = () => {
    }

    render() {
        return (
            <div className="container" id="dashboard-container">
                <div className="row mt-4 pb-4">
                    <div className="col-md-7">
                        <div className="row">
                            <div className="col">
                                <PieChart
                                    yields={this.props.yields}
                                />
                            </div>
                        </div>
                        <div className="row">
                            <div className="col">
                                <div className="mt-5" id="plant-schedule-container" >
                                    <h3 className="text-center">{this.props.allMyPlants.length>0 ? "My Plant Schedule" : ""}</h3>
                                    {this.props.allMyPlants.map(p => {
                                        return (
                                            <RangeBarChart
                                                user={this.props.user}
                                                plant={p}
                                                key={p.id} />
                                        )
                                    })}
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="col-md-5">
                        <MyGardens
                            authUser={this.props.authUser}
                            onDeleteGardenClick={this.props.onDeleteGardenClick}
                            getLayouts={this.props.getLayouts}
                            gardens={this.props.gardens}
                            updateGarden={this.props.updateGarden}
                            handleCreateNewDiagram={this.props.handleCreateNewDiagram}
                        />
                    </div>
                </div>

            </div>
        )
    }
}

export default DashBoard;