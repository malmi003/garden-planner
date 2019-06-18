import React from "react";
import { withRouter } from 'react-router';
import Garden from "../../components/Garden";
import PGOBar from "./PGOBar";

class GardenDiagram extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            gdId: parseInt(window.location.href.split(":").pop()),
            layouts: []
        }
    }

    render() {
        return (
            <div id="garden-diagram-container">
                <PGOBar
                    handleGetPlants={this.props.handleGetPlants}
                    handleCreateNewPGO={this.props.handleCreateNewPGO}
                    plants={this.props.plants}
                    layouts={this.props.layouts}
                />
                <Garden
                    layouts={this.props.layouts}
                    handleDeletePGO={this.props.handleDeletePGO}
                    handleUpdatePGO={this.props.handleUpdatePGO}
                    resetCompsIncomps={this.props.resetCompsIncomps}
                    currentCompanions={this.props.currentCompanions}
                    currentIncompatables={this.props.currentIncompatables}
                    getAllRelevantPlantFromPGOId={this.props.getAllRelevantPlantFromPGOId}
                />
            </div>
        )
    }
}
export default withRouter(GardenDiagram)