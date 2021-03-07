import React from 'react'
import LecForm from "./lecForm";
import PageHeader from "../components/PageHeader";
import PeopleOutlineTwoToneIcon from '@material-ui/icons/PeopleOutlineTwoTone';
import { Paper,makeStyles } from '@material-ui/core';
import NavBar from "../components/navbar";
import bgImage from '../images/bg4.jpg'
import Footer from '../components/footer'


const useStyles = makeStyles(theme => ({
    pageContent: {
        margin: theme.spacing(5),
        padding: theme.spacing(3),
        marginLeft:"10%",
        backgroundColor: 'hsla(0, 0%, 100%, 0.4)',
    }
}))

export default function Lecs() {

    const classes = useStyles();

    return (
        <div>

            <NavBar pageName="Lecturer Registration" />
            <img src={bgImage} className="homeloginImg"></img>
          
            <PageHeader
                title="Lecturer Registration"
                icon={<PeopleOutlineTwoToneIcon fontSize="large" />}
            />
            <Paper className={classes.pageContent}>
                <LecForm />
            </Paper>
            <Footer/>
            
        </div>
    )
}
