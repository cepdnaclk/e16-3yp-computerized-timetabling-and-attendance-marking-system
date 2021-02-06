import React from 'react';
import Avatar from '@material-ui/core/Avatar';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import TextField from '@material-ui/core/TextField';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Checkbox from '@material-ui/core/Checkbox';
import Link from '@material-ui/core/Link';
import Grid from '@material-ui/core/Grid';
import Box from '@material-ui/core/Box';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import { createMuiTheme } from '@material-ui/core/styles';
import { ThemeProvider } from '@material-ui/styles';
import { purple } from '@material-ui/core/colors';

const thm = createMuiTheme({
    palette: {
      primary: {
        // Purple and green play nicely together.
        main: purple[500],
      },
      secondary: {
        // This is green.A700 as hex.
        main: '#228B22',
      },
      
    },
    background: 'linear-gradient(45deg, #FE6B8B 30%, #FF8E53 90%)',
   
  });

function Copyright() {
  return (
    <Typography variant="body2" color="textSecondary" align="center">
      {'Copyright © '}
      <Link color="inherit" href="https://material-ui.com/">
        UOP efc
      </Link>{' '}
      {new Date().getFullYear()}
      {'.'}
    </Typography>
  );
}

const useStyles = makeStyles((theme) => ({
  paper: {
    marginTop: theme.spacing(8),
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
  },
  avatar: {
    margin: theme.spacing(1),
    backgroundColor:thm.palette.secondary.main,
    background: 'linear-gradient(45deg, #32CD32 30%, #228B22 90%)',

  },
  form: {
    width: '100%', // Fix IE 11 issue.
    marginTop: theme.spacing(1),
  },
  submit: {
    margin: theme.spacing(3, 0, 2),
    background: 'linear-gradient(45deg, #32CD32 30%, #228B22 90%)',
    boxShadow: '0 3px 5px 2px rgba(255, 105, 135, .3)',
  },

  
}));

export default function LoginCard(props) {
  const classes = useStyles();

  return (
    <ThemeProvider theme={thm}>
    <Container component="main" maxWidth="xs" >
      <CssBaseline />
      <div className={classes.paper}>
        <Avatar className={classes.avatar} color="secondary">
          <LockOutlinedIcon backgroundColor="secondary"/>
        </Avatar>
        <Typography component="h1" variant="h5">
          Log in
        </Typography>
        <form className={classes.form} onSubmit={props.sr}>
        
          <TextField
            
            variant="outlined"
            color="secondary"
            margin="normal"
            required
            fullWidth
            id="userName"
            label="User Name"
            name="userName"
            autoComplete="email"
            autoFocus
            onChange={props.ocn}
          />
          {<h6 style={{color : "red"}}>{props.nameError}</h6>}
          
          <TextField className={classes.inputField}
            color="secondary"
            variant="outlined"
            margin="normal"
            required
            fullWidth
            name="password"
            label="Password"
            type="password"
            id="password"
            autoComplete="current-password"
            onChange={props.ocp}
          />
          {<h6 style={{color : "red"}}>{props.passError}</h6>}
          {<h6 style={{color : "red"}}>{props.loginError}</h6>}
          
          <Button
            onClick={props.sr}
            fullWidth
            variant="contained"
            color="secondary"
            className={classes.submit}
          >
            Log In
          </Button>
          
          <Grid container>
            <Grid item xs>
              <Link href="#" variant="body2" color="textSecondary">
                Forgot password?
              </Link>
            </Grid>
        
          </Grid>
        </form>
      </div>
      <Box mt={8}>
        <Copyright />
      </Box>
    </Container>
    </ThemeProvider>
  );
}