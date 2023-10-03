import styles from './LibraryBook.module.css';
import { useNavigate } from "react-router-dom";

export const LibraryBook = (props) => {
    const book = props.book;
    let navigate = useNavigate(); 
    const angles = [-5, -3, 3, 5];

    const getAngle = () => {
        return angles[Math.floor(Math.random() * angles.length)];
    }

    const backgroundColors = ["#A97AF6", "#7ABBF6","#F67A7A", "#847AF6", "#F6B67A", "#21A61E"];

    const getBackColor = () => {
        return backgroundColors[Math.floor(Math.random() * backgroundColors.length)];
    };

    const buttonClicked = () => {
        navigate('/book');
    };

    return (
        <div className={styles.cont}>
            <div className={styles.Book}   role='button' onClick = {buttonClicked} style={{backgroundColor: getBackColor(), transform: "rotate(" + getAngle() + "deg)"}}>
                <p className={styles.Text}>{book.title}</p>
            </div>
        </div>
    );
};