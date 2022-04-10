import axios from "axios";

const API_URL = "http://localhost:8080/api/auth/";

// const register = ({ firstName, lastName, city, email, password }, picture) => {
const register = ({ firstName, lastName, city, email, password }, picture) => {
    const formData = new FormData()
    const user = {
        "firstName": firstName,
        "lastName": lastName,
        "city": city,
        "email": email,
        "password": password
    }
    const json = JSON.stringify(user)

    formData.append('user', new Blob([
        json
    ], {
        type: "application/json"
    }))
    formData.append('file', picture)

    // return axios.post(API_URL + "signup", {
    //     
    // });
    return axios({
        method: 'POST',
        url: API_URL + "signup",
        data: formData
    })
};

const login = (email, password) => {
    return axios
        .post(API_URL + "signin", {
            email,
            password,
        })
        .then((response) => {
            if (response.data.accessToken) {
                localStorage.setItem("user", JSON.stringify(response.data));
            }
            return response.data;
        });
};

const logout = () => {
    localStorage.removeItem("user");
};

const getCurrentUser = () => {
    return JSON.parse(localStorage.getItem("user"));
};

export default {
    register,
    login,
    logout,
    getCurrentUser
};
