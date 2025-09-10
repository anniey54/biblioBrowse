import React from 'react';

export default function home() {
  return (
    <div style={{display: 'flex', flexDirection: 'column', gap: '10px'}}>
        <h1>header 1</h1>
        <h2>header 2</h2>
        <h3>header 3</h3>
        <p>body text</p>
        <button>
          button
        </button>
        <textarea placeholder='Enter here...' rows={4}></textarea>
        <input type='text' placeholder='Enter here...' />
    </div>
  )
}
