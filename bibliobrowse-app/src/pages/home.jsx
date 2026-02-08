import { Link } from 'react-router-dom';
import styles from'./home.module.css';
import heroImage from './../../public/homepage-background.png';
import signUpIllustration from './../../public/homepage-illustration.svg';
import { useEffect, useState } from 'react';
import Carousel from '../components/Carousel';

export default function home() {
  const [popularBooks, setPopularBooks] = useState(null);
  const [popularCollections, setPopularCollections] = useState(null);

  useEffect(() => {
    const getPopularBooks = async () => {
      try {
        fetch('http://localhost:8080/api/books')
          .then((response) => {return response.json()})
          .then((data) => {
            setPopularBooks(data.slice(0, 3));
            console.log("books:", data.slice(0, 4));
          })
      } catch (error) {
        console.error(error.message);
      }
    }

    const getPopularCollections = async () => {
      try {
        fetch('http://localhost:8080/api/collections')
          .then((response) => {return response.json()})
          .then((data) => {
            const publicCollections = data.filter(coll => coll.status === 'Public');
            setPopularCollections(publicCollections.slice(0, 3));
            console.log("collections:", publicCollections.slice(0, 5));
          })
      } catch (error) {
        console.error(error.message);
      }
    }

    getPopularBooks();
    getPopularCollections();
  },[]);

  return (
    <div className={styles.homePage}>
      {/* Hero */}
      <div className={styles.hero}>
        <div className={styles.heroContent}>
          <h1>Welcome to BiblioBrowse</h1>
          <p>Explore thousands of fiction books and curated collections to find your next read. Track your reading progress, add reviews, organize your library, and create custom collections that match your taste.</p>
          <div className={styles.heroButtons}>
            <Link to={'/books'}>
              <button>Browse Books</button>
            </Link>
            <Link to={'/collections'}>
              <button>Browse Collections</button>
            </Link>
          </div>
        </div>
        <div className={styles.heroImage}>
          <img src={heroImage} alt='Home page hero image' />
        </div>
      </div>
      {/* popular books */}
      <div className={styles.popularSection}>
        <h2>Popular Books</h2>
        {popularBooks != null &&
          <Carousel itemList={popularBooks} viewMoreLink={'/books'} cardType={'book'} />
        }
      </div>
      {/* Sign up section */}
      <div className={styles.signUpSection}>
        <div className={styles.signUpText}>
          <h2>Your Next Great Read Awaits</h2>
          <p>Sign up to track your reading progress, add reviews and ratings, and organize your library effortlessly. Plus, create custom collections that reflect your unique taste and discover books tailored just for you.</p>
          <button>Sign up</button>
        </div>
        <div className={styles.signUpImage}>
          <img src={signUpIllustration} alt='Home page illustration' />
        </div>
      </div>
      {/* popular collections */}
      <div className={styles.popularSection}>
        <h2>Popular Collections</h2>
        {popularCollections != null &&
          <Carousel itemList={popularCollections} viewMoreLink={'/collections'} cardType={'collection'} />
        }
      </div>
    </div>
  )
}
