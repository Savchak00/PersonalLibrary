import styles from './Library.module.css';
import logo from '../logo.svg'
import {LibraryBook} from './LibraryBook/LibraryBook'

export const Library = (props) => {

    const buttonClicked = (event) => {

    }

    return (
        <div className={styles.Library}>
            <img src={logo} className={styles.Logo} lt="Logo" />
            <div className={styles.ListOfBooks}>
                    <LibraryBook text="Clean Code" color="#A97AF6" angle={-5}/>
                    <LibraryBook text="Clean Architecture" color="#7ABBF6" angle={5}/>
                    <LibraryBook text="Essays" color="#F67A7A" angle={0}/>
                    <LibraryBook text="Clean Code" color="#21A61E" angle={2}/>
                    <LibraryBook text="Clean Code" color="#847AF6" angle={-2}/>
            </div>
            <button className={styles.Button} onClick={buttonClicked} >Add</button>
        </div>
    );
};