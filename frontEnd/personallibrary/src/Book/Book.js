import styles from './Book.module.css';
import logo from '../logo.svg';
import photo from '../booksPhoto.jpeg';
import TextareaAutosize from 'react-textarea-autosize';
import {useEffect} from 'react';
import Axios from "axios";
import {useState} from 'react';

export const Book = (props) => {
    const [title,setTitle] = useState("");
    const [author,setAuthor] = useState("");
    const [isbn,setISBN] = useState("");
    const [plot,setPlot] = useState("");
    const [numberOfFullReads,setNumberOfFullReads] = useState(0);
    const [additionDate, setAdditionDate] = useState(null);

    useEffect(() => {
        fetchBook();
    },[]);

    const fetchBook = () => {
        Axios.get("http://172.20.10.8:8080/api/book/1", {
        })
        .then((response) => {
            let book = response.data["responseData"]["book"];
            setTitle(book["title"]);
            setAuthor(book["author"]["firstName"] + " " + book["author"]["lastName"]);
            setISBN(book["isbn"]);
            setPlot(book["plot"]);
            setNumberOfFullReads(book["numberOfFullReads"]);
            setAdditionDate(book["additionDate"]);
        });
    };

    const saveButtonClicked = () => {
        // post date 
    }

    const increaseNumberOfFullReads = () => {
        setNumberOfFullReads(numberOfFullReads + 1);
    }

    const decreaseNumberOfFullReads = () => {
        setNumberOfFullReads(numberOfFullReads - 1);
    }   

    return (
        <div>
            <div style={{textAlign:'center'}}>
                <img src={logo} className={styles.LogoBook} alt="Logo" />
            </div>
            <div style={{textAlign:'center'}}>
                <img src={photo} className={styles.Photo} alt="Books"/>
            </div>
            <div style={{textAlign:'start'}}>
                <input className={styles.Title} defaultValue={title}/>
            </div>
            <div className={styles.InfoBlock}>
                <div style={{width:"25%"}}>
                    <div id='vertical row'>
                        <div>
                            <p style={{padding:0, margin:0}}>Author:</p>
                            <input className={styles.Author} defaultValue={author}/>
                        </div>
                        <div style={{marginTop:"40px"}}>
                            <p style={{padding:0, margin:0}}>Addition date:</p>
                            <p className={styles.Author}>{additionDate}</p>

                        </div>
                    </div>
                </div>
                <div style={{width:"25%"}}>
                    <div id='vertical row'>
                        <div>
                            <p style={{padding:0, margin:0}}>ISBN:</p>
                            <input className={styles.Author} defaultValue={isbn}/>
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
                    <TextareaAutosize className={styles.Plot} defaultValue={plot}/>
                </div>
            </div>
            <div style={{textAlign:"center"}}>
                <button className={styles.Button} onClick={saveButtonClicked} >Save</button>
            </div>
        </div>
    );
};