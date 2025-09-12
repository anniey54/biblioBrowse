import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Home from './pages/home';
import Books from './pages/books';
import Navbar from './components/navbar';
import Footer from './components/Footer';
import ScrollToTop from './components/ScrollToTop';

function App() {

  return (
    <div>
      <BrowserRouter>
      <ScrollToTop/>
      <Navbar />
        <div style={{marginTop: '64px', minHeight:'100vh'}}>
          <Routes>
            <Route path='/' element={<Home/>}/>
            <Route path='/books' element={<Books/>}/>
          </Routes>
        </div>
        <Footer/>
      </BrowserRouter>
    </div>
  )
}

export default App
