import axios from "axios";

export default {

    addUser: function (newUserData) {
        return axios.post("http://localhost:8080/api/user/create", newUserData);
    },
    getUserById: function (userId) {
        return axios.post("http://localhost:8080/api/user/getbyid",userId);
    }
}