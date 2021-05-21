import React from 'react'
import StudentForm from "./stdForm";
import PageHeaderStudent from "../components/PageHeaderStudent";
import PeopleOutlineTwoToneIcon from '@material-ui/icons/PeopleOutlineTwoTone';
import { Paper,makeStyles } from '@material-ui/core';
import bgImage from '../images/bg4.jpg'
import NavBar from "../components/navbar";
import Footer from '../components/footer'
import "../css/stdRg.css"


const useStyles = makeStyles(theme => ({
    pageContent: {
        padding: theme.spacing(3),
        backgroundColor: 'transparent',
      
      
    }
}))

export default function Employees() {

    const classes = useStyles();

    return (
        <>
            <img src={bgImage} className="homeloginImg"></img>
            <NavBar pageName="Student Registration" />
			<div className = "stdRg-outer">
				<div className = "stdRg-inner">
            <PageHeaderStudent
                title="Student Registration"
                icon={<PeopleOutlineTwoToneIcon fontSize="large" />}
            />
            <Paper className={classes.pageContent}>
                <StudentForm />
            </Paper>
			</div>
			</div>
            <Footer/>
        </>
    )
}
