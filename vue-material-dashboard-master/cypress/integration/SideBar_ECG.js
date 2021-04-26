describe('Sidebar and ECG test', () => {

	it('Test Sidebare', () => {
		cy.visit('http://localhost:8081/');
		cy.contains('Heart Rater').should('exist')
		cy.contains('Dashboard')
		cy.contains('ECG Live')
		cy.contains('Measurements')
	})
	it('Test ECG', () => {
		cy.contains('ECG Live')
			.click()
		cy.contains('Live ECG')
		cy.contains('Heart Rate')
		cy.contains('Pause')
	})
})

