import styles from './Library.module.css';
import {LibraryBook} from './LibraryBook/LibraryBook';
import logo from '../logo.svg';
import Flickity from 'react-flickity-component';
import './flikity.css'

export const Library = (props) => {

    const buttonClicked = () => {
    
    };
  
    const flickityOptions = {
      initialIndex: 2,
      groupCells: 2
    }

    return (
        <div className={styles.Library}>
        <img src={logo} className={styles.LogoLibrary} alt="Logo" />
            <div className={styles.ListOfBooks}>
              <Flickity className={'carousel'} elementType={'div'} options={flickityOptions} disableImagesLoaded={false} reloadOnUpdate static >
                <LibraryBook text="Clean Code" color="#A97AF6" angle={-5}/>
                <LibraryBook text="Clean Architecture" color="#7ABBF6" angle={5}/>
                <LibraryBook text="Essays" color="#F67A7A" angle={0}/>
                <LibraryBook text="Essays" color="#F67A7A" angle={0}/>
                <LibraryBook text="Essays" color="#F67A7A" angle={0}/>
                <LibraryBook text="Essays" color="#F67A7A" angle={0}/>
                <LibraryBook text="Essays" color="#F67A7A" angle={0}/>
                <LibraryBook text="Essays" color="#F67A7A" angle={0}/>
                <LibraryBook text="Essays" color="#F67A7A" angle={0}/>
                <LibraryBook text="Essays" color="#F67A7A" angle={0}/>
                <LibraryBook text="Essays" color="#F67A7A" angle={0}/>
                <LibraryBook text="Essays" color="#F67A7A" angle={0}/>
                <LibraryBook text="Essays" color="#F67A7A" angle={0}/>
                <LibraryBook text="Essays" color="#F67A7A" angle={0}/>
                <LibraryBook text="Essays" color="#F67A7A" angle={0}/>
                <LibraryBook text="Essays" color="#F67A7A" angle={0}/>
                <LibraryBook text="Essays" color="#F67A7A" angle={0}/>
                <LibraryBook text="Essays" color="#F67A7A" angle={0}/>
                <LibraryBook text="Essays" color="#F67A7A" angle={0}/>
                <LibraryBook text="Essays" color="#F67A7A" angle={0}/>
                <LibraryBook text="Essays" color="#F67A7A" angle={0}/>

              </Flickity>
            </div>
            <div>

            </div>
            <button className={styles.Button} onClick={buttonClicked} >Add</button>
        </div>
    );
};