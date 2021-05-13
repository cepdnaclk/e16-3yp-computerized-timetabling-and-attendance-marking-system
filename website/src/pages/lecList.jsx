import React, { Component } from "react";
import NavBar from "../components/navbar";
import LeclistCard from "../components/lecListCard";
import bgImage from "../images/bg4.jpg";
import Footer from "../components/footer";
import "../css/lecList.css";
import axios from "axios";
import { Redirect } from "react-router-dom";
import LoadingComponent from "../components/loadingComponent";

class LecList extends Component {
  state = {
    lecturerData: [],
    loading: false,
    loading1: false,
  };

  doSomething = (lectID) => {
    //console.log(lectID)
    this.setState({ loading: false });

    this.setState({
      loading1: true,
    });
  };

  findLecSchedule = () => {};

  componentDidMount() {
    this.setState(
      {
        lecturerData: JSON.parse(localStorage.getItem("leclist")).leclist,
      },
      () => {
        console.log("my lecturerData = ", this.state.lecturerData);
        this.setState({ loading: true });
      }
    );
    console.log();
  }
  render() {
    if (this.state.loading1) return <Redirect to="/admintimetable" />;
    if (this.state.loading === false)
      return <LoadingComponent></LoadingComponent>;

    return (
      <div>
        <NavBar pageName="Lecturer List" />
        <img
          src={bgImage}
          className="homeloginImg"
          alt="background image"
        ></img>

        <div className="ll-outer">
          <div className="ll-inner">
            {this.state.lecturerData.map((data, index) => (
              <LeclistCard
                key={index}
                groupName={data.firstName + " " + data.lastName}
                func={this.doSomething}
                lectID={data.lectID}
              ></LeclistCard>
            ))}
          </div>
        </div>

        <Footer />
      </div>
    );
  }
}

export default LecList;
