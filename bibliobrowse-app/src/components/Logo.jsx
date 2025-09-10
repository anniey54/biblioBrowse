import styles from './Logo.module.css'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBookBookmark } from '@fortawesome/free-solid-svg-icons';

const Logo = ({fontColour = 'var(--primary)'}) => {
  return (
    <div className={styles.logo} style={{color: fontColour}}>
        <FontAwesomeIcon icon={faBookBookmark} size='2xl' />
        <h3>BiblioBrowse</h3>
    </div>
  )
}

export default Logo