import axios from "axios";

const baseUrl = "http://localhost:8080/";
class CustomerService {
  constructor() {}
  customerRegister(customer) {
    let myheader = { "content-Type": "application/json" };
    return axios.post(baseUrl + "customer/registerCustomer", customer, {
      headers: myheader,
    });
  }

  loginCustomer(customer) {
    let myheader = { "content-Type": "application/json" };
    return axios.post(baseUrl + "customer/loginCustomer", customer, {
      headers: myheader,
    });
  }

  getCustomer(id) {
    let myheader = {
      "content-Type": "application/json",
      withCredentials: true,
      Authorization: `Bearer ${JSON.parse(localStorage.getItem("user")).jwt}`,
    };
    return axios.get(baseUrl + `customer/getcustomer/${id}`, {
      headers: myheader,
    });
  }
  updateCustomer(cust) {
    let myheader = {
      "content-Type": "application/json",
      withCredentials: true,
      Authorization: `Bearer ${JSON.parse(localStorage.getItem("user")).jwt}`,
    };
    return axios.put(baseUrl + `customer/updateCustomer/${cust.custId}`, cust, {
      headers: myheader,
    });
  }
  updateProfilePhoto(id, img) {
    let myheader = {
      "content-Type": "multipart/form-data",
      withCredentials: true,
      Authorization: `Bearer ${JSON.parse(localStorage.getItem("user")).jwt}`,
    };
    const formData = new FormData();
    formData.append("file", img);
    return axios.post(baseUrl + `customer/uploadprofilephoto/${id}`, formData, {
      headers: myheader,
    });
  }
  deleteCustomer(id) {
    let myheader = {
      "content-Type": "application/json",
      withCredentials: true,
      Authorization: `Bearer ${JSON.parse(localStorage.getItem("user")).jwt}`,
    };
    return axios.delete(baseUrl + `customer/deletecustomer/${id}`, {
      headers: myheader,
    });
  }
  bookABike(order) {
    let myheader = {
      "content-Type": "application/json",
      withCredentials: true,
      Authorization: `Bearer ${JSON.parse(localStorage.getItem("user")).jwt}`,
    };
    return axios.post(baseUrl + "customer/bookabike", order, {
      headers: myheader,
    });
  }
}
export default new CustomerService();
