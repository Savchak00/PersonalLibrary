import styles from './LibraryBook.module.css';

export const LibraryBook = (props) => {
    return (
        <div className={styles.cont}>
            <div className={styles.Book} style={{backgroundColor: props.color, transform: "rotate(" + props.angle + "deg)"}}>
                <p className={styles.Text}>{props.text}</p>
            </div>
        </div>
    );
};