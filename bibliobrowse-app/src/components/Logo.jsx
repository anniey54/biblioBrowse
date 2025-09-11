import styles from './Logo.module.css';
import { Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBookBookmark } from '@fortawesome/free-solid-svg-icons';

const Logo = ({fontColour = 'var(--primary)'}) => {
  return (
    <Link to={'/'} className={styles.logo} style={{color: fontColour}}>
        <FontAwesomeIcon icon={faBookBookmark} size='2xl' />
        <h3>BiblioBrowse</h3>
    </Link>
  )
}

export default Logo