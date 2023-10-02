import styles from './LibraryBook.module.css';

export const LibraryBook = (props) => {
    return (
        <div className={styles.cont}>
            <div className={styles.Book} style={{backgroundColor: props.color, transform: "rotate(" + props.angle + "deg)"}}>
                <text>{props.text}</text>
            </div>
        </div>
    );
};