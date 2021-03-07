import React, { Component } from 'react';
import IconButton from '@material-ui/core/IconButton';
import AddIcon from '@material-ui/icons/Add';

class AddStudent extends Component {
    state = {  }

    displayENumber = (searchWord) => {

        //console.log(this.props.numberList)
        //console.log(searchWord)
        var tmpWord = ''
        if(searchWord !== null){
            tmpWord = searchWord.toUpperCase()
        }
        const result = this.props.numberList.filter( number => number.toUpperCase() === tmpWord);
        console.log(result)

        if(result.length !== 0){
            return (
                <div className="addstudent-outer">
                    <p className="addstudent-para">{this.props.searchWord}</p>
                    <IconButton aria-label="add" style={{display: "inline-block"}}>
                            <AddIcon />
                    </IconButton>
                </div>
            );

        }
        else{
            return <div></div>

        }

    }
    
    render() { 
        return this.displayENumber(this.props.searchWord);
    }
}
 
export default AddStudent;