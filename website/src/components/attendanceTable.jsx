import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';

const useStyles = makeStyles({
  table: {
    minWidth: 650,

  },
  root:{
   opacity: 0.8

  }
 
});

export default function AttendanceTable(props) {
  const classes = useStyles();

  return (
    <TableContainer component={Paper} className={classes.root}>
      <Table className={classes.table} aria-label="simple table">
        <TableHead>
          <TableRow >
            <TableCell align="center">Reg No</TableCell>
            <TableCell align="center">Name</TableCell>
            <TableCell align="center">Lecture Percentage</TableCell>
            <TableCell align="center">Lab Percentage</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {props.data.map((attendanceItem) => (
            <TableRow key={attendanceItem.name} >
              <TableCell component="th" scope="row" align="center">
                {attendanceItem.regNo}
              </TableCell>
              <TableCell align="center">{attendanceItem.name}</TableCell>
              <TableCell align="center">{(Math.round(attendanceItem.attendance.lecturePercentage * 100) / 100).toFixed(2)+'%'}</TableCell>
              <TableCell align="center">{(Math.round(attendanceItem.attendance.labPercentage * 100) / 100).toFixed(2)+'%'}</TableCell>
              
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}
