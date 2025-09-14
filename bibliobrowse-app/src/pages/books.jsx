import React, { useState } from 'react';
import BookCard from '../components/bookCard';

export default function books() {
  const [isFavourite, setIsFavourite] = useState(false);

  return (
    <div>
      <BookCard 
        title={'The Hunger Games'}
        imageUrl={'https://m.media-amazon.com/images/I/71un2hI4mcL.jpg'}
        author={'Suzanne Collins'}
        rating={'4.5'}
        isFavourite={isFavourite}
        toggleFavourite={() => setIsFavourite(!isFavourite)}
      />
    </div>
  )
}
