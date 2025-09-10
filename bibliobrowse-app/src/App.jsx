import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Home from './pages/home';
import Books from './pages/books';
import Navbar from './components/navbar';

function App() {

  return (
    <div>
      <BrowserRouter>
      <Navbar />
        <div style={{marginTop: '64px'}}>
          <Routes>
            <Route path='/' element={<Home/>}/>
            <Route path='/books' element={<Books/>}/>
          </Routes>
        </div>
      </BrowserRouter>
    </div>
  )
}

export default App
