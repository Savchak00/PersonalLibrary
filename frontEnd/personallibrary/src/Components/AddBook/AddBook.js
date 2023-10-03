import styles from './AddBook.module.css';
import logo from '../../logo.svg';
import photo from '../../booksPhoto.jpeg';
import TextareaAutosize from 'react-textarea-autosize';
import {useEffect} from 'react';
import Axios from "axios";
import {useState} from 'react';
import { useNavigate, useLocation } from "react-router-dom";
import { SpinnerDiamond } from 'spinners-react';

export const AddBook = (props) => {
    const [title,setTitle] = useState("");
    const [authorFirstName,setAuthorFirstName] = useState("");
    const [authorLastName,setAuthorLastName] = useState("");
    const [isbn,setISBN] = useState("");
    const [plot,setPlot] = useState("");
    const [numberOfFullReads,setNumberOfFullReads] = useState(0);
    const [additionDate, _] = useState(new Date().toJSON().slice(0, 10));
    let navigate = useNavigate(); 
    const location = useLocation();

    const [isLoading, setIsLoading] = useState(false);

    const addButtonClicked = () => {
        if (!validateISBN(isbn)) {
            alert("Isbn should be specific format!")
        } else if (title == "" || authorFirstName == "" || authorLastName == "" || plot == "") {
            alert("Fields shouldn't be empty!")
        } else {
            setIsLoading(true);
            Axios.post("http://172.20.10.8:8080/api/library/addBook", {
                "title" : title,
                "isbn" : isbn,
                "additionDate" : additionDate,
                "plot" : plot,
                "numberOfFullReads": numberOfFullReads,
                "author" : {
                    "firstName" : authorFirstName,
                    "lastName" : authorLastName
                },
                "owner" : {
                    "userId" :  sessionStorage.getItem("userId")
                }
            }).then((res) => {
                if (res.data["responseData"]["isBookAdded"]) {
                    alert(res.data["message"]);
                } else {
                    alert("Something is wrong. Information is not saved (");
                }
            }).catch((error) => {
                alert("Something is wrong. " + error);
            }); 
            setIsLoading(false);
            navigate("/library");
        }
    }

    const increaseNumberOfFullReads = () => {
        setNumberOfFullReads(numberOfFullReads + 1);
    }

    const decreaseNumberOfFullReads = () => {
        if (numberOfFullReads > 0) {
            setNumberOfFullReads(numberOfFullReads - 1);
        }
    }   

    const changeTitle = (event) => {
        setTitle(event.target.value);
    }

    const changeAuthorFirstName = (event) => {
        setAuthorFirstName(event.target.value);
    }

    const changeAuthorLastName = (event) => {
        setAuthorLastName(event.target.value);
    }

    const changeISBN = (event) => {
        setISBN(event.target.value);
    }

    const changePlot = (event) => {
        setPlot(event.target.value);
    }

    const returnToLibrary = () => {
        navigate('/library');
        console.log("aaa");
    }

    const validateISBN = (isbn) => {
        const isbnRegex = /^(?=(?:\D*\d){10}(?:(?:\D*\d){3})?$)[\d-]+$/;
    
        return isbnRegex.test(isbn);
    };

    return (
        <div>
            {isLoading ? (<SpinnerDiamond className={styles.Spinner}/>) : <div>
            <div style={{textAlign:'center'}}>
                <button style={{border:"none", background:"none"}} onClick={returnToLibrary}><img src={logo} className={`${styles.LogoBook} ${styles.Image}`} alt="Logo"/></button>
            </div>
            <div style={{textAlign:'center'}}>
                <img src={photo} className={`${styles.width80Perc} ${styles.Photo}`} alt="Books"/>
            </div>
            <div className= {`${styles.CenterBlockHorizontally} ${styles.width80Perc}`} style={{textAlign:'start'}}>
                <input className={styles.Title} placeholder="Title" onChange={changeTitle} />
            </div>
            <div className={styles.InfoBlock}>
                <div style={{width:"25%"}}>
                    <div id='vertical row'>
                        <div>
                            <p style={{padding:0, margin:0}}>Author:</p>
                            <input className={styles.Author} placeholder="First Name" onChange={changeAuthorFirstName}/>
                            <input className={styles.Author} placeholder="Last Name" onChange={changeAuthorLastName}/>
                        </div>
                        <div style={{marginTop:"11px"}}>
                            <p style={{padding:0, margin:0}}>Addition date:</p>
                            <p className={styles.Author}>{additionDate}</p>

                        </div>
                    </div>
                </div>
                <div style={{width:"25%"}}>
                    <div id='vertical row'>
                        <div>
                            <p style={{padding:0, margin:0}}>ISBN:</p>
                            <input className={styles.Author} placeholder="isbn" onChange={changeISBN}/>
                        </div>
                        <div style={{marginTop:"40px"}}>
                            <p style={{padding:0, margin:0}}>Number of full reads:</p>
                            <div style={{display:"flex", alignItems: "center"}}>
                                <text className={styles.Author} > {numberOfFullReads} </text>
                                <button className={styles.PlusMinusButton} onClick={decreaseNumberOfFullReads}>-</button>
                                <button className={styles.PlusMinusButton} onClick={increaseNumberOfFullReads}>+</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div id='plot' style={{width:"50%"}}>
                    <p style={{padding:0, margin:0}}>Plot:</p>
                    <TextareaAutosize className={styles.Plot} placeholder="Plot" onChange={changePlot}/>
                </div>
            </div>
            <div style={{textAlign:"center"}}>
                <button className={styles.Button} onClick={addButtonClicked} >Add</button>
            </div>
            </div> }
        </div>
    );
};