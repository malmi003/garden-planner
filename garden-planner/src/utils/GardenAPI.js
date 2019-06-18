import axios from "axios";

export default {

    addGarden: function (newGardenData) {
        return axios.post("http://localhost:8080/api/garden/creategarden", newGardenData);
    },
    updateGarden: function (updateGarden) {
        return axios.post("http://localhost:8080/api/garden/updategarden", updateGarden);
    },
    deleteGarden: function(garden) {
        return axios.post("http://localhost:8080/api/garden/deletegarden", garden)
    },
    getPGO:function(id) {
        return axios.post("http://localhost:8080/api/garden/getpgo", id)
    },
    createPGO: function(newPGOData){
        return axios.post("http://localhost:8080/api/garden/createpgo", newPGOData);
    },
    updatePGO: function(updatedPGO) {
        return axios.post("http://localhost:8080/api/garden/updatepgo", updatedPGO);
    },
    deletePGO:function(pgoId) {
        return axios.post("http://localhost:8080/api/garden/deletepgo", pgoId);
    },
    getGardensByUserId: function(userId) {
        return axios.post("http://localhost:8080/api/garden/getgardensbyuserid", userId);
    },
    getGardenById: function(gardenId) {
        return axios.post("http://localhost:8080/api/garden/getgardenbyid", gardenId);
    },
    getPGOsByGardenId: function(gardenId) {
        return axios.post("http://localhost:8080/api/garden/getpgosbygardenid", gardenId);
    },

}