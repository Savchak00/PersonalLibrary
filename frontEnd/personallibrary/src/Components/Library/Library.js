import styles from './Library.module.css';
import {LibraryBook} from './LibraryBook/LibraryBook';
import logo from '../../logo.svg';
import Flickity from 'react-flickity-component';
import './flikity.css'
import {useEffect, useState} from 'react';
import Axios from "axios";
import { SpinnerDiamond } from 'spinners-react';
import { useNavigate } from "react-router-dom";

export const Library = (props) => {
    const [library, setLibrary] = useState([]);
    const [isLoading, setIsLoading] = useState(false);
    let navigate = useNavigate(); 

    useEffect(() => {
      setIsLoading(true);
      fetchLibrary();
      setIsLoading(false);
    },[]);

    const fetchLibrary = () => {
      Axios.get(`http://172.20.10.8:8080/api/library/${sessionStorage.getItem("userId")}`, {
      })
      .then((response) => {
        const lib = response.data["responseData"]["library"];
        setLibrary([...library, ...lib]);
      });
      console.log(library);
  };

    const buttonClicked = () => {
      navigate("addBook");
    };
  
    const flickityOptions = {
      initialIndex: library.length/2,
      groupCells: library.length/5
    }

    return (
        <div className={styles.Library}>
            {isLoading ? (<SpinnerDiamond className={styles.Spinner}/>) :
          (<div>
                <img src={logo} className={styles.LogoLibrary} alt="Logo" />
                <div className={styles.ListOfBooks}>
                  <Flickity className={`carousel`} options={flickityOptions} disableImagesLoaded={false} reloadOnUpdate static >
                  {library.map((book, index) => (
                    <LibraryBook
                      key={index}
                      book={book}
                    />
                  ))}
                  </Flickity>
                </div>
                <button className={styles.Button} onClick={buttonClicked} >Add</button>
            </div>)}
        </div>
    );
};