import React, { Component } from 'react';
import NavBar from '../components/navbar'
import LeclistCard from '../components/lecListCard'
import bgImage from '../images/bg4.jpg'
import Footer from '../components/footer'
import '../css/lecList.css'
import axios from 'axios';
import LoadingComponent from "../components/loadingComponent"
import {Redirect} from "react-router-dom"

const GET_LECTURER_LIST_URI = "/lec/find/all";
const FIND_LEC_SCHEDULE_URL = "/schedule/findscheduledetailsbylecturer/";
const LECT_ALL_COURSES_URL = "/lec/findallcoursesbylectureid/";

class LecList extends Component {
    state = { 
        lecturerData:[],
        loading:false,
        l1:false,
        l2:false
     }

    doSomething = (lectID)=>{
        //console.log(lectID)
        this.setState({loading:false})
        this.findLecSchedule(lectID)
    }

    findLecSchedule = (lectID)=> {
        let lec_tt_url = FIND_LEC_SCHEDULE_URL + lectID;
        const auth = "Bearer " + localStorage.getItem("token");
        axios
        .get(lec_tt_url, {
            headers: {
            Authorization: auth,
            },
        })
        .then((response) => {
            //console.log("timetable response data = ", response);
            localStorage.setItem("timeTable", JSON.stringify(response.data));
            this.setState({l1:true})
    
        })
        .catch((error) => {
            console.log("error =", error);
        });

        let lectURL = LECT_ALL_COURSES_URL + lectID

        axios
        .get(lectURL, {
            headers: {
            Authorization: auth,
            },
        })
        .then((response) => {
            //console.log(response.data);
            localStorage.setItem(
            "lecCourses",
            JSON.stringify({ courses: response.data })
            );
            this.setState({l2:true})
            
        })
        .catch((error) => {
            console.log("error =", error);
        });
    }
    
    componentDidMount(){
        
        const auth = "Bearer "+ localStorage.getItem('token');

        axios.get(GET_LECTURER_LIST_URI, {
            headers: {
                'Authorization': auth
            }
            })
            .then(
                (res)=>{
                    console.log(res);
                    this.setState({
                        lecturerData:res.data,
                        loading:true
                    })

                    
                }
            )
            .catch(e=>{
                console.log(e);
                
            })
    }
    render() { 

        if(this.state.l1 && this.state.l2) return <Redirect to="/lectimetable" />
        else if(this.state.loading === false) return <LoadingComponent></LoadingComponent>


        return ( 

            
            <div>
                <NavBar pageName="Lecturer List" />
                <img src={bgImage} className="homeloginImg" alt="background image"></img>


                <div className="ll-outer">
                    <div className="ll-inner">

                        {this.state.lecturerData.map(data => <LeclistCard groupName={data.userName} func={this.doSomething} lectID={data.lectID}></LeclistCard>)}
    
                    </div>
                </div>
            
             <Footer/>
            </div>
         );
    }
}
 
export default LecList;