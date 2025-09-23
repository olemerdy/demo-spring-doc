package contracts

import org.lafeuille.demo.fixtures.PersonFixtures
import org.springframework.cloud.contract.spec.Contract
import org.springframework.cloud.contract.spec.internal.MediaTypes

import static org.lafeuille.demo.fixtures.PersonFixtures.INSTANCE as People

[
        Contract.make {
            name 'GET people by UID found'
            request {
                method GET()
                urlPath("/api/v1/people/${People.johnSmith.uid}") {
                }
                headers {
                    contentType(MediaTypes.APPLICATION_JSON)
                }
            }
            response {
                status OK()
                body file('PersonResponse.JohnSmith.json')
                headers {
                    contentType(MediaTypes.APPLICATION_JSON)
                }
            }
        },

        Contract.make {
            name 'GET people by UID not found'
            request {
                method GET()
                urlPath("/api/v1/people/${PersonFixtures.UnknownGuy.UID}") {
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
                urlPath("/api/v1/people/${People.johnSmith.uid}") {
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