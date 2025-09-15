import React, { useState } from 'react';
import BookCard from '../components/bookCard';
import CollectionCard from '../components/CollectionCard';

export default function books() {
  const [isBookFavourite, setIsBookFavourite] = useState(false);
  const [isCollectionFavourite, setIsCollectionFavourite] = useState(true);

  return (
    <div>
      <BookCard 
        title={'The Hunger Games'}
        imageUrl={'https://m.media-amazon.com/images/I/71un2hI4mcL.jpg'}
        author={'Suzanne Collins'}
        rating={4.5}
        isFavourite={isBookFavourite}
        toggleFavourite={() => setIsBookFavourite(!isBookFavourite)}
      />
      <CollectionCard
        title={'My Favourite Books'}
        imageUrls={['https://m.media-amazon.com/images/I/71un2hI4mcL.jpg',
          'https://m.media-amazon.com/images/I/71un2hI4mcL.jpg',
          'https://m.media-amazon.com/images/I/71un2hI4mcL.jpg',
          'https://m.media-amazon.com/images/I/71un2hI4mcL.jpg',
          'https://m.media-amazon.com/images/I/71un2hI4mcL.jpg',
          'https://m.media-amazon.com/images/I/71un2hI4mcL.jpg',
          'https://m.media-amazon.com/images/I/71un2hI4mcL.jpg'
        ]}
        author={'UserName 123'}
        numBooks={25}
        isFavourite={isCollectionFavourite}
        toggleFavourite={() => setIsCollectionFavourite(!isCollectionFavourite)}
      />
    </div>
  )
}
