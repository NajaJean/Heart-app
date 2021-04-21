describe('Dashboard check', () => {

    it('Render correctly', () => {
        cy.visit('http://localhost:8081/', { delay: 200 });
        cy.contains('Blood Pressure')
        cy.contains('Steps')
        cy.contains('Sleep')
    })
    it('Change patient', () => {
        cy.get('input[name=patientID]').clear()
            .type('010101-1233', { delay: 100 })
            .should('have.value', '010101-1233')
        cy.get('button[name=changePatient]')
            .click()
    })
    it('Update dates', () => {
        cy.get('div[name=from]', { delay: 200 })
            .type('2021-01-01')
            .invoke('val')
            .get('.md-datepicker-day-button.md-datepicker-selected')
            .contains(1, { delay: 200 })
            .click()

        cy.get('div[name=to]', { delay: 200 })
            .type('2021-01-08')
            .get('.md-datepicker-day-button.md-datepicker-selected')
            .contains(8, {delay: 200})
            .click()

        cy.get('button[name="updateDate"]', { delay: 200 })
            .click()
    })
    it('Update thresholds', { delay: 200 },() => {
        cy.get('button[name=thresholdButton]', { delay: 200 })
            .click()
        cy.get('input[name = bloodSysLower]', { delay: 200 })
            .clear()
            .type('80')
        cy.get('input[name = bloodSysUpper]', { delay: 200 })
            .clear()
            .type('120')
        cy.get('button[name=updateThreshold]', { delay: 200 })
            .click()

    })
})