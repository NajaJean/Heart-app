import axios from "axios";

export default axios.create({
  baseURL: "http://165.22.31.227:8080/api",
  withCredentials: false
});
