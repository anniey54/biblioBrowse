import styles from './CollectionCard.module.css';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faHeart } from "@fortawesome/free-solid-svg-icons";
import {faHeart as emptyHeart} from "@fortawesome/free-regular-svg-icons"
import toast from 'react-hot-toast'; 
import { Link } from 'react-router-dom';

const CollectionCard = ({title, imageUrls, author, numBooks, isFavourite, toggleFavourite}) => {
  const clickFavouriteButton = () => {
    toggleFavourite();
    if (!isFavourite) toast.success('Successfully added collection to favourite')
    else toast.success('Successfully removed collection from favourite');
  };
  
  return (
    <div style={{marginBottom: '-20px'}}>
      <Link to={'/'}>
        <div className={styles.collectionCard} >
          <div className={styles.bookCovers}>
            {imageUrls.slice(0, 6).map((url, index) => (
              <img key={index} src={url}/>
            ))}
          </div>
          <div className={styles.titleAuthor}>
            <p style={{fontWeight: 'bold'}}>{title}</p>
            <p>{author}</p>
            <p style={{fontSize: '0.85rem'}}>{numBooks} books</p>
          </div>
        </div>
      </Link>
      <div className={styles.favouriteButton} title='Favourite'>
        <FontAwesomeIcon icon={isFavourite ? faHeart : emptyHeart} color='var(--heart)' onClick={clickFavouriteButton}/>
      </div>
    </div>
  )
}

export default CollectionCard