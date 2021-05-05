describe('Testing API', () => {

	var idOne = 0;	
	it('POST measurement', () => {
		cy.request({
			method: 'POST',
			url: 'http://localhost:8080/api/measurements',
			body: {				    
		        "patientid": "010101-1234",
		        "datepost": "Wed, 30 Sep 2020 17:15:34 GMT",
		        "measurementtype": "cnt_steps",
		        "measurementvalue": "2845"		
			}
		}).then((response)=> {
			expect(response.body).has.property("patientid","010101-1234");			
			expect(response.status).equal(201)			
			expect(response.body).to.deep.equal({
			    "patientid": "010101-1234",
			    "datepost": "2020-09-30T17:15:34.000+00:00",
			    "measurementtype": "cnt_steps",
			    "measurementvalue": "2845"
			})
		})
    })

	it('POST measurement, bad request', () => {
		cy.request({
			failOnStatusCode: false,
			method: 'POST',
			url: 'http://localhost:8080/api/measurements',
			body: {				    
			    "patientid": "",
			    "datepost": "2020-09-30T17:15:34.000+00:00",
			    "measurementtype": "cnt_steps",
			    "measurementvalue": "2845"	
			}
		}).then((response)=> {	
			expect(response.status).equal(400)			
		})
    })

	it('POST measurement, internal server error', () => {
		cy.request({
			failOnStatusCode: false,
			method: 'POST',
			url: 'http://localhost:8080/api/measurements',
			body: {				    
			    "patientid": "",
			    "datepost": "",
			    "measurementtype": "",
			    "measurementvalue": ""	
			}
		}).then((response)=> {	
			expect(response.status).equal(500)			
		})
    })

	it('GET last 7 measurements', () => {
		cy.request({
			method: 'GET',
			url: 'http://localhost:8080/api/7measurements/010101-1234/cnt_steps',
		}).then((response)=> {		
			expect(response.status).equal(200)			
		})
    })

	it('GET last 7 measurements, no content', () => {
		cy.request({
			method: 'GET',
			url: 'http://localhost:8080/api/7measurements/666/cnt_steps',
		}).then((response)=> {		
			expect(response.status).equal(204)			
		})
    })

	it('GET measurement', () => {
		cy.request({
			method: 'GET',
			url: 'http://localhost:8080/api/measurements/010101-1234/cnt_steps/Wed Feb 3 2021 00:00:00 GMT+0100/Wed Feb 10 2021 00:00:00 GMT+0100',
		}).then((response)=> {		
			expect(response.status).equal(200)			
		})
	})	
	
	it('GET measurement, no content', () => {
		cy.request({
			method: 'GET',
			url: 'http://localhost:8080/api/measurements/666/cnt_steps/Wed Feb 3 2021 00:00:00 GMT+0100/Wed Feb 10 2021 00:00:00 GMT+0100',
		}).then((response)=> {		
			expect(response.status).equal(204)			
		})
	})
	
	it('POST realtime', () => {
		cy.request({
			method: 'POST',
			url: 'http://localhost:8080/api/realtime',
			body: {
			    "patientid": "010101-1234",
			    "datepost": " 2021-04-19T07:30:20.277+00:00",
			    "measurementvalue": [524,986,1125,1079,1020,1006,1015,1024,1026,1025,1023,1023,1023,1024,818,505,429,503,458,546,491,489,534,455,535,487,489,536,455,535,487,490,537,456,536,487,489,536,456,536,487,489,536,455,535,487,489,536,455,535,488,488,536,455,535,487,489,537,456,535,488,488,536,455,535,488,488,537,456,535,488,488,537,456,535,488,487,536,456,535,488,487,537,456,535,489,487,537,456,535,488,486,537,456,535,489,487,537,457,535,489,486,538,456,535,489,487,537,456,535,489,485,537,457,535,490,486,537,457,535,489,484,537,456,535,490,92]
			}
		}).then((response)=> {
			expect(response.body).has.property("patientid","010101-1234");			
			expect(response.status).equal(201)			
			expect(response.body).to.deep.equal({
			    "patientid": "010101-1234",
			    "datepost": "2021-04-19T07:30:20.277+00:00",
			    "measurementvalue": [524,986,1125,1079,1020,1006,1015,1024,1026,1025,1023,1023,1023,1024,818,505,429,503,458,546,491,489,534,455,535,487,489,536,455,535,487,490,537,456,536,487,489,536,456,536,487,489,536,455,535,487,489,536,455,535,488,488,536,455,535,487,489,537,456,535,488,488,536,455,535,488,488,537,456,535,488,488,537,456,535,488,487,536,456,535,488,487,537,456,535,489,487,537,456,535,488,486,537,456,535,489,487,537,457,535,489,486,538,456,535,489,487,537,456,535,489,485,537,457,535,490,486,537,457,535,489,484,537,456,535,490,92]
			})
		})
    })

	it('POST realtime, bad request', () => {
		cy.request({
			method: 'POST',
			failOnStatusCode: false,
			url: 'http://localhost:8080/api/realtime',
			body: {
			    "patientid": "",
			    "datepost": "2021-04-19T07:30:20.277+00:00",
			    "measurementvalue": [524,986,1125,1079,1020,1006,1015,1024,1026,1025,1023,1023,1023,1024,818,505,429,503,458,546,491,489,534,455,535,487,489,536,455,535,487,490,537,456,536,487,489,536,456,536,487,489,536,455,535,487,489,536,455,535,488,488,536,455,535,487,489,537,456,535,488,488,536,455,535,488,488,537,456,535,488,488,537,456,535,488,487,536,456,535,488,487,537,456,535,489,487,537,456,535,488,486,537,456,535,489,487,537,457,535,489,486,538,456,535,489,487,537,456,535,489,485,537,457,535,490,486,537,457,535,489,484,537,456,535,490,92]
			}
		}).then((response)=> {	
			expect(response.status).equal(400)
		})
    })

	it('POST realtime, internal server error', () => {
		cy.request({
			method: 'POST',
			failOnStatusCode: false,
			url: 'http://localhost:8080/api/realtime',
			body: {
			    "patientid": "",
			    "datepost": "",
			    "measurementvalue": []
			}
		}).then((response)=> {	
			expect(response.status).equal(500)
		})
    })

	it('GET realtime', () => {
		cy.request({
			method: 'GET',
			url: 'http://localhost:8080/api/latestecg/010101-1234',
		}).then((response)=> {		
			expect(response.status).equal(200)			
		})
	})
	
	it('GET realtime, no content', () => {
		cy.request({
			method: 'GET',
			url: 'http://localhost:8080/api/latestecg/666',
		}).then((response)=> {		
			expect(response.status).equal(204)			
		})
	})
		
	it('PUT new thresholds', () => {
		cy.request({
			method: 'PUT',
			url: 'http://localhost:8080/api/thresholds/0',
			body: {
				"patientid": "010101-1234",
		 	   	"measurementtype": "cnt_steps",
		    	"thresholdvalue": 1076,
		    	"thresholdtype": "lower"
			}
		}).then((response)=> {		
			idOne = response.body.id
			expect(response.status).equal(201)			
		})
	})	
	
	it('PUT new thresholds, no content', () => {
		cy.request({
			method: 'PUT',
			failOnStatusCode: false,
			url: 'http://localhost:8080/api/thresholds/0',
			body: {
				"patientid": "010101-1234",
		 	   	"measurementtype": "",
		    	"thresholdvalue": 239,
		    	"thresholdtype": "lower"
			}
		}).then((response)=> {		
			expect(response.status).equal(400)			
		})
	})	
	
	it('PUT edit thresholds', () => {
		cy.request({
			method: 'PUT',
			url: 'http://localhost:8080/api/thresholds/' + idOne,
			body: {
				"patientid": "010101-1234",
		 	   	"measurementtype": "cnt_steps",
		    	"thresholdvalue": 1001,
		    	"thresholdtype": "lower"
			}
		}).then((response)=> {	
			expect(response.body).has.property("thresholdvalue",1001);			
			expect(response.status).equal(200)			
		})
	})	
	
	it('GET thresholds', () => {
		cy.request({
			method: 'GET',
			url: 'http://localhost:8080/api/thresholds/010101-1234',
		}).then((response)=> {		
			expect(response.status).equal(200)			
		})
	})	
	
	it('GET thresholds, no content', () => {
		cy.request({
			method: 'GET',
			url: 'http://localhost:8080/api/thresholds/666',
		}).then((response)=> {		
			expect(response.status).equal(204)			
		})
	})	

})