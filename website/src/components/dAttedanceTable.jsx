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

export default function DAttendanceTable(props) {
  const classes = useStyles();

  return (
    <TableContainer component={Paper} className={classes.root}>
      <Table className={classes.table} aria-label="simple table">
        <TableHead>
          <TableRow >
            <TableCell align="center" rowSpan={2}>Date</TableCell>
            <TableCell align="center" rowSpan={2}>Time</TableCell>
            <TableCell align="center" colSpan={2}>Attendance</TableCell>
            
          </TableRow>
          <TableRow >
            <TableCell align="center" >Lecture</TableCell>
            <TableCell align="center" >Lab</TableCell>
            
            
          </TableRow>
        </TableHead>
        <TableBody>
          {props.data.map((row) => (
            <TableRow key={row.name} >
              <TableCell component="th" scope="row" align="center">
                {row[0]}
              </TableCell>
              <TableCell align="center">{row[1]}</TableCell>
              <TableCell align="center">{row[2]}</TableCell>
              <TableCell align="center">{row[3]}</TableCell>
              
              
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}
