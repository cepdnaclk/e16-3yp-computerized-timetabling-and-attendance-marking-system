
import React from 'react'
import AdminForm from "./adminForm";
import PageHeader from "../components/PageHeader";
import PeopleOutlineTwoToneIcon from '@material-ui/icons/PeopleOutlineTwoTone';
import { Paper,makeStyles } from '@material-ui/core';
import SideMenu from "../components/SideMenu"

const useStyles = makeStyles(theme => ({
    pageContent: {
        margin: theme.spacing(5),
        padding: theme.spacing(3),
        marginLeft:"10%"
    }
}))

export default function Employees() {

    const classes = useStyles();

    return (
        <>

            <SideMenu/>
            
            <PageHeader
                title="Admin Registration"
                icon={<PeopleOutlineTwoToneIcon fontSize="large" />}
            />
            <Paper className={classes.pageContent}>
                <AdminForm />
            </Paper>
        </>
    )
}
