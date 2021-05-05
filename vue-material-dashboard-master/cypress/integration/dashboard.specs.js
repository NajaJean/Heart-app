describe('Dashboard check', () => {

    it('Log In, FAIL', () => {
        cy.visit('http://localhost:8081/', { delay: 200 });
        cy.get('input[name=username]').clear()
            .type('Not a user', { delay: 100 })
            .should('have.value', 'Not a user')
        cy.get('input[name=password]').clear()
            .type('bad password', { delay: 100 })
        cy.get('button[name=login]')
            .click()
        cy.contains('Failed to log in')
        cy.get('.swal2-confirm.swal2-styled')
            .wait(600)
            .click()

    })
    it('Log In, PASS', () => {
        cy.get('input[name=username]').clear()
            .type('admin', { delay: 100 })
            .should('have.value', 'admin')
        cy.get('input[name=password]').clear()
            .type('Mathilde', { delay: 100 })
        cy.get('button[name=login]')
            .click()

    })
    it('Render dashboard correctly', () => {
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
            .wait(500)
            .type('2021-01-01')
            .invoke('val')
            .get('.md-datepicker-day-button.md-datepicker-selected')
            .contains(1, { delay: 200 })
            .click()

        cy.get('div[name=to]', { delay: 200 })
            .wait(500)
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
            .wait(500)
        cy.get('button[name=updateThreshold]', { delay: 200 })
            .click()

    })
})

describe('Sidebar and ECG test', () => {

    it('Test Sidebare', () => {
        cy.contains('HeartRater').should('exist')
        cy.contains('Dashboard')
        cy.contains('ECG Live')
        cy.contains('Log Out')
    })
    it('Test ECG', () => {
        cy.contains('ECG Live')
            .click()
        cy.contains('Live ECG')
        cy.contains('Heart Rate')
    })
})