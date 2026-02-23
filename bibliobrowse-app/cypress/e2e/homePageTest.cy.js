/// <reference types="Cypress" />
// 
describe('template spec', () => {
  it('renders the default elements on the screen', () => {
    cy.visit('http://localhost:5173/')

    cy.get('[data-testid="home-hero-title"]')
      .should('exist')
      .should('have.text', 'Welcome to BiblioBrowse')
    cy.get('[data-testid="home-hero-paragraph"]')
      .should('exist')
      .should('have.text', 'Explore thousands of fiction books and curated collections to find your next read. Track your reading progress, add reviews, organize your library, and create custom collections that match your taste.')
    cy.get('[data-testid="home-signup-title"]')
      .should('exist')
      .should('have.text', 'Your Next Great Read Awaits')
    cy.get('[data-testid="home-signup-paragraph"]')
      .should('exist')
      .should('have.text', 'Sign up to track your reading progress, add reviews and ratings, and organize your library effortlessly. Plus, create custom collections that reflect your unique taste and discover books tailored just for you.')
  });

  it('Click on homepage buttons', () => {
    cy.visit('http://localhost:5173/')

    cy.contains('Browse Books').click()
    cy.url().should('include', '/books')

    cy.visit('http://localhost:5173/')
    cy.contains('Browse Collections').click()
    cy.url().should('include', '/collections')

    cy.visit('http://localhost:5173/') 
    cy.contains('Sign up').click()
    cy.url().should('include', '/')

    cy.visit('http://localhost:5173/')
    cy.get('[data-testid="popular-book-view-more"]').click()
    cy.url().should('include', '/books')

    cy.visit('http://localhost:5173/')
    cy.get('[data-testid="popular-collection-view-more"]').click()
    cy.url().should('include', '/collections')
  });
})