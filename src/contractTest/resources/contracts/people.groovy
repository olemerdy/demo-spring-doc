package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    name 'GET people by UID found'
    request {
        method 'GET'
        urlPath('/api/v1/people/john.doe') {
        }
        headers {
            contentType('application/json')
        }
    }
    response {
        status OK()
        body file('response.json')
        headers {
            contentType('application/json')
        }
    }
}