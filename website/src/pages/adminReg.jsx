
import React from 'react'
import AdminForm from "./adminForm";
import PageHeader from "../components/PageHeader";
import PeopleOutlineTwoToneIcon from '@material-ui/icons/PeopleOutlineTwoTone';
import { Paper,makeStyles } from '@material-ui/core';
import SideMenu from "../components/SideMenu"
import { CircularProgress } from '@material-ui/core';
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

export default function Employees() {

    const classes = useStyles();

    return (
        <>
            <NavBar pageName="Admin Registration" />
            <img src={bgImage} className="homeloginImg"></img>

            <PageHeader
                title="Admin Registration"
                icon={<PeopleOutlineTwoToneIcon fontSize="large" />}
            />
            <Paper className={classes.pageContent}>
                <AdminForm />
            </Paper>
            <Footer/>
        </>
    )
}
