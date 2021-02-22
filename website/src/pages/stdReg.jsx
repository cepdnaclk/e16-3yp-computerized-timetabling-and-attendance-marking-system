import React from 'react'
import StudentForm from "./stdForm";
import PageHeaderStudent from "../components/PageHeaderStudent";
import PeopleOutlineTwoToneIcon from '@material-ui/icons/PeopleOutlineTwoTone';
import { Paper,makeStyles } from '@material-ui/core';
import bgImage from '../images/bg4.jpg'

const useStyles = makeStyles(theme => ({
    pageContent: {
        margin: theme.spacing(5),
        padding: theme.spacing(3),
       // marginLeft:"320px"
    }
}))

export default function Employees() {

    const classes = useStyles();

    return (
        <>
            
            <PageHeaderStudent
                title="Student Registration"
                icon={<PeopleOutlineTwoToneIcon fontSize="large" />}
            />
            <Paper className={classes.pageContent}>
                <StudentForm />
            </Paper>
        </>
    )
}
