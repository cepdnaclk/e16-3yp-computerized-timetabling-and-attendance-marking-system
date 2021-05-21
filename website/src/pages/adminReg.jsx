
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
import "../css/adminReg.css"

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
            <NavBar pageName="Admin Registration" />
            <img src={bgImage} className="homeloginImg"></img>
			<div className = "adminreg-outer">
				<div className = "adminreg-inner">
					<PageHeader
						title="Admin Registration"
						icon={<PeopleOutlineTwoToneIcon fontSize="large" />}
					/>
					<Paper className={classes.pageContent}>
						<AdminForm />
					</Paper>
				</div>
			</div>
            <Footer/>
        </>
    )
}
