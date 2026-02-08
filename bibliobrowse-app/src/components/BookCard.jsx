import styles from './BookCard.module.css';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faStar, faHeart } from "@fortawesome/free-solid-svg-icons";
import {faHeart as emptyHeart} from "@fortawesome/free-regular-svg-icons"
import toast from 'react-hot-toast'; 
import { Link } from 'react-router-dom';
import { useEffect, useState } from 'react';

const BookCard = ({title, imageUrl, author, rating, isFavourite, toggleFavourite}) => {
  const [authorName, setAuthorName] = useState("");

  useEffect (() => {
    const getAuthor = async () => {
      try {
        fetch(`http://localhost:8080/api/authors/${author}`)
          .then((response) => {return response.json()})
          .then((data) => {
            setAuthorName(data.fullName);
          })
      } catch (error) {
        console.error(error.message);
      }
    }

    getAuthor();
  }, []);

  const addOnCollection = () => {
    toast.success('Successfully added book to collection');
  };

  const clickFavouriteButton = () => {
    toggleFavourite();
    if (!isFavourite) toast.success('Successfully added book to favourite')
    else toast.success('Successfully removed book from favourite');
  };

  return (
    <div style={{marginBottom: '-26px', width: 'fit-content'}}>
      <Link to={'/'} style={{display: 'flex', width: 'fit-content'}}>
        <div className={styles.bookCard}>
          <img src={imageUrl} alt='book cover' />
          <div className={styles.cardContent}>
            <div className={styles.cardText}>
              <p className={styles.title}>{title}</p>
              <p>{authorName}</p>
            </div>
            <div className={styles.ratingAndButtons}>
              <div className={styles.rating}>
                <p>{rating}</p>
                <FontAwesomeIcon icon={faStar} color='var(--star)'/>
              </div>
            </div>
          </div>
        </div>
      </Link>
      <div className={styles.buttons}>
        <span title='Favourite'>
          <FontAwesomeIcon icon={isFavourite ? faHeart : emptyHeart} color='var(--heart)' onClick={clickFavouriteButton}/>
        </span>
        <img draggable="false" src='./../../addOnCollectionIcon.svg' title='Add to Collection' onClick={addOnCollection}/>
      </div>
    </div>
  )
}

export default BookCard