import React from 'react'
import LecForm from "./lecForm";
import PageHeader from "../components/PageHeader";
import PeopleOutlineTwoToneIcon from '@material-ui/icons/PeopleOutlineTwoTone';
import { Paper,makeStyles } from '@material-ui/core';


const useStyles = makeStyles(theme => ({
    pageContent: {
        margin: theme.spacing(5),
        padding: theme.spacing(3),
        marginLeft:"10%"
    }
}))

export default function Lecs() {

    const classes = useStyles();

    return (
        <div>
          
            <PageHeader
                title="Lecturer Registration"
                icon={<PeopleOutlineTwoToneIcon fontSize="large" />}
            />
            <Paper className={classes.pageContent}>
                <LecForm />
            </Paper>
            
        </div>
    )
}
