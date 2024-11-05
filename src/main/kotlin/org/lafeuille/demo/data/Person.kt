package org.lafeuille.demo.data

import org.springframework.ldap.odm.annotations.Attribute
import org.springframework.ldap.odm.annotations.DnAttribute
import org.springframework.ldap.odm.annotations.Entry
import org.springframework.ldap.odm.annotations.Id
import java.time.LocalDate
import javax.naming.Name

@Entry(objectClasses = ["person", "top"], base = "ou=lafeuilleOrg")
class Person(
    @Id
    var dn: Name?,
    @Attribute(name = "cn")
    @DnAttribute(value = "cn", index = 1)
    var fullName: String?,

    var birthDate: String?,
    var lastName: String?,
    var firstName: String?,
) {
    constructor() : this(null, null, null, null, null)
}
