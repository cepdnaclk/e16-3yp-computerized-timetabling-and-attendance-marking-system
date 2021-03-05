import React from 'react'
import StudentForm from "./stdForm";
import PageHeaderStudent from "../components/PageHeaderStudent";
import PeopleOutlineTwoToneIcon from '@material-ui/icons/PeopleOutlineTwoTone';
import { Paper,makeStyles } from '@material-ui/core';
import bgImage from '../images/bg4.jpg'
import NavBar from "../components/navbar";
import Footer from '../components/footer'


const useStyles = makeStyles(theme => ({
    pageContent: {
        margin: theme.spacing(5),
        padding: theme.spacing(3),
        backgroundColor: 'hsla(0, 0%, 100%, 0.4)',
        marginTop:'1%'
      
    }
}))

export default function Employees() {

    const classes = useStyles();

    return (
        <>
            <img src={bgImage} className="homeloginImg"></img>
            <NavBar pageName="Student Registration" />
            <PageHeaderStudent
                title="Student Registration"
                icon={<PeopleOutlineTwoToneIcon fontSize="large" />}
            />
            <Paper className={classes.pageContent}>
                <StudentForm />
            </Paper>
            <Footer/>
        </>
    )
}
