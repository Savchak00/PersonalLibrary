import styles from './LogIn.module.css';
import {useState} from 'react';
import logo from '../logo.svg'
import Axios from "axios";
import { useNavigate } from "react-router-dom";

export const LogIn = (props) => {
    const [name,setName] = useState("");
    const [surName,setSurName] = useState("");
    const [email,setEmail] = useState("");
    let navigate = useNavigate(); 
    
    const handleChangeName = (event) => {
        setName(event.target.value);
    };

    const handleChangeSurName = (event) => {
        setSurName(event.target.value);
    };

    const handleChangeEmail = (event) => {
        setEmail(event.target.value);
    };

    const buttonClicked = () => {
        if (name == "" || surName == "" || email == "") {
            alert("Fields cannot be empty");
        } else {
            Axios.post("http://172.20.10.8:8080/api/logIn/logInOrCreate", {
                "firstName" : name,
                "lastName" : surName,
                "email" : email
            })
            .then((response) => {
                sessionStorage.setItem("userId", response.data["responseData"]["userId"]);
                navigate('library');
            });
        }
    };

    return (
        <div className={styles.LogIn}>
            <img className={`${styles.CenterBlock} ${styles.LogInLogo} ${styles.Image}`} src={logo} alt="Logo" />
            <div className={styles.InputDiv}>
                <input className={styles.Input} onChange={handleChangeName} placeholder='Name' style={{backgroundColor:"#DAC9F5"}}/>
                <input className={styles.Input} onChange={handleChangeSurName} placeholder='Surname' style={{backgroundColor:"#FEF1CF"}}/>
                <input className={styles.Input} onChange={handleChangeEmail} placeholder='Email' style={{backgroundColor:"#D7FFD7"}}/>
            </div>
            <button className={`${styles.Button} ${styles.CenterBlock}`} onClick={buttonClicked} >Log In</button>
        </div>
    );
};