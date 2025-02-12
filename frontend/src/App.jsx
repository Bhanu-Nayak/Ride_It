import { Navigate, Route, Routes } from "react-router-dom";
import "./App.css";
import PaymentRejected from "./components/Payment/PaymentRejected";
import PaymentSuccess from "./components/Payment/PaymentSuccess";
import AboutPage from "./components/aboutPage/AboutPage";
import BookingHistory from "./components/bookingHistory/BookingHistory";
import BookingSummary from "./components/bookingSummary/BookingSummary";
import AdminPanel from "./components/dashBoard/AdminPanel";
import CustomerProfile from "./components/dashBoard/CustomerProfile";
import OwnerDashboard from "./components/dashBoard/OwnerDashboard";
import HomeComponent from "./components/homeComponent/HomeComponent";
import ImageTest from "./components/imageTest/ImageTest";
import AdminLogin from "./components/loginComponent/AdminLogin";
import Login from "./components/loginComponent/Login";
import OwnerLogin from "./components/loginComponent/OwnerLogin";
import LogoutPage from "./components/logoutComponent/LogoutPage";
import Navbar from "./components/navbar/Navbar";
import CustomerRegisterPage from "./components/registerComponent/CustomerRegisterPage";
import OwnerRegistrationPage from "./components/registerComponent/OwnerRegistrationPage";
import SearchPageComponent from "./components/searchPageComponent/SearchPageComponent";
import Unauthorized from "./components/unAuthorized/unAuthorized";
import AddBikeForm from "./components/updateComponent/AddBikeForm";
import EditAdmin from "./components/updateComponent/EditAdmin";
import EditBike from "./components/updateComponent/EditBike";
import EditCustomer from "./components/updateComponent/EditCustomer";
import EditOwner from "./components/updateComponent/EditOwner";
import UpdateProfilePhoto from "./components/updateComponent/UpdateProfilePhoto";
import { useEffect, useState } from "react";

//javascript - html- jsx
function App() {
  // const [refresh, setRefresh] = useState(false);
  const user = localStorage.getItem("user");
  let currUser;

  if (user != null) {
    currUser = JSON.parse(user);
  }
  return (
    <div>
      {user != null ? (
        <Navbar user={currUser.name} role={currUser.role} />
      ) : (
        <Navbar />
      )}
      <Routes>
        <Route
          path="/"
          element={<Navigate replace to="/home"></Navigate>}
        ></Route>
        <Route path="/home" element={<HomeComponent />}></Route>
        <Route path="/unauthorized" element={<Unauthorized />}></Route>
        <Route path="/login" element={<Login />}></Route>
        <Route path="/logout" element={<LogoutPage />}></Route>
        <Route path="/ownerlogin" element={<OwnerLogin />}></Route>
        <Route path="/register" element={<CustomerRegisterPage />}></Route>
        <Route path="/ownerreg" element={<OwnerRegistrationPage />}></Route>
        <Route path="/about" element={<AboutPage />}></Route>
        <Route path="/search" element={<SearchPageComponent />}></Route>
        <Route path="/test" element={<ImageTest />}></Route>
        <Route path="/owner" element={<OwnerDashboard />}></Route>
        <Route path="/owner/edit" element={<EditOwner />}></Route>
        <Route path="/owner/addbike" element={<AddBikeForm />}></Route>
        <Route path="owner/editbike" element={<EditBike />}></Route>
        <Route path="/profile" element={<CustomerProfile />}></Route>
        <Route path="/summary" element={<BookingSummary />}></Route>
        <Route path="/profile/editcustomer" element={<EditCustomer />}></Route>
        <Route
          path="/profile/profilephoto"
          element={<UpdateProfilePhoto />}
        ></Route>
        <Route
          path="/profile/bookinghistory"
          element={<BookingHistory />}
        ></Route>
        <Route path="/admin" element={<AdminPanel />}></Route>
        <Route path="/admin/edit" element={<EditAdmin />}></Route>
        <Route path="/adminlogin" element={<AdminLogin />}></Route>
        <Route path="/success" element={<PaymentSuccess />}></Route>
        <Route path="/rejected" element={<PaymentRejected />}></Route>
      </Routes>
    </div>
  );
}

export default App;
