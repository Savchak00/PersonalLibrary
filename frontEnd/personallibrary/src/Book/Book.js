import styles from './Book.module.css';
import logo from '../logo.svg';
import photo from '../booksPhoto.jpeg';
import TextareaAutosize from 'react-textarea-autosize';

export const Book = (props) => {
    const plot = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.\n" + 
    "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,\b" + 
    "when an unknown printer took a galley of type and scrambled it to make a type specimen book.";
    return (
        <div>
            <div style={{textAlign:'center'}}>
                <img src={logo} className={styles.Logo} alt="Logo" />
            </div>
            <div style={{textAlign:'center'}}>
                <img src={photo} className={styles.Photo} alt="Books"/>
            </div>
            <div style={{textAlign:'start'}}>
                <input className={styles.Title} defaultValue={"Clean Code"}/>
            </div>
            <div className={styles.InfoBlock}>
                <div style={{width:"25%"}}>
                    <div id='vertical row'>
                        <div>
                            <p style={{padding:0, margin:0}}>Author:</p>
                            <input className={styles.Author} defaultValue={"Robert Martin"}/>
                        </div>
                        <div style={{marginTop:"40px"}}>
                            <p style={{padding:0, margin:0}}>Addition date:</p>
                            <input className={styles.Author} defaultValue={"2023-23-03"}/>
                        </div>
                    </div>
                </div>
                <div style={{width:"25%"}}>
                    <div id='vertical row'>
                        <div>
                            <p style={{padding:0, margin:0}}>ISBN:</p>
                            <input className={styles.Author} defaultValue={"0-7674-343-2"}/>
                        </div>
                        <div style={{marginTop:"40px"}}>
                            <p style={{padding:0, margin:0}}>Number of full reads:</p>
                            <input className={styles.Author} defaultValue={"1 - +"}/>
                        </div>
                    </div>
                </div>
                <div id='plot' style={{width:"50%"}}>
                    <p style={{padding:0, margin:0}}>Plot:</p>
                    <TextareaAutosize className={styles.Plot} defaultValue={plot}/>
                </div>
            </div>
            <div style={{textAlign:"center"}}>
                <button className={styles.Button} >Save</button>
            </div>
        </div>
    );
};