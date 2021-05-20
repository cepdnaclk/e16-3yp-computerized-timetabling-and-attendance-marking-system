import React from 'react'
import LecForm from "./lecForm";
import PageHeader from "../components/PageHeader";
import PeopleOutlineTwoToneIcon from '@material-ui/icons/PeopleOutlineTwoTone';
import { Paper,makeStyles } from '@material-ui/core';
import NavBar from "../components/navbar";
import bgImage from '../images/bg4.jpg'
import Footer from '../components/footer'
import "../css/lcReg.css"


const useStyles = makeStyles(theme => ({
    pageContent: {
        align:"center",
        padding: theme.spacing(3),
        backgroundColor: 'transparent',
    }
}))

export default function Lecs() {

    const classes = useStyles();

    return (
        <div>

            <NavBar pageName="Lecturer Registration" />
            <img src={bgImage} className="homeloginImg"></img>
			<div className="lcReg-outer">
				<div className="lcReg-inner">
					<PageHeader
						title="Lecturer Registration"
						icon={<PeopleOutlineTwoToneIcon fontSize="large" />}
					/>
					<Paper className={classes.pageContent}>
						<LecForm />
					</Paper>
				</div>
			</div>
            <Footer/>
            
        </div>
    )
}
