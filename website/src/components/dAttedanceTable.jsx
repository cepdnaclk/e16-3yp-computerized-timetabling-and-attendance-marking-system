import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableContainer from "@material-ui/core/TableContainer";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import Paper from "@material-ui/core/Paper";

const useStyles = makeStyles({
  table: {
    minWidth: 650,
  },
  root: {
    opacity: 0.8,
  },
});

export default function DAttendanceTable(props) {
  const classes = useStyles();

  const generateAttendance = (data, index) => {
    // console.log(res);
    // console.log(props.data[0]);
    let lab_or_lecture = props.data[index].lab_or_lecture;
    let present = props.data[index].present;

    if (
      (lab_or_lecture === 0 && data === "lab") ||
      (lab_or_lecture === 1 && data === "lecture")
    ) {
      return "-";
    }
    return present ? 1 : 0;
  };

  return (
    <TableContainer component={Paper} className={classes.root}>
      <Table className={classes.table} aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell align="center" rowSpan={2}>
              Date
            </TableCell>
            <TableCell align="center" rowSpan={2}>
              Time
            </TableCell>
            <TableCell align="center" colSpan={2}>
              Attendance
            </TableCell>
          </TableRow>
          <TableRow>
            <TableCell align="center">Lecture</TableCell>
            <TableCell align="center">Lab</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {props.data.map((attendanceItem, index) => (
            <TableRow key={attendanceItem.name}>
              <TableCell component="th" scope="row" align="center">
                {attendanceItem.date}
              </TableCell>
              <TableCell align="center">{attendanceItem.time}</TableCell>
              <TableCell align="center">
                {generateAttendance("lecture", index)}
              </TableCell>
              <TableCell align="center">
                {generateAttendance("lab", index)}
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}
