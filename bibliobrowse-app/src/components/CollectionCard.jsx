import styles from './CollectionCard.module.css';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faHeart } from "@fortawesome/free-solid-svg-icons";
import {faHeart as emptyHeart} from "@fortawesome/free-regular-svg-icons"
import toast from 'react-hot-toast'; 
import { Link } from 'react-router-dom';
import { useState, useEffect } from 'react';

const CollectionCard = ({id, title, author, isFavourite, toggleFavourite}) => {
  const [bookImages, setBookImages] = useState([]);
  const [displayBooks, setDisplayBooks] = useState([]);
  const [numBooks, setNumBooks] = useState(0);
  const [authorName, setAuthorName] = useState("");

  useEffect(() => {
    const getBookImages = async () => {
      try {
        fetch(`http://localhost:8080/api/collection-book/collection/${id}`)
          .then((response) => {return response.json()})
          .then((data) => {
            const images = data.map(item => item.imageCover)
            setBookImages(images.slice(0, 6));
            setDisplayBooks(images.slice(0, 6));
            setNumBooks(data.length)
          })
      } catch (error) {
        console.error(error.message);
      }
    }

    const getAuthor = async () => {
      try {
        fetch(`http://localhost:8080/api/users/${author}`)
          .then((response) => {return response.json()})
          .then((data) => {
            setAuthorName(data.username);
          })
      } catch (error) {
        console.error(error.message);
      }
    }

    getAuthor();
    getBookImages();
  }, []);

  useEffect(() => {
    const changeNumberOfImage = () => {
      if (window.innerWidth <= 380) {
        setDisplayBooks(bookImages.slice(0, 4));
      } else {
        setDisplayBooks(bookImages.slice(0, 5));
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
            {displayBooks.map((url, index) => (
              <img key={index} src={url}/>
            ))}
          </div>
          <div className={styles.titleAuthor}>
            <p style={{fontWeight: 'bold'}}>{title}</p>
            <p>{authorName}</p>
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