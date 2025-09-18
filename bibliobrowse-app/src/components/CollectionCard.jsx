import styles from './CollectionCard.module.css';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faHeart } from "@fortawesome/free-solid-svg-icons";
import {faHeart as emptyHeart} from "@fortawesome/free-regular-svg-icons"
import toast from 'react-hot-toast'; 
import { Link } from 'react-router-dom';
import { useState, useEffect } from 'react';

const CollectionCard = ({title, imageUrls, author, numBooks, isFavourite, toggleFavourite}) => {
  const [imageSlice, setImageSlice] = useState(imageUrls.slice(0, 6));

  useEffect(() => {
    const changeNumberOfImage = () => {
      if (window.innerWidth <= 380) {
        setImageSlice(imageUrls.slice(0, 4));
      } else {
        setImageSlice(imageUrls.slice(0, 5));
      }
    }
		window.addEventListener("resize", changeNumberOfImage);

		changeNumberOfImage();

		return () => {
			window.removeEventListener("resize", changeNumberOfImage);
		};
	}, []);

  const clickFavouriteButton = () => {
    toggleFavourite();
    if (!isFavourite) toast.success('Successfully added collection to favourite')
    else toast.success('Successfully removed collection from favourite');
  };
  
  return (
    <div style={{marginBottom: '-20px', width: 'fit-content'}}>
      <Link to={'/'}>
        <div className={styles.collectionCard} >
          <div className={styles.bookCovers}>
            {imageSlice.map((url, index) => (
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