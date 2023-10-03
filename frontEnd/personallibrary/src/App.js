import styles from './App.module.css';
import { LogIn } from './LogIn/LogIn';
import {Library} from './Library/Library';
import {Book} from './Book/Book'
import {Route, Routes, BrowserRouter} from 'react-router-dom';
import {AddBook} from './AddBook/AddBook';

function App() {

  return (
    <div className={styles.App}>
      <BrowserRouter>
        <Routes>
          <Route exact path  = '/'  element={<LogIn/>}/>
          <Route path  = '/library' element={<Library/>}/>
          <Route path = '/book' element={<Book/>}/>
          <Route path = '/library/addBook' element={<AddBook/>}/>
        </Routes>
      </BrowserRouter>
      <div className={styles.readYourBook}>Read your book</div>
    </div>
  );
};


export default App;
