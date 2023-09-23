describe('Add product as an admin', () => {
    it('passes', () => {
      //Visit the login page
      cy.visit('http://localhost:9090');
      cy.get('#MenuItems > li.nav-item > a').click()
      cy.get('#username').type('admin')
      cy.get('#LoginForm > input:nth-child(2)').type('admin')
      cy.get('#submit').click()
      cy.get('body > div.small-container > h3').should('have.text', 'We wish you pleasant shopping admin!');

      //add new product
      cy.get('body > div.small-container > button > a').click()
      cy.url().should('include', '/products/add');
      cy.get('#name').type('dress test')
      cy.get('#description').type('desc test')
      cy.get('#image').type('https://assets.ajio.com/medias/sys_master/root/20230624/d2lM/64966b12a9b42d15c9ddcd02/-473Wx593H-465410816-burgundy-MODEL.jpg')
      cy.get('#image1').type('https://assets.ajio.com/medias/sys_master/root/20230624/d2lM/64966b12a9b42d15c9ddcd02/-473Wx593H-465410816-burgundy-MODEL.jpg')
      cy.get('#image2').type('https://assets.ajio.com/medias/sys_master/root/20230624/d2lM/64966b12a9b42d15c9ddcd02/-473Wx593H-465410816-burgundy-MODEL.jpg')
      cy.get('#image3').type('https://assets.ajio.com/medias/sys_master/root/20230624/d2lM/64966b12a9b42d15c9ddcd02/-473Wx593H-465410816-burgundy-MODEL.jpg')
      cy.get('#price').type('100')
      cy.get('#grade').type('9')
      cy.get('#quantitySizeS').type('3')
      cy.get('#quantitySizeM').type('4')
      cy.get('#quantitySizeL').type('5')
      cy.get('#quantitySizeXL').type('6')
      cy.get('#submit').click()
      cy.url().should('include', '/products');
    })
  })