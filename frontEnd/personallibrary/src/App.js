import styles from './App.module.css';
import { LogIn } from './Components/LogIn/LogIn';
import {Library} from './Components/Library/Library';
import {Book} from './Components/Book/Book'
import {Route, Routes, BrowserRouter} from 'react-router-dom';
import {AddBook} from './Components/AddBook/AddBook';

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
    </div>
  );
};


export default App;
