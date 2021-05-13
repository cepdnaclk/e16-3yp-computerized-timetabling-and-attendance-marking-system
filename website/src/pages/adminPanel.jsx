import React, { Component } from "react";
import NavBar from "../components/navbar";
import AdminButton from "../components/adminButton";
import "../css/adminPanel.css";
import b1 from "../images/studentbt.svg";
import b2 from "../images/lecbt.svg";
import b3 from "../images/adminbt.svg";
import b4 from "../images/deletebt.svg";
import b5 from "../images/upadatebt.svg";
import b6 from "../images/ttbt.svg";
import bgImage from "../images/bg4.jpg";
import Footer from "../components/footer";
import axios from "axios";
import LoadingComponent from "../components/loadingComponent";

const GET_LECTURER_LIST_URI = "/lec/find/all";
const FIND_LEC_SCHEDULE_URL = "/schedule/findscheduledetailsbylecturer/";
const LECT_ALL_COURSES_URL = "/lec/findallcoursesbylectureid/";

class AdminPanel extends Component {
  state = {
    courses: [],
    details: [],
    timeTable: [],
    leclist: [],
    loading0: false,
    loading1: false,
    loading2: false,
  };

  componentDidMount() {
    const auth = "Bearer " + localStorage.getItem("token");

    axios
      .get(GET_LECTURER_LIST_URI, {
        headers: {
          Authorization: auth,
        },
      })
      .then((response) => {
        console.log("leclist = ", response.data);
        localStorage.setItem(
          "leclist",
          JSON.stringify({ leclist: response.data })
        );
        this.setState({ leclist: response.data }, () => {
          this.setState({ loading0: true });
        });
      })
      .catch((error) => {
        console.log("error =", error);
      });

    const LECTURER_ALL_COURSES_URL =
      LECT_ALL_COURSES_URL + localStorage.getItem("lid");
    axios
      .get(LECTURER_ALL_COURSES_URL, {
        headers: {
          Authorization: auth,
        },
      })
      .then((response) => {
        // console.log(response);
        localStorage.setItem(
          "lecCourses",
          JSON.stringify({ courses: response.data })
        );
        this.setState({ courses: response.data }, () => {
          this.setState({ loading1: true });
        });
      })
      .catch((error) => {
        console.log("error =", error);
      });

    let lec_tt_url = FIND_LEC_SCHEDULE_URL + localStorage.getItem("lid");
    axios
      .get(lec_tt_url, {
        headers: {
          Authorization: auth,
        },
      })
      .then((response) => {
        // console.log("timetable response data = ", response);
        localStorage.setItem("timeTable", JSON.stringify(response.data));
        this.setState({ timeTable: response.data }, () => {
          this.setState({ loading2: true });
        });
      })
      .catch((error) => {
        console.log("error =", error);
      });
  }

  render() {
    if (this.state.loading0 && this.state.loading1 && this.state.loading2) {
      return (
        <React.Fragment>
          <img src={bgImage} className="homeloginImg"></img>
          <NavBar pageName="Admin Panel" />
          <div className="admin-outer">
            <div className="buttonPanel">
              <AdminButton
                t1="CREATE"
                t2="STUDENT ACCOUNTS"
                val="1"
                img={b1}
              ></AdminButton>
              <AdminButton
                t1="CREATE"
                t2="LECTURER ACCOUNTS"
                val="2"
                img={b2}
              ></AdminButton>
              <AdminButton
                t1="CREATE"
                t2="ADMIN ACCOUNTS"
                val="3"
                img={b3}
              ></AdminButton>
              <AdminButton
                t1="DELETE"
                t2="ACCOUNTS"
                val="4"
                img={b4}
              ></AdminButton>
              <AdminButton
                t1="STUDENT"
                t2="GROUPS"
                val="5"
                img={b5}
              ></AdminButton>
              <AdminButton
                t1="MODIFY"
                t2="TIMETABELS"
                val="6"
                img={b6}
              ></AdminButton>
            </div>
          </div>

          <Footer />
        </React.Fragment>
      );
    } else {
      return <LoadingComponent></LoadingComponent>;
    }
  }
}

export default AdminPanel;
