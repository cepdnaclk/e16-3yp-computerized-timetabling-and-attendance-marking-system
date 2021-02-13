import React, { Component } from 'react';
import NavBar from '../components/navbar'
import CourseList from "../components/courseList"
import UserCard from "../components/userCard"
import axios from 'axios'
import '../css/home.css'
import bgImage from '../images/bg4.jpg'

const STUDENT_HOME_PAGE_URI =  '/student'; 

class Home extends Component {
    state = {
        courses:[],
        details:[],
        searchWord:null
    }

    
    componentDidMount(){
        this.setState({courses:[
            {courseNum:"CO321",courseName:"EMBEDED SYSTEMS"},
            {courseNum:"CO322",courseName:"DATA STRUCTURES AND ALGORYTHMS"},
            {courseNum:"CO323",courseName:"COMPUTER COMINUCATION NETWORKS II"},

        ]});
        this.setState({details:[
            {   
                id:1,
                nameTag:"Name:",
                value: "Saubhagya"

            },
            {   
                id:2,
                nameTag:"E Number:",
                value:"E/16/242"

            },
            {   
                id:3,
                nameTag:"Password:",
                value:"samplePassword"

            }
        ]});
        this.setState({searchWord:''})

    }

    constructor(props){
        super(props);
        
        /*const auth = "Bearer "+ localStorage.getItem('token');
        axios.get(STUDENT_HOME_PAGE_URI, {
                            headers: {
                              'Authorization': auth
                            }
                          }).then( response =>{
                              if(response.status === 200){

                                this.setState({courses:response.data.courses})
                                this.setState({details:[
                                    {   
                                        id:1,
                                        nameTag:"Name:",
                                        value: response.data.student.userName
                        
                                    },
                                    {   
                                        id:2,
                                        nameTag:"E Number:",
                                        value:response.data.student.eNo
                        
                                    },
                                    {   
                                        id:3,
                                        nameTag:"Password:",
                                        value:"samplePassword"
                        
                                    }
                                ]}

                                )
                                console.log(response);

                              }
                            
                          })*/
    }
    

    render() { 
        
        
        return (  
            <div className="home-outer">
                <NavBar pageName="Home" />
                <img src={bgImage} className="homeloginImg"></img>
                <h2 className="hm-title">My Attendance records</h2>
                <CourseList courses={this.state.courses} sw={this.state.searchWord}/>
                <UserCard data={["Saubhagya","E/16/242"]} />
            </div>

        );
    }
}
 
export default Home;