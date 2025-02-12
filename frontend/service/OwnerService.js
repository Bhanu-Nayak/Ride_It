import axios from "axios";

let baseUrl = "http://localhost:8080/";

class OwnerService {
  constructor() {}
  registerOwner(owner) {
    let myheader = { "content-Type": "application/json" };
    // console.log("in the method of registerOwner")
    return axios.post(baseUrl + "owner/addowner", owner, { headers: myheader });
  }
  // getall(){
  //     return axios.get(baseUrl+"owner/getallowner")
  // }
  // login
  ownerLogin(e, p) {
    console.log("in onwerlogin method" + e, p);
    let myheader = { "content-Type": "application/json" };
    // console.log("in the method of loginrOwner");
    let owner = { email: e, password: p };
    return axios.post(baseUrl + "owner/login", owner, { headers: myheader });
  }
  editOwner(owner) {
    let myheader = {
      "content-Type": "application/json",
      withCredentials: true,
      Authorization: `Bearer ${JSON.parse(localStorage.getItem("user")).jwt}`,
    };
    // console.log("in the method of registerOwner")
    return axios.put(baseUrl + `owner/updateowner/${owner.ownerId}`, owner, {
      headers: myheader,
    });
  }
  deleteOwner(id) {
    // console.log(JSON.parse(localStorage.getItem("owner")).jwt);
    let myheader = {
      "content-Type": "application/json",
      withCredentials: true,
      Authorization: `Bearer ${JSON.parse(localStorage.getItem("user")).jwt}`,
    };
    return axios.delete(baseUrl + `owner/deleteowner/${id}`, {
      headers: myheader,
    });
  }
  getOwnerBikes(id) {
    // console.log(JSON.parse(localStorage.getItem("owner")).jwt);
    let myheader = {
      "content-Type": "application/json",
      withCredentials: true,
      Authorization: `Bearer ${JSON.parse(localStorage.getItem("user")).jwt}`,
    };
    return axios.get(baseUrl + `owner/getAllbikes/${id}`, {
      headers: myheader,
    });
  }
  addNewBike(b) {
    // console.log(JSON.parse(localStorage.getItem("owner")).jwt);
    let myheader = {
      "content-Type": "application/json",
      withCredentials: true,
      Authorization: `Bearer ${JSON.parse(localStorage.getItem("user")).jwt}`,
    };
    return axios.post(baseUrl + "owner/addbike", b, {
      headers: myheader,
    });
  }
  deleteBike(id) {
    // console.log(JSON.parse(localStorage.getItem("owner")).jwt);
    let myheader = {
      "content-Type": "application/json",
      withCredentials: true,
      Authorization: `Bearer ${JSON.parse(localStorage.getItem("user")).jwt}`,
    };
    return axios.delete(baseUrl + `owner/deleteBike/${id}`, {
      headers: myheader,
    });
  }
  editBike(bike) {
    // console.log(JSON.parse(localStorage.getItem("owner")).jwt);
    let myheader = {
      "content-Type": "application/json",
      withCredentials: true,
      Authorization: `Bearer ${JSON.parse(localStorage.getItem("user")).jwt}`,
    };
    return axios.put(baseUrl + `owner/editbike/${bike.bikeId}`, bike, {
      headers: myheader,
    });
  }
}
export default new OwnerService();
