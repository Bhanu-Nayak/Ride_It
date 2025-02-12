import AdminService from "../../../service/AdminService";
import OwnerService from "../../../service/OwnerService";
import { Button } from "../ui/button";
import { Card, CardContent, CardHeader, CardTitle } from "../ui/card";
import { Input } from "../ui/input";
import { motion } from "framer-motion";
import { useState, useEffect } from "react";
import { FaMotorcycle } from "react-icons/fa";
import { Link, useNavigate } from "react-router-dom";

const Login = () => {
  // console.log("test");
  const navigate = useNavigate();
  useEffect(() => {
    const user = localStorage.getItem("user");
    let currUser;
    if (user != null) {
      currUser = JSON.parse(user);
      if (currUser.role == "OWNER") navigate("/owner");
      else navigate("/profile");
    }
  }, []);
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [err, setErr] = useState("");
  // (result)=>{console.log("login successfull "+JSON.stringify(result.data))
  const handleLogin = (e) => {
    e.preventDefault();
    OwnerService.ownerLogin(email, password)
      .then((res) => {
        console.log(res.data);
        localStorage.setItem("user", JSON.stringify(res.data));
        localStorage.setItem("userpp", JSON.stringify(res.data.profilePhoto));
        navigate("/owner");
      })
      .catch((err) => {
        setErr(err.message);
      });
  };

  return (
    <div className="flex min-h-screen items-center justify-center bg-gradient-to-r from-gray-800 to-gray-800">
      <motion.div
        initial={{ opacity: 0, y: -20 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.5 }}
      >
        <Card
          className="w-96 p-6 shadow-xl rounded-2xl text-white" //bg-gray-950
          style={{ backgroundColor: "#d4dfbf" }}
        >
          <CardHeader className="text-center">
            <CardTitle className="flex justify-center items-center gap-2 text-2xl font-bold">
              <FaMotorcycle className="text-orange-400" />
              Owner Login
            </CardTitle>
          </CardHeader>
          <CardContent>
            <form onSubmit={handleLogin} className="space-y-4">
              <div>
                <Input
                  type="email"
                  placeholder="Email"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  required
                  className="w-full p-3 rounded-md bg-gray-800 border border-gray-700 focus:ring-2 focus:ring-yellow-500"
                />
              </div>
              <div>
                <Input
                  type="password"
                  placeholder="Password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  required
                  className="w-full p-3 rounded-md bg-gray-800 border border-gray-700 focus:ring-2 focus:ring-yellow-500"
                />
              </div>
              <span>{err}</span>
              <Button
                type="submit"
                className="w-full bg-orange-400 hover:bg-yellow-600 text-black font-bold py-2 px-4 rounded-lg transition duration-300"
              >
                Login
              </Button>
            </form>
            <p className="text-gray-400 text-center mt-4">
              Don't have an account?{" "}
              <a href="/ownerreg" className="text-orange-400 hover:underline">
                Sign Up
              </a>
            </p>
          </CardContent>
        </Card>
      </motion.div>
    </div>
  );
};
export default Login;
