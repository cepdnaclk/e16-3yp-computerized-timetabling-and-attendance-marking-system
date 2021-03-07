import React from 'react'
import { useState } from 'react'
import { Grid,Paper, Avatar, TextField, Button, Typography,Link } from '@material-ui/core'
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import { Redirect } from 'react-router';
import { useForm} from '../components/useForm';
import Controls from "../components/controls/Controls";
import {makeStyles } from '@material-ui/core';
import axios from 'axios'
import { CircularProgress } from '@material-ui/core';
import Snackbar from '@material-ui/core/Snackbar';
import MuiAlert from '@material-ui/lab/Alert';




const LOGIN_REST_API_URL = '/login';
const STU_ID_FROM_SESSION_URL = "/student/getdetailsfromsession";
const LEC_ID_FROM_SESSION_URL = '/lecturer/getdetailsfromsession';


const initialFValues = {
  userName: '',
  password: '',
 
  
}

const loading = {
  isLoading : false,
  errorMsg:null,
  isLoggedAdmin: false,
  isLoggedStu : false,
  isLoggedLecturer : false,
  
}


const Login=()=>{

  const [errorObj,setLoading] = useState(loading);
  const [open, setOpen] = React.useState(false);
  const avatarStyle={backgroundColor:'#1bbd7e'}
  const btnstyle={opacity:1,marginTop:"3%",backgroundColor: '#1bbd7e'}
  const useStyles = makeStyles(theme => ({
        pageContent: {
            margin: theme.spacing(5),
            padding: theme.spacing(3),
            padding :20,
            height:'45vh',
            width:"25%",
            borderRadius: "25px", 
            margin:"auto",
            backgroundColor: 'hsla(0, 0%, 90%, 0.4)'
        
        },
        root: {
          width: '100%',
          '& > * + *': {
            marginTop: theme.spacing(2),
          },
        },
  }))

  const classes = useStyles();
  

  const validate = (fieldValues = values) => {
    let temp = { ...errors }

    if ('userName' in fieldValues){
      temp.userName = (fieldValues.userName ) ? "" : "This field is required."

    }
        

    if ('password' in fieldValues){

      temp.password = (fieldValues.password ) ? "" : "This field is required."

    }
    

    setErrors({
      ...temp
    })

    if (fieldValues == values){
      
      return Object.values(temp).every(x => x == "")

    }
  }

  const {
    values,
    setValues,
    errors,
    setErrors,
    handleInputChange,
    resetForm
  } = useForm(initialFValues, true, validate);

  const callBack=()=>{
    
    setLoading({
      isLoading:false,
    })
    console.log(errorObj)
    setOpen(true)

  }


  const handleSubmit=(e)=>{

    e.preventDefault()
    
    if (validate()){
      
      console.log(values)
      setLoading({isLoading:true});
      axios.post(LOGIN_REST_API_URL, values)
      .then( response => {
            

            if(response.data.token&&response.data.role){

                
                localStorage.setItem('token', response.data.token);

                if(response.data.role==="ROLE_STUDENT"){
                  const auth = "Bearer "+ localStorage.getItem('token');
                  axios
                    .get(STU_ID_FROM_SESSION_URL,{
                        headers: {
                          'Authorization': auth
                        }
                      })
                    .then((response) => {
                      localStorage.setItem("sid", response.data.result1);
                      localStorage.setItem("sfn", response.data.result2);
                      localStorage.setItem("sen", response.data.result3);
                      localStorage.setItem("sln", response.data.result4);
                      setLoading({isLoggedStu:true,isLoading:false})
                      
                      
                      
                    })
                    .catch((error) => {
                      console.log("error =", error);
                    });

                    
            
                }

                else if(response.data.role==="ROLE_ADMIN"){
                  
                  setLoading({isLoggedAdmin:true,isLoading:false})
                  
                    
                }
                else if(response.data.role=="ROLE_LECTURER"){

                  const auth = "Bearer "+ localStorage.getItem('token');
        
                  axios
                    .get(LEC_ID_FROM_SESSION_URL,{
                        headers: {
                          'Authorization': auth
                        }
                      })
                    .then((response) => {
                        console.log(response.data);
                      localStorage.setItem("lid", response.data.result1);
                      localStorage.setItem("lfn", response.data.result2);
                      localStorage.setItem("lln", response.data.result3);
                      setLoading({isLoggedLecturer: true,isLoading:false});
                      })
                      .catch((error) => {
                        console.log("error =", error);
                      });

                      


                }
                  
            }}).catch( error => {
              if (error.response.status===401){
              callBack()
              }
            })
    }

  }





  const handleClose = (event, reason) => {
    if (reason === 'clickaway') {
      return;
    }
    setOpen(false);
  };

  
function Alert(props) {
  return <MuiAlert elevation={6} variant="filled" {...props} />;
}

  return(
    <>
        {errorObj.isLoggedAdmin?<Redirect to = {{pathname:"adminpanel"}}/>:''}
        {errorObj.isLoggedStu?<Redirect to = {{pathname:"home"}}/>:''}
        {errorObj.isLoggedLecturer?<Redirect to = {{pathname:"lecturerdashboard"}}/>:''}
        

        <Paper className={classes.pageContent}>
          <Grid align='center'>
                <Avatar style={avatarStyle}><LockOutlinedIcon/></Avatar>
              <h2>Sign In</h2>
          </Grid>
          <Grid container>
            <Grid item className={classes.root}>

              <Controls.InputLogin 
                placeholder='Enter User Name' 
                variant="outlined" 
                name="userName"
                label="User Name"
                value={values.userName}
                onChange={handleInputChange}
                error={errors.userName}
                
              />

            <div style={{height:5}}></div>

              <Controls.InputLogin
                placeholder='Enter Password' 
                variant="outlined" 
                name="password"
                label="Password"
                value={values.password}
                onChange={handleInputChange}
                error={errors.password}
                type="password"
                
              />  
            

            
            <Button 
              style={{textColor:"white"}} 
              type='submit' 
              variant="contained" 
              style={btnstyle} 
              onClick={handleSubmit}
              disableFocusRipple={true}
              disabled = {errorObj.isLoading}
              fullWidth> {
                errorObj.isLoading ? 
                <CircularProgress style={{'color':'#008000'}} size={24}  />:"Sign In"                        
            }
            </Button>

            

          </Grid>
          </Grid>

        </Paper>

            <div className={classes.root}>

              <Snackbar open={open} autoHideDuration={6000} onClose={handleClose}>
                <Alert onClose={handleClose} severity="error">
                  Username or Password is Incorrect!
                </Alert>
              </Snackbar>
              
          
            </div>
      
    </>
  )

}
export default Login