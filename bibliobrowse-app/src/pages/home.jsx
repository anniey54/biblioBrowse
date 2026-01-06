import { Link } from 'react-router-dom';
import styles from'./home.module.css';
import heroImage from './../../public/homepage-background.png';

export default function home() {
  return (
    <div className={styles.homePage}>
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
      <textarea placeholder='Enter here...' rows={4}></textarea>
      <input type='text' placeholder='Enter here...' />
    </div>
  )
}
