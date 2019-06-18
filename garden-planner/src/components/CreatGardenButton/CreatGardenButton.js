import React from "react";


class CreatGardenButton extends React.Component {
  constructor(props) {
    super(props)
  }
  render() {
    return (
      <button 
      className="button is-fullwidth mb-3 is-info" 
      onClick={() => this.props.handleCreateNewDiagram()} >
        Create New Garden
      </button>
    )
  }
}
export default CreatGardenButton;