import axios from "axios";

const baseUrl = "http://localhost:8080/";
class AdminService {
  getAllCustomer() {
    let myheader = {
      "content-Type": "application/json",
      withCredentials: true,
      //   Authorization: `Bearer ${JSON.parse(localStorage.getItem("user")).jwt}`,
    };
    return axios.get(baseUrl + "admin/getallcustomer", {
      headers: myheader,
    });
  }
  getAllOwner() {
    let myheader = {
      "content-Type": "application/json",
      withCredentials: true,
      //   Authorization: `Bearer ${JSON.parse(localStorage.getItem("user")).jwt}`,
    };
    return axios.get(baseUrl + "admin/getallowner", {
      headers: myheader,
    });
  }
  adminLogin(e, p) {
    console.log("in onwerlogin method" + e, p);
    let myheader = { "content-Type": "application/json" };
    // console.log("in the method of loginrOwner");
    let admin = { email: e, password: p };
    return axios.post(baseUrl + "admin/login", admin, { headers: myheader });
  }
  editAdmin(admin) {
    let myheader = {
      "content-Type": "application/json",
      withCredentials: true,
      Authorization: `Bearer ${JSON.parse(localStorage.getItem("user")).jwt}`,
    };
    // console.log("in the method of registerOwner")
    return axios.put(baseUrl + `admin/updateadmin/${admin.adminId}`, admin, {
      headers: myheader,
    });
  }
  verifyOwner(id) {
    let myheader = {
      "content-Type": "application/json",
      withCredentials: true,
      Authorization: `Bearer ${JSON.parse(localStorage.getItem("user")).jwt}`,
    };
    // console.log("in the method of registerOwner")
    return axios.put(baseUrl + `admin/verifyowner/${id}`, {
      headers: myheader,
    });
  }
  verifyCustomer(id) {
    let myheader = {
      "content-Type": "application/json",
      withCredentials: true,
      Authorization: `Bearer ${JSON.parse(localStorage.getItem("user")).jwt}`,
    };
    // console.log("in the method of registerOwner")
    return axios.put(baseUrl + `admin/verifycustomer/${id}`, {
      headers: myheader,
    });
  }
}
export default new AdminService();
