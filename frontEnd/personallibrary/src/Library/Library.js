import Carousel from 'react-multi-carousel';
import 'react-multi-carousel/lib/styles.css';
import styles from './Library.module.css';
import {LibraryBook} from './LibraryBook/LibraryBook';
import logo from '../logo.svg';

export const Library = (props) => {

    const buttonClicked = () => {
    
    };

    const responsive = {
        superLargeDesktop: {
          breakpoint: { max: 4000, min: 3000 },
          items: 5
        },
        desktop: {
          breakpoint: { max: 3000, min: 1024 },
          items: 3
        },
        tablet: {
          breakpoint: { max: 1024, min: 464 },
          items: 2
        },
        mobile: {
          breakpoint: { max: 464, min: 0 },
          items: 1
        }
    };

    return (
        <div className={styles.Library}>
            <img src={logo} className={styles.Logo} alt="Logo" />
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