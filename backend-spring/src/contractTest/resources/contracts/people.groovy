package contracts

import org.springframework.cloud.contract.spec.Contract
import org.springframework.cloud.contract.spec.internal.MediaTypes

[
        Contract.make {
            name 'GET people by UID found'
            request {
                method GET()
                urlPath('/api/v1/people/john.doe') {
                }
                headers {
                    contentType(MediaTypes.APPLICATION_JSON)
                }
            }
            response {
                status OK()
                body file('response.json')
                headers {
                    contentType(MediaTypes.APPLICATION_JSON)
                }
            }
        },

        Contract.make {
            name 'GET people by UID not found'
            request {
                method GET()
                urlPath('/api/v1/people/john.smith') {
                }
                headers {
                    contentType(MediaTypes.APPLICATION_JSON)
                }
            }
            response {
                status NOT_FOUND()
            }
        },

        Contract.make {
            name 'DELETE people by UID'
            request {
                method DELETE()
                urlPath('/api/v1/people/john.doe') {
                }
                headers {
                    contentType(MediaTypes.APPLICATION_JSON)
                }
            }
            response {
                status NO_CONTENT()
            }
        }
]