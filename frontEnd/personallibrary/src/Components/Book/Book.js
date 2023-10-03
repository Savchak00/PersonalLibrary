import styles from './Book.module.css';
import logo from '../../logo.svg';
import photo from '../../booksPhoto.jpeg';
import TextareaAutosize from 'react-textarea-autosize';
import {useEffect} from 'react';
import Axios from "axios";
import {useState} from 'react';
import { useNavigate, useLocation } from "react-router-dom";
import { SpinnerDiamond } from 'spinners-react';

export const Book = (props) => {
    const [title,setTitle] = useState("");
    const [authorFirstName,setAuthorFirstName] = useState("");
    const [authorLastName,setAuthorLastName] = useState("");
    const [isbn,setISBN] = useState("");
    const [plot,setPlot] = useState("");
    const [numberOfFullReads,setNumberOfFullReads] = useState(0);
    const [additionDate, setAdditionDate] = useState(null);
    const [bookId, setBookId] = useState(-1);
    let navigate = useNavigate(); 
    const location = useLocation();

    const [isLoading, setIsLoading] = useState(false);

    useEffect(() => {
        setIsLoading(true);
        fetchBook();
        setIsLoading(false);
    },[]);

    const fetchBook = () => {
        Axios.get(`http://172.20.10.8:8080/api/book/${location.state.bookId}`, {
        })
        .then((response) => {
            let book = response.data["responseData"]["book"];
            setTitle(book["title"]);
            setAuthorFirstName(book["author"]["firstName"]);
            setAuthorLastName(book["author"]["lastName"]);
            setISBN(book["isbn"]);
            setPlot(book["plot"]);
            setNumberOfFullReads(book["numberOfFullReads"]);
            setAdditionDate(book["additionDate"]);
            setBookId(book["bookId"])
        });
    };

    const saveButtonClicked = () => {
        if (!validatePlot()) {
            alert("Plot should be less than 500 symbols!");
        } else if (!validateISBN(isbn)) {
            alert("Isbn should be specific format!");
        } else if (title == "" || authorFirstName == "" || authorLastName == "" || plot == "") {
            alert("Fields shouldn't be empty!");
        } else {
            Axios.put("http://172.20.10.8:8080/api/book/edit", {
                "title" : title,
                "isbn" : isbn,
                "additionDate" : additionDate,
                "plot" : plot,
                "numberOfFullReads": numberOfFullReads,
                "bookId": bookId,
                "author" : {
                    "firstName" : authorFirstName,
                    "lastName" : authorLastName
                }
            }).then((res) => {
                if (res.data["responseData"]["isBookEdited"]) {
                    alert("Book is changed");
                } else {
                    alert("Something is wrong. Information is not saved (");
                }
            }).catch((error) => {
                alert("Something is wrong. " + error);
            }); 
        }
    }

    const removeButtonClicked = () => {
        Axios.put(`http://172.20.10.8:8080/api/library/removeBook/${location.state.bookId}`).then((res) => {
            if (res.data["responseData"]["isBookRemoved"]) {
                alert("Book is removed");
                navigate("../library");
            } else {
                alert("Something is wrong. Book is not removed.");
            }
        }).catch((error) => {
            alert("Something is wrong. " + error);
        }); 
    }

    const increaseNumberOfFullReads = () => {
        setNumberOfFullReads(numberOfFullReads + 1);
    }

    const decreaseNumberOfFullReads = () => {
        if (numberOfFullReads > 0) {
            setNumberOfFullReads(numberOfFullReads - 1);
        };
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


    const validatePlot = () => {
        return plot.length < 500;
    }
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
                <input className={styles.Title} defaultValue={title} onChange={changeTitle}/>
            </div>
            <div className={styles.InfoBlock}>
                <div style={{width:"25%"}}>
                    <div id='vertical row'>
                        <div>
                            <p style={{padding:0, margin:0}}>Author:</p>
                            <input className={styles.Author} defaultValue={authorFirstName} onChange={changeAuthorFirstName}/>
                            <input className={styles.Author} defaultValue={authorLastName} onChange={changeAuthorLastName}/>
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
                            <input className={styles.Author} defaultValue={isbn} onChange={changeISBN}/>
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
                    <TextareaAutosize className={styles.Plot} defaultValue={plot} onChange={changePlot}/>
                </div>
            </div>
            <div style={{textAlign:"center"}}>
                <button className={styles.Button} onClick={saveButtonClicked} >Save</button>
                <button className={styles.Button} style={{background:"#F67A7A"}} onClick={removeButtonClicked} >Remove</button>
            </div>
            </div> }
        </div>
    );
};