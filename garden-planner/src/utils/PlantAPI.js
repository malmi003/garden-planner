import axios from "axios";

export default {

    getAllPlants: function () {
        return axios.get("http://localhost:8080/api/plant/getallplants");
    },
    getPlantById: function(plantId) {
        return axios.post("http://localhost:8080/api/plant/getplantbyid", plantId)
    },
    getCompanionPlants: function(plantId) {
        return axios.post("http://localhost:8080/api/plant/getcompanionplants", plantId)
    },
    getIncompatablePlants: function(plantId) {
        return axios.post("http://localhost:8080/api/plant/getincompatableplants", plantId)
    },
    getPlantsByUserId: function(userId) {
        return axios.post("http://localhost:8080/api/plant/getplantsbyuserid", userId)
    }
}